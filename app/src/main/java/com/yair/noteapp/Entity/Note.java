package com.yair.noteapp.Entity;

/**
 * Created by Yair on 19/12/2019.
 */

import android.location.Location;

import com.google.firebase.database.Exclude;

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

    private Location location;

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

        this.location = new Location("");
        this.location.setLongitude(32);
        this.location.setLatitude(35);
    }

    //region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setLatitude(double latitude){
        location.setLatitude(latitude);
    }

    public void setLongitude(double longitude){
        location.setLongitude(longitude);
    }

    //endregion

    //region Getters
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
    @Exclude
    public Location getLocation() {
        return location;
    }

    public Double getLatitude(){
        return location.getLatitude();
    }

    public Double getLongitude(){
        return location.getLongitude();
    }
//endregion
}
