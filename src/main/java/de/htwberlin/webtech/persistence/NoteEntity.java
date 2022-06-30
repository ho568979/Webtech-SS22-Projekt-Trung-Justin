package de.htwberlin.webtech.persistence;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "notes")
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "is_done")
    private boolean done;

    @CreationTimestamp
    @Column(name = "created_time")
    private LocalDateTime createdtime;

    public NoteEntity(String title, String body, boolean done) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.done = done;
    }

    protected NoteEntity() {
    }

    public long getId() {
        return id;
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

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDateTime getCreatedtime() {
        return createdtime;
    }

}
