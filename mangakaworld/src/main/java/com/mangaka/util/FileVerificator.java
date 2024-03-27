package com.mangaka.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileVerificator {
    public final List<String> PERMITTED_IMAGE_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png");

    private String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return null; // No extension found
    }

    public boolean FileIsPDF(MultipartFile file) {
        return getFileExtension(file).equals("pdf");
    }

    public boolean FileIsImage(MultipartFile file) {
        return PERMITTED_IMAGE_EXTENSIONS.contains(getFileExtension(file));
    }

    public boolean FileIsImage(List<MultipartFile> files) {
        for (MultipartFile file : files) {
            if (!PERMITTED_IMAGE_EXTENSIONS.contains(getFileExtension(file))) {
                return false;
            }
        }
        return true;
    }
}
