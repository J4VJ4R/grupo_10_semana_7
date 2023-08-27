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
    public void add(Doctor doctor) throws SaveFailedException {
        MemoryStorage.addDoctor(doctor);
    }

    @Override
    public Promise<Stream<Doctor>> getAll() {
        return Promise.resolve(MemoryStorage.getAllDoctors().stream());
    }

    public Doctor getById(int id) throws NotFoundException {
        return MemoryStorage.getAllDoctors()
                .stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException());
    }
}
