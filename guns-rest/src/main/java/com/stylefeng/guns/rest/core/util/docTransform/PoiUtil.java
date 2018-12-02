package com.stylefeng.guns.rest.core.util.docTransform;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

/**
 * 利用jodconverter(基于OpenOffice服务)将文件(*.doc、*.docx、*.xls、*.ppt)转化为html格式或者pdf格式，
 * 使用前请检查OpenOffice服务是否已经开启, OpenOffice进程名称：soffice.exe | soffice.bin
 *
 * @author hyj
 */
public class PoiUtil {
    private static PoiUtil poiUtil;

    /**
     * 获取Doc2HtmlUtil实例
     */
    public static PoiUtil getPoiUtilInstance() {
        if (poiUtil == null) {
            synchronized(PoiUtil.class){
                if (poiUtil == null) {
                    poiUtil = new PoiUtil();
                }
            }
        }
        return poiUtil;
    }
    public static void wordToHtml(String wordPath,String htmlPath,String newFilename) throws TransformerException, IOException, ParserConfigurationException {
        convert2Html(wordPath, htmlPath, newFilename);
    }

    public static void writeFile(String content, String path) {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            File file = new File(path);
            if(!file.exists()){

            }
            fos = new FileOutputStream(file);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(content);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fos != null)
                    fos.close();
            } catch (IOException ie) {
            }
        }
    }

    /**
     * 将word转换成html
     * 支持 .doc and .docx
     * @param fileName word文件名
     * @param outPutFilePath html存储路径
     * @param newFileName html名
     * @throws TransformerException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static void convert2Html(String fileName, String outPutFilePath,String newFileName)
            throws TransformerException, IOException,
            ParserConfigurationException {
        String substring = fileName.substring(fileName.lastIndexOf(".")+1);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        OutputStreamWriter outputStreamWriter = null;
        /**
         * word2007和word2003的构建方式不同，
         * 前者的构建方式是xml，后者的构建方式是dom树。
         * 文件的后缀也不同，前者后缀为.docx，后者后缀为.doc
         * 相应的，apache.poi提供了不同的实现类。
         */
        if("docx".equals(substring)){
//    		writeFile(new String("<html><head>  <meta http-equiv=\"content-type\" content=\"text/html\" charset=\"utf-8\"/></head>对不起，.docx格式的word文档，暂时不能生成预览</html>".getBytes("utf-8")), outPutFilePath+newFileName);

            //step 1 : load DOCX into XWPFDocument
            InputStream inputStream = new FileInputStream(new File(fileName));
            XWPFDocument document = new XWPFDocument(inputStream);

            //step 2 : prepare XHTML options
            final String imageUrl = "\\image\\";

            XHTMLOptions options = XHTMLOptions.create();
            options.setExtractor(new FileImageExtractor(new File(outPutFilePath + imageUrl)));
            options.setIgnoreStylesIfUnused(false);
            options.setFragment(true);
            options.URIResolver(new FileURIResolver(new File(outPutFilePath+imageUrl)));
                /*new IURIResolver() {
                    //  @Override 重写的方法，加上这个报错，你看看是啥问题
                    public String resolve(String uri) {
                        return imageUrl + uri;
                    }
                }*/

            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(outPutFilePath+newFileName), "utf-8");
            //step 3 : convert XWPFDocument to XHTML
            XHTMLConverter xhtmlConverter = (XHTMLConverter)XHTMLConverter.getInstance();
            xhtmlConverter.convert(document, outputStreamWriter, options);
        }else{
            //根据输入文件路径与名称读取文件流
            //把文件流转化为输入wordDom对象
            HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName));//poiUtils.loadDoc(new FileInputStream(inputFile));
            //通过反射构建dom创建者工厂 DocumentBuilderFactory.newInstance()
            //生成dom创建者
            //生成dom对象
            //生成针对Dom对象的转化器
            WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                    DocumentBuilderFactory.newInstance().newDocumentBuilder()
                            .newDocument());
            //转化器重写内部方法
            wordToHtmlConverter.setPicturesManager( new PicturesManager()
            {
                public String savePicture( byte[] content,
                                           PictureType pictureType, String suggestedName,
                                           float widthInches, float heightInches )
                {
                    return suggestedName;
                }
            } );
            //转化器开始转化接收到的dom对象
            wordToHtmlConverter.processDocument(wordDocument);
            //save pictures
            List pics=wordDocument.getPicturesTable().getAllPictures();
            if(pics!=null){
                for(int i=0;i<pics.size();i++){
                    Picture pic = (Picture)pics.get(i);
                    System.out.println();
                    try {
                        pic.writeImageContent(new FileOutputStream(outPutFilePath
                                + pic.suggestFullFileName()));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            //从加载了输入文件中的转换器中提取DOM节点
            Document htmlDocument = wordToHtmlConverter.getDocument();
            //从提取的DOM节点中获得内容
            DOMSource domSource = new DOMSource(htmlDocument);
            //输出流的源头
            StreamResult streamResult = new StreamResult(out);
            //转化工厂生成序列转化器
            TransformerFactory tf = TransformerFactory.newInstance();    //这个应该是转换成xml的
            Transformer serializer = tf.newTransformer();
            //设置序列化内容格式
            serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(domSource, streamResult);
        }

        out.close();
        outputStreamWriter.close();
        //生成文件方法
        writeFile(new String(out.toByteArray()), outPutFilePath+newFileName);
    }

    public static void poiExcelToHtml (String fileName, String outPutFilePath,String newFileName) throws IOException, ParserConfigurationException, TransformerException {
        InputStream input=new FileInputStream(fileName);
        HSSFWorkbook excelBook = new HSSFWorkbook(input);
        ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument() );
        excelToHtmlConverter.processWorkbook(excelBook);
        List pics = excelBook.getAllPictures();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                Picture pic = (Picture) pics.get (i);
                try {
                    pic.writeImageContent (new FileOutputStream (outPutFilePath + pic.suggestFullFileName() ) );
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        Document htmlDocument =excelToHtmlConverter.getDocument();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource (htmlDocument);
        StreamResult streamResult = new StreamResult (outStream);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty (OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty (OutputKeys.INDENT, "yes");
        serializer.setOutputProperty (OutputKeys.METHOD, "html");
        serializer.transform (domSource, streamResult);
        outStream.close();

        String content = new String (outStream.toByteArray() );

        FileUtils.writeStringToFile(new File (outPutFilePath, "exportExcel.html"), content, "utf-8");

    }

    /*public static boolean PPTtoImage(File file){
        boolean isppt = checkFile(file);
        if (!isppt) {
            System.out.println("The image you specify don't exit!");
            return false;
        }
        try {

            FileInputStream is = new FileInputStream(file);
            SlideShow ppt = new SlideShow(is);
            is.close();
            Dimension pgsize = ppt.getPageSize();
            List slides = ppt.getSlides();
            for (int i = 0; i < slide.length; i++) {
                System.out.print("第" + i + "页。");

                TextRun[] truns = slide[i].getTextRuns();
                for ( int k=0;k<truns.length;k++){
                    RichTextRun[] rtruns = truns[k].getRichTextRuns();
                    for(int l=0;l<rtruns.length;l++){
                        int index = rtruns[l].getFontIndex();
                        String name = rtruns[l].getFontName();
                        rtruns[l].setFontIndex(1);
                        rtruns[l].setFontName("宋体");
//                        System.out.println(rtruns[l].getText());
                    }
                }
                BufferedImage img = new BufferedImage(pgsize.width,pgsize.height, BufferedImage.TYPE_INT_RGB);

                Graphics2D graphics = img.createGraphics();
                graphics.setPaint(Color.BLUE);
                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
                slide[i].draw(graphics);

                // 这里设置图片的存放路径和图片的格式(jpeg,png,bmp等等),注意生成文件路径
                FileOutputStream out = new FileOutputStream("D:/poi-test/pptToImg/pict_"+ (i + 1) + ".jpeg");
                javax.imageio.ImageIO.write(img, "jpeg", out);
                out.close();

            }
            System.out.println("success!!");
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(e);
            // System.out.println("Can't find the image!");
        } catch (IOException e) {
        }
        return false;
    }*/

    public static boolean checkFile(File file) {

        boolean isppt = false;
        String filename = file.getName();
        String suffixname = null;
        if (filename != null && filename.indexOf(".") != -1) {
            suffixname = filename.substring(filename.indexOf("."));
            if (suffixname.equals(".ppt")) {
                isppt = true;
            }
            return isppt;
        } else {
            return isppt;
        }
    }

    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException {
        wordToHtml("C:\\Users\\john\\Desktop\\vsftpd详细配置解读.docx","C:\\Users\\john\\Desktop\\openoffice\\html_new\\",System.currentTimeMillis()+".html");


    }

}
