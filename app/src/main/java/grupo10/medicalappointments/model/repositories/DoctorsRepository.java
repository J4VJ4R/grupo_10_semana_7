package grupo10.medicalappointments.model.repositories;

import grupo10.medicalappointments.model.entities.Doctor;
import grupo10.medicalappointments.model.exceptions.SaveFailedException;

public interface DoctorsRepository {
    public void add(Doctor doctor) throws SaveFailedException;
}
