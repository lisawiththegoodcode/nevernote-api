package com.nevernote.api.nevernoteapi.controllers;

import com.nevernote.api.nevernoteapi.models.Notebook;
import com.nevernote.api.nevernoteapi.repositories.NotebookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class NotebookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotebookRepository notebookRepository;

    @Test
    void getNotebookByIdReturnsNotebook() throws Exception{
        Notebook notebook = new Notebook("test1");
        notebook.setId((long) 1);

        given(notebookRepository.findById((long) 1).isPresent());

        this.mockMvc.perform(get("/notes"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':'1',name':'test1','createdAt':'null','lastModified':'null','notes':[]}"));

    }
}


