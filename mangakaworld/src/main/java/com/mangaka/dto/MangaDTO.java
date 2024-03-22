package com.mangaka.dto;

import java.util.List;

public class MangaDTO {
    private String mangaka;
    private String name;
    private String description;
    private List<String> pagesFileNames;

    public String getMangaka() {
        return this.mangaka;
    }

    public void setMangaka(String mangaka) {
        this.mangaka = mangaka;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPagesFileNames() {
        return this.pagesFileNames;
    }

    public void setPagesFileNames(List<String> pagesFileNames) {
        this.pagesFileNames = pagesFileNames;
    }

}
