package com.stylefeng.guns.generator.engine.base;

import com.stylefeng.guns.core.util.ToolUtil;
import com.sun.javafx.PlatformUtil;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ADI项目模板生成 引擎
 *
 * @author fengshuonan
 * @date 2017-05-07 22:15
 */
public abstract class GunsTemplateEngine extends AbstractTemplateEngine {

    private GroupTemplate groupTemplate;//结合模板和变量生成代码的过程就是这个类的作用

    public GunsTemplateEngine() {
        initBeetlEngine();
    }

    protected void initBeetlEngine() {
        Properties properties = new Properties();
        properties.put("RESOURCE.root", "");
        properties.put("DELIMITER_STATEMENT_START", "<%");
        properties.put("DELIMITER_STATEMENT_END", "%>");
        properties.put("HTML_TAG_FLAG", "##");
        Configuration cfg = null;
        try {
            cfg = new Configuration(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        groupTemplate = new GroupTemplate(resourceLoader, cfg);//生成beetl代码生成器的一个主类
        groupTemplate.registerFunctionPackage("tool", new ToolUtil());//模板里就可以利用tool关键字来调用静态方法
    }

    protected void configTemplate(Template template) {
        //利用模板引擎进行值的绑定，模板到时才能知道到哪里取值
        template.binding("controller", super.controllerConfig);
        template.binding("context", super.contextConfig);
        template.binding("dao", super.daoConfig);
        template.binding("service", super.serviceConfig);
        template.binding("sqls", super.sqlConfig);
        template.binding("table", super.tableInfo);
    }

    //把模板输出到文件中，是一个输出的过程

    /**
     * 这里模板可以经过beetl内部转化绑定变量之后输出的一个代码的路径（整合得到file）
     * @param template 模板的路径 如Controller.java.btl
     * @param filePath
     */
    protected void generateFile(String template, String filePath) {
        Template pageTemplate = groupTemplate.getTemplate(template);
        configTemplate(pageTemplate);
        if (PlatformUtil.isWindows()) {
            // 转到window下的路径分隔符\\（转义）
            filePath = filePath.replaceAll("/+|\\\\+", "\\\\");
        } else {
            //linux路径分隔符/
            filePath = filePath.replaceAll("/+|\\\\+", "/");
        }
        File file = new File(filePath);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            //beetl拿到模板和变量后开始渲染，渲染到对应的代码里面（文件输出流输出到文件中）
            pageTemplate.renderTo(fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 外部调用类开始的地方
     * 开始模板生成的代码
     */
    public void start() {
        //配置之间的相互依赖
        //初始化变量
        super.initConfig();

        //生成模板
        if (super.contextConfig.getControllerSwitch()) {
            generateController();
        }
        if (super.contextConfig.getIndexPageSwitch()) {
            generatePageHtml();
        }
        if (super.contextConfig.getAddPageSwitch()) {
            generatePageAddHtml();
        }
        if (super.contextConfig.getEditPageSwitch()) {
            generatePageEditHtml();
        }
        if (super.contextConfig.getJsSwitch()) {
            generatePageJs();
        }
        if (super.contextConfig.getInfoJsSwitch()) {
            generatePageInfoJs();
        }
        if (super.contextConfig.getSqlSwitch()) {
            generateSqls();
        }
    }

    protected abstract void generatePageEditHtml();

    protected abstract void generatePageAddHtml();

    protected abstract void generatePageInfoJs();

    protected abstract void generatePageJs();

    protected abstract void generatePageHtml();

    protected abstract void generateController();

    protected abstract void generateSqls();

}
