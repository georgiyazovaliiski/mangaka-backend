package com.mangaka.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileSaver {
    @Value("${manga.storage.location}")
    public String mangaStorageLocation;

    public List<String> savePdfAsJpgs(MultipartFile pdfFile) throws IOException {
        try (PDDocument document = PDDocument.load(pdfFile.getInputStream())) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            int pageCount = document.getNumberOfPages();

            List<String> pageList = new ArrayList<>();

            for (int pageIndex = 0; pageIndex < pageCount; pageIndex++) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(pageIndex, 300); // 300 DPI
                String outputFileName = mangaStorageLocation + "page_" + (pageIndex + 1) + ".jpg";
                ImageIO.write(bim, "jpg", new File(outputFileName));
                pageList.add(outputFileName);
            }
            return pageList;
        }
    }

    public List<String> saveJpgs(List<MultipartFile> jpgs) throws IOException {
        Path directory = Paths.get(mangaStorageLocation);
        Files.createDirectories(directory);
        List<String> pageList = new ArrayList<>();

        for (MultipartFile file : jpgs) {
            String originalFilename = file.getOriginalFilename();
            String uniqueFilename = System.currentTimeMillis() + "_" + originalFilename;
            Path filePath = Paths.get(mangaStorageLocation, uniqueFilename);
            file.transferTo(filePath);
            System.out.println("File saved: " + filePath);
            pageList.add(filePath.getFileName().toString());
        }

        return pageList;
    }
}
