package com.zawartkawoj.upcomingevents.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class EventDto {

    private Integer id;
    private String name;
    private String note;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public EventDto() {
    }

    public EventDto(String name, String note, LocalDate date) {
        this.name = name;
        this.note = note;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}
