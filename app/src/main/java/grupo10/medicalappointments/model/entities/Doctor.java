package grupo10.medicalappointments.model.entities;

import androidx.annotation.NonNull;

public class Doctor {
    private int id;
    private String name;
    private String specialty;

    public Doctor() {
    }

    public Doctor(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " (" + specialty + ")";
    }
}
