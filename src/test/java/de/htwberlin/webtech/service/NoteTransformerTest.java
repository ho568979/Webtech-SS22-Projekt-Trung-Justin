package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.NoteEntity;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.doReturn;

public class NoteTransformerTest implements WithAssertions {

    private final NoteTransformer underTest = new NoteTransformer();

    @Test
    @DisplayName("should transform NoteEntity to Note")
    void should_transform_note_entity_to_note(){
        //given
        var noteEntity = Mockito.mock(NoteEntity.class);
        doReturn(111L).when(noteEntity).getId();
        doReturn("this is a title").when(noteEntity).getTitle();
        doReturn("this is a body text").when(noteEntity).getBody();
        doReturn(false).when(noteEntity).getDone();

        //when
        var result = underTest.transformEntity(noteEntity);

        //then
        assertThat(result.getId()).isEqualTo(111);
        assertThat(result.getTitle()).isEqualTo("this is a title");
        assertThat(result.getBody()).isEqualTo("this is a body text");
        assertThat(result.isDone()).isEqualTo(false);
    }
}
