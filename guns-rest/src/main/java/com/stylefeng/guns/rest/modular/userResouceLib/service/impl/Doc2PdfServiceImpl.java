/*
package com.stylefeng.guns.rest.modular.userResouceLib.service.impl;

import com.aspose.words.*;
import com.dm.docpreview.convert.domain.ConvertStatus;
import com.dm.docpreview.convert.service.File2PdfService;
import com.dm.docpreview.convert.util.AsposeLicenseUtil;
import com.stylefeng.guns.core.util.aspose.AsposeLicenseUtil;
import com.stylefeng.guns.rest.modular.userResouceLib.service.IFile2PdfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.InputStream;
import java.io.OutputStream;

*/
/**
 * Created by hyj on 2018/11/29
 * <p>
 * Inserts a watermark into a document.
 *
 * @param doc           The input document.
 * @param watermarkText Text of the watermark.
 *//*

@Service
public class Doc2PdfServiceImpl implements IFile2PdfService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ConvertStatus convert2Pdf(InputStream inputStream, OutputStream outputStream) {
        try {
            if (AsposeLicenseUtil.setWordsLicense()) {
                long start = System.currentTimeMillis();

                Document doc = new Document(inputStream);

                // insertWatermarkText(doc, "测试水印"); // 添加水印

                PdfSaveOptions pdfSaveOptions = new PdfSaveOptions();
                pdfSaveOptions.setSaveFormat(SaveFormat.PDF);
                pdfSaveOptions.getOutlineOptions().setHeadingsOutlineLevels(3); // 设置3级doc书签需要保存到pdf的heading中
                pdfSaveOptions.getOutlineOptions().setExpandedOutlineLevels(1); // 设置pdf中默认展开1级

                doc.save(outputStream, pdfSaveOptions);
                long end = System.currentTimeMillis();
                logger.debug("convert doc2pdf completed, elapsed " + (end - start) / 1000.0 + " seconds!");
                return ConvertStatus.SUCCESS;
            } else {
                return ConvertStatus.LICENSE_ERROR;
            }
        } catch (Exception e) {
            logger.error("convert doc2pdf error!", e);
            return ConvertStatus.CONVERT_DOC2PDF_ERROR;
        }
    }


    */
/**
 * Inserts a watermark into a document.
 *
 * @param doc           The input document.
 * @param watermarkText Text of the watermark.
 *//*

    private void insertWatermarkText(Document doc, String watermarkText) throws Exception {
        // Create a watermark shape. This will be a WordArt shape.
        // You are free to try other shape types as watermarks.
        Shape watermark = new Shape(doc, ShapeType.TEXT_PLAIN_TEXT);

        // Set up the text of the watermark.
        // watermark.getTextPath().setSize(16.0);
        // watermark.getTextPath().setFontFamily("Arial"); // 使用Arial时最后那个字会丢
        watermark.getTextPath().setFontFamily("宋体");
        watermark.getTextPath().setItalic(true);
        watermark.getTextPath().setText(watermarkText);

        // Font size does not have effect if you specify height of the shape.
        // So you can just specify height instead of specifying font size.
        double fontSize = 100.0;
        watermark.setWidth(watermarkText.length() * fontSize);
        watermark.setHeight(fontSize);

        // Text will be directed from the bottom-left to the top-right corner.
        watermark.setRotation(-30);
        // Remove the following two lines if you need a solid black text.
        watermark.getFill().setColor(Color.lightGray); // Try LightGray to get more Word-style watermark
        watermark.setStrokeColor(Color.lightGray); // Try LightGray to get more Word-style watermark

        // Place the watermark in the page center.
        watermark.setRelativeHorizontalPosition(RelativeHorizontalPosition.PAGE);
        watermark.setRelativeVerticalPosition(RelativeVerticalPosition.PAGE);
        watermark.setWrapType(WrapType.NONE);
        watermark.setVerticalAlignment(VerticalAlignment.CENTER);
        watermark.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // watermark.setHorizontalAlignment(HorizontalAlignment.LEFT);

        // Create a new paragraph and append the watermark to this paragraph.
        Paragraph watermarkPara = new Paragraph(doc);
        watermarkPara.appendChild(watermark);

        // Insert the watermark into all headers of each document section.
        for (Section sect : doc.getSections()) {
            // There could be up to three different headers in each section, since we want
            // the watermark to appear on all pages, insert into all headers.
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_PRIMARY);
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_FIRST);
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_EVEN);
        }
    }

    private void insertWatermarkIntoHeader(Paragraph watermarkPara, Section sect, int headerType) throws Exception {
        HeaderFooter header = sect.getHeadersFooters().getByHeaderFooterType(headerType);

        if (header == null) {
            // There is no header of the specified type in the current section, create it.
            header = new HeaderFooter(sect.getDocument(), headerType);
            sect.getHeadersFooters().add(header);
        }

        // Insert a clone of the watermark into the header.
        header.appendChild(watermarkPara.deepClone(true));
    }
}
*/
