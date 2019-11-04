package com.nevernote.api.nevernoteapi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nevernote.api.nevernoteapi.models.Note;
import com.nevernote.api.nevernoteapi.models.Notebook;
import com.nevernote.api.nevernoteapi.repositories.NoteRepository;
import com.nevernote.api.nevernoteapi.models.Tag;
import com.nevernote.api.nevernoteapi.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    TagRepository tagRepository;


//    @GetMapping("notes")
//    public List<NoteCommand> getNotes() {
//        NoteCommand output = new NoteCommand();
//        List<NoteCommand> notes = new ArrayList<>();
//
//        for (Note note : noteRepository.findAll()) {
//            output.title = note.getTitle();
//            output.body = note.getBody();
//            for (Tag tag : note.getTags()) {
//                output.tags.add(tag.getName());
//            }
//            notes.add(output);
//        }
//        return notes;
//    }

//        List<Note> notes = new ArrayList<>();
//
//        for (Note n:(List<Note>)noteRepository.findAll()) {
//            System.out.println(n);
//        }
//
//        for (Note note : noteRepository.findAll()) {
//            for (Tag tag : note.getTags()) {
//                System.out.println(tag);
//            }
//            notes.add(note);
//        }
    //        return notes;

    @GetMapping("/notes")
    public List<Note> getNotes() {
        return (List<Note>) noteRepository.findAll();
    }

    @GetMapping("/notes/{id}")
    public Note getNoteById (@PathVariable Long id){
        Optional<Note> dbNote = noteRepository.findById(id);

        if(dbNote.isPresent()) {
            return dbNote.get();
        }
        else {
            return null;
        }
    }



//    @PostMapping("/notes")
    //take in an object, map the object back and forth
//    public Note NewNote(@RequestBody Map<String, Object> map){
////    public Note newNote(String title, String body, String[] tags){
//        //loop through tag array
//        //save note.
//        // save tags to note id
//        //read things out of map and set to the object
//        System.out.println(map);
//        Note newNote = noteRepository.save(new Note((String)map.get("title"),(String)map.get("body")));
//
////        for (String tag:tags) {
//////            tagRepository.save(new Tag(tag));
////            newNote.getTags().add(new Tag(tag));
////        }
//////        newNote.setTags(newNote.getTags());
////        noteRepository.save(newNote);
//        return null;
//    }
    @PostMapping("/notes")
    public Note addNote(@RequestBody NoteCommand input){
        List<Tag> tags = new ArrayList<Tag>();

        for (String tag:input.tags) {
            tags.add(new Tag(tag));
       }
        return noteRepository.save(new Note(input.title, input.body, tags));

    }

    @DeleteMapping("/notes/{id}")
    public void deleteNote(@PathVariable String id){
        noteRepository.deleteById(Long.parseLong(id));
    }

    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable String id, @RequestBody NoteCommand input){
        Optional<Note> dbNote = noteRepository.findById(Long.parseLong(id));
        List<Tag> tags = new ArrayList<>();
        if (dbNote.isPresent()){
            Note note = dbNote.get();
            for (String tag:input.tags) {
                tags.add(new Tag(tag));
            }
            note.setTags(tags);
            note.setTitle(input.title);
            note.setBody(input.body);
            return noteRepository.save(note);
        }
        else{
            return null;
        }
    }

    @PutMapping("/notes/{id}/addNotebook")
    public Note addNotebook (@PathVariable String id, @RequestBody NotebookCommand input){
        Optional<Note> dbNote = noteRepository.findById(Long.parseLong(id));
        if (dbNote.isPresent()){
            Note note = dbNote.get();
            note.setNotebook(new Notebook(input.notebook));
            return noteRepository.save(note);
        }
        else{
            return null;
        }
    }

    //HELPER CLASS
    public static class NoteCommand {
        @JsonProperty
        String title;
        @JsonProperty
        String body;
        @JsonProperty
        ArrayList<String> tags;
    }
    public static class NotebookCommand {
        @JsonProperty
        String notebook;
    }
//array of tags needs to be converted to array of strings and past
}
