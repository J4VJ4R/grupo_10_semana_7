package grupo10.medicalappointments.model.repositories;

import java.util.Collection;
import java.util.stream.Stream;

import grupo10.medicalappointments.model.Promise;
import grupo10.medicalappointments.model.entities.Doctor;
import grupo10.medicalappointments.model.exceptions.NotFoundException;
import grupo10.medicalappointments.model.exceptions.SaveFailedException;

public interface DoctorsRepository {
    public Promise add(Doctor doctor);

    public Promise<Stream<Doctor>> getAll();

    public Promise<Doctor> getById(int id);
}
