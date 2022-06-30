package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.NoteEntity;
import de.htwberlin.webtech.persistence.NoteRepository;
import de.htwberlin.webtech.web.api.Note;
import de.htwberlin.webtech.web.api.NoteManipulationRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAll() {
        List<NoteEntity> notes = noteRepository.findAll();
        return notes.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Note findById(Long id) {
        var noteEntity = noteRepository.findById(id);
        return noteEntity.map(this::transformEntity).orElse(null);
    }
    public Note create(NoteManipulationRequest request) {
        var noteEntity = new NoteEntity(request.getTitle(), request.getBody(), request.getDone());
        noteEntity = noteRepository.save(noteEntity);
        return transformEntity(noteEntity);
    }

    public Note update(Long id, NoteManipulationRequest request) {
        var noteEntityOptional = noteRepository.findById(id);
        if (noteEntityOptional.isEmpty()) {
            return null;
        }

        var noteEntity = noteEntityOptional.get();
        noteEntity.setTitle(request.getTitle());
        noteEntity.setBody(request.getBody());
        noteEntity.setDone(request.getDone());
        noteEntity = noteRepository.save(noteEntity);

        return transformEntity(noteEntity);
    }

    public boolean deleteById(Long id) {
        if (!noteRepository.existsById(id)) {
            return false;
        }

        noteRepository.deleteById(id);
        return true;
    }
    private Note transformEntity(NoteEntity noteEntity) {
        return new Note(
                noteEntity.getId(),
                noteEntity.getTitle(),
                noteEntity.getBody(),
                noteEntity.getDone(),
                Timestamp.valueOf(noteEntity.getCreatedtime())
        );
    }
}
