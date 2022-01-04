package com.demo3.fxml_3;

import java.time.LocalDate;

public class StudentSer implements java.io.Serializable{
    String name, surname;
    LocalDate dateOfBith;
    int pts, ID;

    public StudentSer(String name, String surname, LocalDate dateOfBith, int pts, int ID) {
        this.name = name;
        this.surname = surname;
        this.dateOfBith = dateOfBith;
        this.pts = pts;
        this.ID = ID;
    }
}
