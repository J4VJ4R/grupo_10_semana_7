package grupo10.medicalappointments.model.repositories.memory;

import java.util.stream.Stream;

import grupo10.medicalappointments.model.Promise;
import grupo10.medicalappointments.model.entities.Doctor;
import grupo10.medicalappointments.model.exceptions.NotFoundException;
import grupo10.medicalappointments.model.exceptions.SaveFailedException;
import grupo10.medicalappointments.model.repositories.DoctorsRepository;

public class DoctorsRepository_Memory implements DoctorsRepository {
    private static boolean initialDoctorsSet = false;

    public DoctorsRepository_Memory() {
        if(!initialDoctorsSet){
            MemoryStorage.addDoctor(new Doctor("Dr Goku", "Médico General"));
            MemoryStorage.addDoctor(new Doctor("Dr Vegeta", "Proctólogo"));
            MemoryStorage.addDoctor(new Doctor("Dr Krilin", "Dermatólogo"));
            initialDoctorsSet = true;
        }
    }

    @Override
    public Promise add(Doctor doctor) {
        MemoryStorage.addDoctor(doctor);
        return Promise.resolve(doctor);
    }

    @Override
    public Promise<Stream<Doctor>> getAll() {
        return Promise.resolve(MemoryStorage.getAllDoctors().stream());
    }

    public Promise<Doctor> getById(int id) {
        Doctor foundDoctor = MemoryStorage.getAllDoctors()
                .stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .orElse(null);

        return foundDoctor != null
                ? Promise.resolve(foundDoctor)
                : Promise.reject(new NotFoundException());
    }
}
