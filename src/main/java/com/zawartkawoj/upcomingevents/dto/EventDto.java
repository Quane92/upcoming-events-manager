package com.zawartkawoj.upcomingevents.dto;

import java.time.LocalDate;

public class EventDto {

    private String name;
    private String note;
    private LocalDate date;

    public EventDto() {
    }

    public EventDto(String name, String note, LocalDate date) {
        this.name = name;
        this.note = note;
        this.date = date;
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
