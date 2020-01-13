package com.wadektech.eventshub.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "professional_events",  indices = @Index(value = {"location"}, unique = true))
public class ProfessionalEvents {
    @PrimaryKey(autoGenerate = true)
    private int id ;
    private String title ;
    private String shortDesc ;
    private long date ;
    private String location ;
    private int entryFees ;

    @Ignore
    public ProfessionalEvents(int id, String title, String shortDesc, long date, String location, int entryFees) {
        this.id = id;
        this.title = title;
        this.shortDesc = shortDesc;
        this.date = date;
        this.location = location;
        this.entryFees = entryFees;
    }

    public ProfessionalEvents(String title, String shortDesc, long date, String location, int entryFees) {
        this.title = title;
        this.shortDesc = shortDesc;
        this.date = date;
        this.location = location;
        this.entryFees = entryFees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getEntryFees() {
        return entryFees;
    }

    public void setEntryFees(int entryFees) {
        this.entryFees = entryFees;
    }
}
