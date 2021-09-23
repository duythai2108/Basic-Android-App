package com.example.lab3_sqllite.model;

import java.io.Serializable;

public class ClassRoom implements Serializable {
    private String id;
    private String name;
    private String teacher;
    private int amount;

    public ClassRoom() {
    }

    public ClassRoom(String id, String name, String teacher, int amount) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public int getAmount() {
        return amount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Ten lop : " + this.name + " (Si so :" + this.amount + ") " +
                System.getProperty("line.separator")
                + " Giang vien : " + this.teacher + " - Ma lop : " + this.id;
    }
}
