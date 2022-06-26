package de.htwberlin.webtech.web.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NoteManipulationRequest {

    @Size(min = 3, message = "Please provide a first name with 3 characters or more.")
    private String title;

    @NotBlank(message = "Please type in your note")
    private String body;

//    @Pattern(
//            regexp = "done|not done",
//            message = "Please check if it's done"
//    )
    private boolean done;

    public NoteManipulationRequest(String title, String body, boolean done) {
        this.title = title;
        this.body = body;
        this.done = done;
    }

    public NoteManipulationRequest(){}
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
