package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.NoteEntity;
import de.htwberlin.webtech.web.api.Note;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class NoteTransformer {
        public Note transformEntity(NoteEntity noteEntity) {
            return new Note(
                    noteEntity.getId(),
                    noteEntity.getTitle(),
                    noteEntity.getBody(),
                    noteEntity.getDone()
            );
        }
}
