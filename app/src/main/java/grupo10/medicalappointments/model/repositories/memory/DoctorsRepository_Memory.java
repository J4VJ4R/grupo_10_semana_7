package grupo10.medicalappointments.model.repositories.memory;

import grupo10.medicalappointments.model.entities.Doctor;
import grupo10.medicalappointments.model.exceptions.SaveFailedException;
import grupo10.medicalappointments.model.repositories.DoctorsRepository;

public class DoctorsRepository_Memory implements DoctorsRepository {
    @Override
    public void add(Doctor doctor) throws SaveFailedException {
        MemoryStorage.addDoctor(doctor);
    }
}
