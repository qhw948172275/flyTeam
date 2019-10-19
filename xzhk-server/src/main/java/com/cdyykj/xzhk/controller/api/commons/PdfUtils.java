package com.cdyykj.xzhk.controller.api.commons;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class PdfUtils {

    // 利用模板生成pdf
    public static String pdfout(Map<String,String> o,String prefix,String submitor,String createTime) throws IOException {
        // 模板路径
        String templatePath = "static\\pdf\\flightReport.pdf";
        // 生成的新文件路径
        String newFileName=submitor+"飞行报告详情"+createTime+".pdf";
        String newPDFPath =prefix+newFileName;

        ClassPathResource classPathResource = new ClassPathResource(templatePath);
        InputStream inputStream = classPathResource.getInputStream();

        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            out = new FileOutputStream(newPDFPath);// 输出流
            reader = new PdfReader(inputStream);// 读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            for(String key : o.keySet()){
                String value = o.get(key);
                form.setField(key,value);
            }

            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();

            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
            reader.close();
            bos.close();
            return newFileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
