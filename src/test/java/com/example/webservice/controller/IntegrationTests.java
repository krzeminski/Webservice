package com.example.webservice.controller;

import com.example.webservice.model.Note;
import com.example.webservice.model.NoteContent;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTests {
    @Autowired
    NotesController notesController;
    @Test
    public void testCreateReadDelete(){
        NoteContent noteContent = new NoteContent("note for integration test", "some text");
        Note noteResult = new NotesController().create(noteContent);

        Iterable<Note> notes = notesController.read();
        Assertions.assertThat(notes).first().hasFieldOrPropertyWithValue("title","note for integration test");

        notesController.delete(noteResult.getId());
        Assertions.assertThat(notesController.read()).isEmpty();
    }

}
