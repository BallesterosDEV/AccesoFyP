package com.proyectoacceso02ev.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PublicationDTO {
    @JsonIgnore
    private long id;
    private String text;

    public PublicationDTO() {
        this.text = "";
    }

    public PublicationDTO(String text) {
        this.text = text;
    }

    public PublicationDTO(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
