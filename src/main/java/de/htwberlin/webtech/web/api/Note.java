package de.htwberlin.webtech.web.api;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Note {

    private long id;
    private String title;
    private String body;
    private boolean done;
    private Timestamp createdtime;

    public Note(long id, String title, String body, boolean done, Timestamp createdtime) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.done = done;
        this.createdtime = createdtime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Timestamp getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Timestamp createdtime) {
        this.createdtime = createdtime;
    }
}
