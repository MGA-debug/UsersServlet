package ru.appline.logic;

public class User {

    private String name;
    private String surName;
    private double salary;

    public User(String name, String surName, double salary) {
        this.name = name;
        this.surName = surName;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public static void updateInfo(int id, String name, String surName, double salary) {
        User user = Model.getInstance().getFromList().get(id);
        user.setName(name);
        user.setSurName(surName);
        user.setSalary(salary);
    }
}
