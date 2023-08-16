package com.example.demo2;

import java.util.List;

public class User {
    private String name;
    private String password;
    private List<Note> noteList;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addNote (Note note) {
        noteList.add(note);
    }

    public List<Note> getNoteList () {
        return this.noteList;
    }
}
