package com.mangaka.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
    @Value("${manga.storage.location}")
    public String mangaStorageLocation;

    @Value("$(manga.storage.song.location)")
    public String songStorageLocation;

    public List<String> save(MultipartFile pdfFile) throws IOException {
        try (PDDocument document = PDDocument.load(pdfFile.getInputStream())) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            int pageCount = document.getNumberOfPages();

            List<String> pageList = new ArrayList<>();

            for (int pageIndex = 0; pageIndex < pageCount; pageIndex++) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(pageIndex, 300); // 300 DPI

                String outputFileName = String.format("%s/%s%s%d%s", mangaStorageLocation, System.currentTimeMillis(),
                        "_page_", (pageIndex + 1), ".jpg");

                ImageIO.write(bim, "jpg", new File(outputFileName));

                pageList.add(outputFileName);
            }
            return pageList;
        }
    }

    public List<String> save(List<MultipartFile> jpgs) throws IOException {
        Path directory = Paths.get(mangaStorageLocation);
        Files.createDirectories(directory);

        List<String> pageList = new ArrayList<>();

        for (int pageIndex = 0; pageIndex < jpgs.size(); pageIndex++) {
            String outputFileName = String.format("%s/%s%s%d%s", mangaStorageLocation, System.currentTimeMillis(),
                    "_page_", pageIndex + 1, ".jpg");

            Path filePath = Paths.get(outputFileName);
            jpgs.get(pageIndex).transferTo(filePath);

            pageList.add(filePath.getFileName().toString());
        }

        return pageList;
    }

    public String saveSong(MultipartFile soundFile) throws IOException {
        Path directory = Paths.get(songStorageLocation);
        Files.createDirectories(directory);

        String outputFileName = String.format("%s/%s%s%d%s", songStorageLocation, System.currentTimeMillis(),
                "_page_", soundFile.getOriginalFilename(), ".mp3");

        Path filePath = Paths.get(outputFileName);
        soundFile.transferTo(filePath);

        return filePath.getFileName().toString();
    }
}
