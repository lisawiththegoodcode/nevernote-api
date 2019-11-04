package com.nevernote.api.nevernoteapi.models;

import com.nevernote.api.nevernoteapi.models.Notebook;
import com.nevernote.api.nevernoteapi.models.Tag;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Note {
    //BEGIN FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//change to instant
    //JSON Serialization?
    //intermediate command object represents between browser and server; server and db
    //simplenote doesn't have all of these things or notecommand
    //the things that you want to tell the client about

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant lastModified;

    private String title;
    private String body;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "note",
            cascade = CascadeType.ALL
    )
    private List<Tag> tags = new ArrayList<>();
//    private List<String> tags = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "notebook_id")
    private Notebook notebook;
    //END FIELDS

    //BEGIN CONSTRUCTORS
    public Note(){}

    public Note(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Note(String title, String body, List<Tag> tags) {
        this.title = title;
        this.body = body;
        this.setTags(tags);
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Notebook getNotebook() {
        return notebook;
    }

    public void setNotebook(Notebook notebook) {
        this.notebook = notebook;
    }
    //END GETTER/SETTERS

}
