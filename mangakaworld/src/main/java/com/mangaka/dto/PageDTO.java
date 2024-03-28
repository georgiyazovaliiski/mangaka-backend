package com.mangaka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageDTO {
    @JsonProperty("pageId")
    private Long pageId;

    @JsonProperty("songName")
    private String songName;

    public PageDTO(Long pageId, String songName) {
        this.pageId = pageId;
        this.songName = songName;
    }

    public PageDTO() {
    }

    public Long getPageId() {
        return this.pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public String getSongName() {
        return this.songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

}
