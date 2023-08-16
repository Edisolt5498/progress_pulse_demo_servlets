package com.example.demo2;

public class Category {
    private String name;
    private Colors colors;

    public Category(String name, Colors colors) {
        this.name = name;
        if (colors == null) {
            this.colors = Colors.RED;
        } else {
            this.colors = colors;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Colors getColors() {
        return colors;
    }

    public void setColors(Colors colors) {
        this.colors = colors;
    }
}
