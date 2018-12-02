package com.stylefeng.guns.rest.core.util.docTransform;

import java.io.*;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * 利用jodconverter(基于OpenOffice服务)将文件(*.doc、*.docx、*.xls、*.ppt)转化为html格式或者pdf格式，
 * 使用前请检查OpenOffice服务是否已经开启, OpenOffice进程名称：soffice.exe | soffice.bin
 *
 * @author hyj
 */
public class Doc2HtmlUtil {
    private static Doc2HtmlUtil doc2HtmlUtil;

    /**
     * 获取Doc2HtmlUtil实例
     */
    public static Doc2HtmlUtil getDoc2HtmlUtilInstance() {
        if (doc2HtmlUtil == null) {
            synchronized (Doc2HtmlUtil.class) {
                if (doc2HtmlUtil == null) {
                    doc2HtmlUtil = new Doc2HtmlUtil();
                }
            }
        }
        return doc2HtmlUtil;
    }

    /**
     * 转换文件成html
     *
     * @param sourceFile:
     * @throws IOException
     */
    public File file2Html(File sourceFile, String toFilePath, String type) throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timesuffix = sdf.format(date);
        String htmFileName = null;
        if ("doc".equals(type)) {
            htmFileName = "doc_" + timesuffix + ".html";
        } else if ("docx".equals(type)) {
            htmFileName = "docx_" + timesuffix + ".html";
        } else if ("xls".equals(type)) {
            htmFileName = "xls_" + timesuffix + ".html";
        } else if ("ppt".equals(type)) {
            htmFileName = "ppt_" + timesuffix + ".html";
        } else {
            return null;
        }
        File fileDir = new File(toFilePath);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        File htmlOutputFile = new File(toFilePath + File.separatorChar + htmFileName);
        //File docInputFile = new File(toFilePath + File.separatorChar + docFileName);
        if (htmlOutputFile.exists())
            htmlOutputFile.delete();
        htmlOutputFile.createNewFile();
        /**
         * 由fromFileInputStream构建输入文件
         */
        /*try {
            OutputStream os = new FileOutputStream(docInputFile);
            int bytesRead = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((bytesRead = fromFileInputStream.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fromFileInputStream.close();
        } catch (IOException e) {
        }*/

        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        try {
            connection.connect();
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            converter.convert(sourceFile, htmlOutputFile);

        } catch (IllegalArgumentException e) {
            System.err.println("格式不支持");
        } catch (ConnectException e) {
            System.err.println("文件转换出错，请检查OpenOffice服务是否启动。");
        } catch (OpenOfficeException e) {
            e.printStackTrace();
            System.out.println("****swf转换器异常，读取转换文件 失败****");
        } finally {
            connection.disconnect();
        }
        // convert

