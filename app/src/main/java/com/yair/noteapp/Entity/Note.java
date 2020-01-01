package com.yair.noteapp.Entity;

/**
 * Created by Yair on 19/12/2019.
 */

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    private int priority;

    private Status status;

    @Ignore
    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = Status.ACTIVE;
    }

    public Note(String title, String description, int priority, Status status) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

}
