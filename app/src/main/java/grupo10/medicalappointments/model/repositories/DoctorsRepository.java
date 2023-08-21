package grupo10.medicalappointments.model.repositories;

import java.util.Collection;
import java.util.stream.Stream;

import grupo10.medicalappointments.model.entities.Doctor;
import grupo10.medicalappointments.model.exceptions.NotFoundException;
import grupo10.medicalappointments.model.exceptions.SaveFailedException;

public interface DoctorsRepository {
    public void add(Doctor doctor) throws SaveFailedException;

    public Stream<Doctor> getAll();

    public Doctor getById(int id) throws NotFoundException;
}
