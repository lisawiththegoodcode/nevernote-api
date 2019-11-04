package com.nevernote.api.nevernoteapi.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Notebook {
    //BEGIN FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant lastModified;

    @OneToMany(
            mappedBy = "notebook",
            cascade = CascadeType.ALL
    )
    private List<Note> notes = new ArrayList<Note>();

    //END FIELDS

    //BEGIN CONSTRUCTOR
    public Notebook(){}

    public Notebook(String name){
        this.name = name;
    }
    //END CONSTRUCTOR

    //BEGIN GETTER/SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getLastModified() {
        return lastModified;
    }

    public void setLastModified(Instant lastModified) {
        this.lastModified = lastModified;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //END GETTER/SETTERS
}
