package com.nevernote.api.nevernoteapi.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nevernote.api.nevernoteapi.models.Note;
import com.nevernote.api.nevernoteapi.models.Notebook;
import com.nevernote.api.nevernoteapi.repositories.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class NotebookController {

    @Autowired
    NotebookRepository notebookRepository;

    @RequestMapping("/")
    public StringBuilder index() {
        StringBuilder indexMessage = new StringBuilder("Welcome to Nevernote! ");

        if (getNotebooks().size() == 0) {
            indexMessage.append("You have 0 notebooks currently.");
        }
        else {
            indexMessage.append("You have ");
            indexMessage.append(getNotebooks().size());
            indexMessage.append(" notebooks currently.");

            for (Notebook n:getNotebooks()) {
                indexMessage.append("\n ");
                indexMessage.append("\n ");
                indexMessage.append("Notebook Name: ");
                indexMessage.append(n.getName());
                indexMessage.append("\n ");
                indexMessage.append("Number of Notes: ");
                indexMessage.append(n.getNotes().size());
            }
        }

        return indexMessage;
    }

    @GetMapping("/notebooks")
    public List<Notebook> getAllNotebooks() {
        return getNotebooks();
    }

    @GetMapping("/notebooks/{id}")
    public Notebook getNotebookById (@PathVariable Long id){
        Optional<Notebook> dbNotebook = notebookRepository.findById(id);

        if(dbNotebook.isPresent()) {
            return dbNotebook.get();
        }
        else {
            return null;
        }
    }

    @PostMapping("/notebooks")
    public Notebook addNotebook (@RequestBody NotebookCommand input){
        return notebookRepository.save(new Notebook(input.name));
    }

    @DeleteMapping("/notebooks/{id}")
    public void deleteNote(@PathVariable String id){
        notebookRepository.deleteById(Long.parseLong(id));
    }

    @PutMapping("notebooks/{id}/addNote")
    public Notebook addNote (@PathVariable String id, @RequestBody NoteCommand input){
        Optional<Notebook> dbNotebook = notebookRepository.findById(Long.parseLong(id));
        List<Note> notes = new ArrayList<>();

        if (dbNotebook.isPresent()){
            Notebook notebook = dbNotebook.get();
            notes.add(new Note(input.title, input.body));
            notebook.setNotes(notes);
            return notebookRepository.save(notebook);
        }
        else{
            return null;
        }
    }
    //HELPER CLASS
    public static class NotebookCommand {
        @JsonProperty
        String name;
    }

    public static class NoteCommand {
        @JsonProperty
        String title;
        @JsonProperty
        String body;
    }

    //HELPER METHODS
    public List<Notebook> getNotebooks(){
        return (List<Notebook>)notebookRepository.findAll();
    }

}