        // 转换完之后删除word文件
        //docInputFile.delete();
        return htmlOutputFile;
    }

    /**
     * 将word转换成html文件，并且获取html文件代码。
     *
     * @param sourceFile 需要转换的文档
     * @param filepath   文档中图片的保存位置
     * @return 转换成功的html代码
     */
    public String toHtmlString(File sourceFile, String filepath, String type) {
        // 转换word文档
        File htmlFile = null;
        try {
            htmlFile = file2Html(sourceFile, filepath, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取html文件流
        StringBuffer htmlSb = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(htmlFile), "gb2312"));
            while (br.ready()) {
                htmlSb.append(br.readLine());
            }
            br.close();
            // 删除临时文件
            htmlFile.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // HTML文件字符串
        String htmlStr = htmlSb.toString();
        // 返回经过清洁的html文本
        return clearFormat(htmlStr, filepath);
    }

    /**
     * 清除一些不需要的html标记
     *
     * @param htmlStr 带有复杂html标记的html语句
     * @return 去除了不需要html标记的语句
     */
    protected static String clearFormat(String htmlStr, String docImgPath) {
        // 获取body内容的正则
        String bodyReg = "<BODY .*</BODY>";
        Pattern bodyPattern = Pattern.compile(bodyReg);
        Matcher bodyMatcher = bodyPattern.matcher(htmlStr);
        if (bodyMatcher.find()) {
            // 获取BODY内容，并转化BODY标签为DIV
            htmlStr = bodyMatcher.group().replaceFirst("<BODY", "<DIV")
                    .replaceAll("</BODY>", "</DIV>");
        }
        // 调整图片地址
        htmlStr = htmlStr.replaceAll("<IMG SRC=\"", "<IMG SRC=\"" + docImgPath
                + "/");
        // 把<P></P>转换成</div></div>保留样式
        // content = content.replaceAll("(<P)([^>]*>.*?)(<\\/P>)",
        // "<div$2</div>");
        // 把<P></P>转换成</div></div>并删除样式
        htmlStr = htmlStr.replaceAll("(<P)([^>]*)(>.*?)(<\\/P>)", "<p$3</p>");
        // 删除不需要的标签
        htmlStr = htmlStr
                .replaceAll(
                        "<[/]?(font|FONT|span|SPAN|xml|XML|del|DEL|ins|INS|meta|META|[ovwxpOVWXP]:\\w+)[^>]*?>",
                        "");
        // 删除不需要的属性
        htmlStr = htmlStr
                .replaceAll(
                        "<([^>]*)(?:lang|LANG|class|CLASS|style|STYLE|size|SIZE|face|FACE|[ovwxpOVWXP]:\\w+)=(?:'[^']*'|\"\"[^\"\"]*\"\"|[^>]+)([^>]*)>",
                        "<$1$2>");
        return htmlStr;
    }


    /*public HtmlDocument String2Html(String htmlString)
    {
        WebBrowser getInfoWebBrowser = new WebBrowser();
        getInfoWebBrowser.ScriptErrorsSuppressed = true;
        getInfoWebBrowser.DocumentText = htmlString;

        HtmlDocument html = getInfoWebBrowser.Document;
        Application.DoEvents();
        getInfoWebBrowser.Dispose();

        return html;
    }*/

    /**
     * 转换文件成pdf
     *
     * @param fromFileInputStream:
     * @throws IOException
     */
    public String file2pdf(InputStream fromFileInputStream, String toFilePath, String type) throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timesuffix = sdf.format(date);
        String docFileName = null;
        String htmFileName = null;
        if ("doc".equals(type)) {
            docFileName = "doc_" + timesuffix + ".doc";
            htmFileName = "doc_" + timesuffix + ".pdf";
        } else if ("docx".equals(type)) {
            docFileName = "docx_" + timesuffix + ".docx";
            htmFileName = "docx_" + timesuffix + ".pdf";
        } else if ("xls".equals(type)) {
            docFileName = "xls_" + timesuffix + ".xls";
            htmFileName = "xls_" + timesuffix + ".pdf";
        } else if ("ppt".equals(type)) {
            docFileName = "ppt_" + timesuffix + ".ppt";
            htmFileName = "ppt_" + timesuffix + ".pdf";
        } else {
            return null;
        }

        File fileDir = new File(toFilePath);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        File htmlOutputFile = new File(toFilePath + File.separatorChar + htmFileName);
        File docInputFile = new File(toFilePath + File.separatorChar + docFileName);
        if (htmlOutputFile.exists())
            htmlOutputFile.delete();
        htmlOutputFile.createNewFile();
        if (docInputFile.exists())
            docInputFile.delete();
        docInputFile.createNewFile();
        /**
         * 由fromFileInputStream构建输入文件
         */
        try {
            OutputStream os = new FileOutputStream(docInputFile);
            int bytesRead = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((bytesRead = fromFileInputStream.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }

            os.close();
            fromFileInputStream.close();
        } catch (IOException e) {
        }

        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        try {
            connection.connect();
        } catch (ConnectException e) {
            System.err.println("文件转换出错，请检查OpenOffice服务是否启动。");
        }
        // convert
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        converter.convert(docInputFile, htmlOutputFile);
        connection.disconnect();
        // 转换完之后删除word文件
        docInputFile.delete();
        return htmFileName;
    }

    public static void main(String[] args) throws IOException {
        Doc2HtmlUtil coc2HtmlUtil = getDoc2HtmlUtilInstance();
        File file = null;
        FileInputStream fileInputStream = null;

        file = new File("C:\\Users\\john\\Desktop\\完整的开发文档数据库设计说明书.doc");
        String s = coc2HtmlUtil.toHtmlString(file, "C:\\Users\\john\\Desktop\\openoffice\\doc2html", "doc");
        System.out.println(s);
        //coc2HtmlUtil.file2pdf(fileInputStream, "C:\\Users\\john\\Desktop\\openoffice\\doc","doc");

       /* file = new File("D:/poi-test/test.doc");
        fileInputStream = new FileInputStream(file);
        coc2HtmlUtil.file2Html(fileInputStream, "D:/poi-test/openOffice/doc","doc");
        coc2HtmlUtil.file2pdf(fileInputStream, "D:/poi-test/openOffice/doc","doc");

        file = new File("D:/poi-test/周报模版.ppt");
        fileInputStream = new FileInputStream(file);
        coc2HtmlUtil.file2Html(fileInputStream, "D:/poi-test/openOffice/ppt","ppt");
        coc2HtmlUtil.file2pdf(fileInputStream, "D:/poi-test/openOffice/ppt","ppt");*/

        file = new File("C:\\Users\\john\\Desktop\\vsftpd详细配置解读.docx");
//      coc2HtmlUtil.file2Html(fileInputStream, "D:/poi-test/openOffice/docx","docx");
        coc2HtmlUtil.toHtmlString(file, "C:\\Users\\john\\Desktop\\openoffice\\html_new", "docx");

    }

}
