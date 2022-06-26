package de.htwberlin.webtech.web;

import de.htwberlin.webtech.service.NoteService;
import de.htwberlin.webtech.web.api.Note;
import de.htwberlin.webtech.web.api.NoteManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class NoteRestController {

   private final NoteService noteService;

    public NoteRestController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping(path = "/api/v1/notes")
    public ResponseEntity<List<Note>> fetchNotes() {
        return ResponseEntity.ok(noteService.findAll());
    }

    @GetMapping(path = "/api/v1/notes/{id}")
    public ResponseEntity<Note> fetchNoteById(@PathVariable Long id) {
        var note = noteService.findById(id);
        return note != null? ResponseEntity.ok(note) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/notes")
    public ResponseEntity<Void> createNote(@Valid @RequestBody NoteManipulationRequest request) throws URISyntaxException {
        var note = noteService.create(request);
        URI uri = new URI("/api/v1/notes/" + note.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/api/v1/notes/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody NoteManipulationRequest request) {
        var note = noteService.update(id, request);
        return note != null? ResponseEntity.ok(note) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        boolean successful = noteService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
