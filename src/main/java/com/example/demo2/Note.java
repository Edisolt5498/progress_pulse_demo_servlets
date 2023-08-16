package com.example.demo2;

public class Note {
    private String topic;
    private String note;
    private Category category;

    public Note(String topic, String note, Category category) {
        this.topic = topic;
        this.note = note;
        this.category = category;
    }
    public Note(String topic, String note) {
        this.topic = topic;
        this.note = note;
        this.category = new Category("Default category", Colors.RED);
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
