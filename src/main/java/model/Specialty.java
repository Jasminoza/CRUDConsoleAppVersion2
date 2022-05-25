package model;

import java.io.Serializable;

public class Specialty implements Serializable {
    String specialtyName;

    public Specialty(String specialtyName) {
        this.specialtyName = specialtyName;
    }
}
