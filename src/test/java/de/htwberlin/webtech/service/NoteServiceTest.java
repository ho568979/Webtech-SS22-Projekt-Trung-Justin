package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.NoteRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest implements WithAssertions{

    @Mock
    private NoteRepository repository;

    @InjectMocks
    private NoteService underTest;

    @Test
    @DisplayName("should return true if delete was successful")
    void should_return_true_if_delete_was_successful(){
        //given
        Long givenId = 111L;
        doReturn(true).when(repository).existsById(givenId);

        //when
        boolean result = underTest.deleteById(givenId);

        //then
        verify(repository).deleteById(givenId);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("should return false if note to delete does not exist")
    void should_return_false_if_note_to_delete_does_not_exist() {
        //given
        Long givenId = 111L;
        doReturn(false).when(repository).existsById(givenId);

        //when
        boolean result = underTest.deleteById(givenId);

        //then
        verifyNoMoreInteractions(repository);
        assertThat(result).isFalse();
    }
}

