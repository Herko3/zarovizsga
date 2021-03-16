package hu.nive.ujratervezes.zarovizsga.workhours;

import java.time.LocalDate;

public class WorkSheet {

    private String name;
    private int hours;
    private LocalDate date;

    public WorkSheet(String name, int hours, LocalDate date) {
        this.name = name;
        this.hours = hours;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public LocalDate getDate() {
        return date;
    }
}
