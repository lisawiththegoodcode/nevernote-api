package com.nevernote.api.nevernoteapi;

import java.sql.Timestamp;

public class Note {
    //BEGIN FIELDS
    private final Long id;
    private final Timestamp createdAt;
    private Timestamp lastModified;
    private String title;
    private String body;
    private String[] tags;
    //END FIELDS

    //BEGIN CONSTRUCTOR
    public Note(Long id, Timestamp createdAt, Timestamp lastModified, String title, String body, String[] tags){
        this.id = id;
        this.createdAt = createdAt;
        this.lastModified = lastModified;
        this.title = title;
        this.body = body;
        this.tags = tags;
     }
    //END CONSTRUCTOR

    //BEGIN GETTER/SETTERS
    public Long getId() {
        return id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
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

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
    //END GETTER/SETTERS

}
