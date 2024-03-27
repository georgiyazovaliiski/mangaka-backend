package com.mangaka.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MangaDTO {
    @JsonProperty("mangaka")
    private String mangaka;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    private List<String> pagesFileNames;

    public MangaDTO() {
    }

    public MangaDTO(String mangaka, String name, String description, List<String> pagesFileNames) {
        this.mangaka = mangaka;
        this.name = name;
        this.description = description;
        this.pagesFileNames = pagesFileNames;
    }

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
