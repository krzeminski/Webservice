package com.example.webservice.service;

import com.example.webservice.model.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesService extends CrudRepository<Note, Integer> {
    Iterable<Note> findByTitleAndContent(String title, String content);
    Iterable<Note> findByTitle(String title);
    Iterable<Note> findByContent(String content);

}
