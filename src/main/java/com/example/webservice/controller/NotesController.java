package com.example.webservice.controller;

import com.example.webservice.model.NoteContent;
import com.example.webservice.service.NotesService;
import com.example.webservice.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import util.FieldErrorMessage;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class NotesController {

    @Autowired
    NotesService notesService;

    @GetMapping("/notes")
    Iterable<Note> read(){
        return notesService.findAll();
    }

    @PostMapping("/notes")
    Note create(@Valid @RequestBody NoteContent noteContent) {
        Note note = new Note(noteContent);
        return notesService.save(note);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage>exceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
        return fieldErrorMessages;
    }


    @PutMapping("/notes")
    ResponseEntity<Note> update(@RequestBody Note note){
        if(notesService.findById(note.getId()).isPresent()) {
            return new ResponseEntity(notesService.save(note), HttpStatus.OK);
        }else{
            return new ResponseEntity(note, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/notes/{id}")
    void delete(@PathVariable Integer id){
        notesService.deleteById(id);
    }

    @GetMapping("notes/{id}")
    Optional<Note> findById(@PathVariable Integer id){
        return notesService.findById(id);
    }

    @GetMapping("notes/search")
    Iterable<Note> findByQuery(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "content", required = false) String content)
    {
        if (title != null && content !=null)
            return notesService.findByTitleAndContent(title, content);
        else if (title != null)
            return notesService.findByTitle(title);
        else if (content != null)
            return notesService.findByContent(content);
        else
            return notesService.findAll();
    }


    @PatchMapping("/notes")
    ResponseEntity updateVersion(@RequestBody Note note) {
        if (notesService.findById(note.getId()).isPresent()) {
            note.incrementVersion();
            return new ResponseEntity(notesService.save(note), HttpStatus.OK);
        } else {
            return new ResponseEntity(note, HttpStatus.BAD_REQUEST);
        }
    }
}
