package com.example.webservice;

import com.example.webservice.model.Note;
import com.example.webservice.model.NoteContent;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class SystemTests {
    @Test
    public void testCreateReadDelete(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/notes";

        NoteContent noteContent = new NoteContent("note for testing", "content of the tested note");
        Note note = new Note(noteContent);
        ResponseEntity<Note> entity = restTemplate.postForEntity(url, note, Note.class);

        Note[] notes = restTemplate.getForObject(url, Note[].class);
        Assertions.assertThat(notes).extracting(Note::getTitle).containsOnly("note for testing");

        restTemplate.delete(url + "/" + entity.getBody().getId());
        Assertions.assertThat(restTemplate.getForObject(url, Note[].class)).isEmpty();
    }
}
