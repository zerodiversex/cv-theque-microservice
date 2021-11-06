package com.elasticsearch.cvssearch.services;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class CVService {

    public String getContentFromFileByType(MultipartFile file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String content;
        if (Objects.equals(extension, "pdf")) {
            content = readContentFromPDFFile(file.getBytes());
        } else if (Objects.equals(extension, "docx") || Objects.equals(extension, "doc")) {
            content = readContentFromDocxFile(file.getBytes());
        } else {
            content = readContentFromDocxFile(file.getBytes());
        }
        return content;
    }

    public String readContentFromPDFFile(byte[] file) {
        String content = "";
        try {
            PDDocument document = PDDocument.load(file);

            PDFTextStripper stripper = new PDFTextStripper();

            content = stripper.getText(document);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public String readContentFromDocxFile(byte[] file) {
        StringBuilder content = new StringBuilder();
        try {
            XWPFDocument document = new XWPFDocument(new ByteArrayInputStream(file));

            List<XWPFParagraph> paragraphs = document.getParagraphs();

            for (XWPFParagraph para : paragraphs) {
                content.append(para.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}
