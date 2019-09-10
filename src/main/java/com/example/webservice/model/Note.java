package com.example.webservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int version;

    @JsonProperty("title")
    @NotBlank
    private String title;
    @JsonProperty("content")
    private String content;

    //Timestamps

    @CreationTimestamp
    @Column(name = "created")
    private LocalDate created;

    @UpdateTimestamp
    @Column(name = "modified")
    private LocalDate modified;


    public Note(){
    }

    public Note(NoteContent noteContent) {
        this.title = noteContent.getTitle();
        this.content = noteContent.getContent();
        this.created =  LocalDate.now();
        this.modified = LocalDate.now();
    }

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreated() {
        return created;
    }

    public LocalDate getModified() {
        return modified;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void incrementVersion(){
        this.version++;
    }
}
