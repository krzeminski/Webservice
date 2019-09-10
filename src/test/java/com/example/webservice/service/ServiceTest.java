package com.example.webservice.service;

import com.example.webservice.model.Note;
import com.example.webservice.model.NoteContent;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceTest {
    @Autowired
    NotesService notesService;

    @Test
    public void testCreateReadDelete(){
        NoteContent noteContent = new NoteContent("service test", "zzz");
        Note note = new Note(noteContent);

        notesService.save(note);

        Iterable<Note> notes = notesService.findAll();
        Assertions.assertThat(notes).extracting(Note::getTitle).containsOnly("service test");

        notesService.deleteAll();
        Assertions.assertThat(notesService.findAll()).isEmpty();

    }


}
