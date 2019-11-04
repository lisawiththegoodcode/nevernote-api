package com.nevernote.api.nevernoteapi.models;

import javax.persistence.*;
import java.security.PublicKey;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "note_id")
    private Note note;

    //CONSTRUCTORS
    public Tag(){}

    public Tag(String name){
        this.name = name;
    }

    //GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
