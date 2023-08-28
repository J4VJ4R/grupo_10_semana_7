package grupo10.medicalappointments.model.repositories.memory;

import java.util.Comparator;

import grupo10.medicalappointments.model.Promise;
import grupo10.medicalappointments.model.entities.MedicalAppointment;
import grupo10.medicalappointments.model.exceptions.NotFoundException;
import grupo10.medicalappointments.model.repositories.MedicalAppointmentsRepository;

public class MedicalAppointmentsRepository_Memory implements MedicalAppointmentsRepository {
    @Override
    public Promise add(MedicalAppointment medicalAppointment) {
        MemoryStorage.addMedicalAppointment(medicalAppointment);
        return Promise.resolve(null);
    }

    @Override
    public Promise<MedicalAppointment> getNextByIdentificationNumber(String id) {

        MedicalAppointment appointment = MemoryStorage.getAllMedicalAppointments()
                .stream()
                .filter(a -> a.getIdentification().equals(id))
                .sorted(Comparator.comparing(MedicalAppointment::getDate))
                .findAny()
                .orElse(null);

        return appointment != null
                ? Promise.resolve(appointment)
                : Promise.reject(new NotFoundException());
    }
}
