package de.htwberlin.webtech.web;

import de.htwberlin.webtech.service.NoteService;
import de.htwberlin.webtech.web.api.Note;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteRestController.class)
class NoteRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Test
    @DisplayName("should return found note from note service")
    void should_return_found_note_from_note_service() throws Exception {
        //given
        var ts1 = new Timestamp(1216543272);
        var ts2 = new Timestamp(1210933154);
        var note = List.of(
                new Note(4, "Urlaubsziele", "Vietnam, Korea, Japan", false, ts1),
                new Note(5, "Webtech Projekt", "Meilenstein 3", false, ts2)
        );
        doReturn(note).when(noteService).findAll();

        //when
        mockMvc.perform(get("/api/v1/notes"))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(4))
                .andExpect(jsonPath("$[0].title").value("Urlaubsziele"))
                .andExpect(jsonPath("$[0].body").value("Vietnam, Korea, Japan"))
                .andExpect(jsonPath("$[0].done").value(false))
                .andExpect(jsonPath("$[0].createdtime").value(ts1.getTime()))
                .andExpect(jsonPath("$[1].id").value(5))
                .andExpect(jsonPath("$[1].title").value("Webtech Projekt"))
                .andExpect(jsonPath("$[1].body").value("Meilenstein 3"))
                .andExpect(jsonPath("$[1].done").value(false))
                .andExpect(jsonPath("$[1].createdtime").value(ts2.getTime()));
    }

    @Test
    @DisplayName("should return 404 if note is not found")
    void should_return_404_if_note_is_not_found() throws Exception{
        //given
        doReturn(null).when(noteService).findById(anyLong());

        //when
        mockMvc.perform(get("/api/v1/notes/123"))
                //then
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should return 201 http status and Location header when creating a note")
    void should_return_201_http_status_and_location_header_when_creating_a_note() throws Exception {
        // given
        var ts = new Timestamp(1210933154);
        String noteToCreateAsJson = "{\"title\": \"first Note\", \"body\":\"this contains info of first note\", \"done\":\"false\"}";
        var note = new Note(123, null, null, false, ts);
        doReturn(note).when(noteService).create(any());

        // when
        mockMvc.perform(
                        post("/api/v1/notes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(noteToCreateAsJson)
                )
                // then
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", Matchers.equalTo(("/api/v1/notes/" + note.getId()))))
                .andExpect(header().string("Location", Matchers.containsString(Long.toString(note.getId()))));

    }


    @Test
    @DisplayName("should validate create note request")
    void should_validate_create_note_request() throws Exception {
        // given
        String noteToCreateAsJson = "{\"title\": \"first Note\", \"body\":\"\", \"done\": true}";

        // when
        mockMvc.perform(
                        post("/api/v1/notes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(noteToCreateAsJson)
                )
                // then
                .andExpect(status().isBadRequest());
    }
}
