package grupo10.medicalappointments.model.repositories.memory;

import java.util.Comparator;

import grupo10.medicalappointments.model.entities.MedicalAppointment;
import grupo10.medicalappointments.model.repositories.MedicalAppointmentsRepository;

public class MedicalAppointmentsRepository_Memory implements MedicalAppointmentsRepository {
    @Override
    public void add(MedicalAppointment medicalAppointment) {
        MemoryStorage.addMedicalAppointment(medicalAppointment);
    }

    @Override
    public MedicalAppointment getNextByIdentificationNumber(String id) {
        return MemoryStorage.getAllMedicalAppointments()
                .stream()
                .filter(a -> a.getIdentification().equals(id))
                .sorted(Comparator.comparing(MedicalAppointment::getDate))
                .findAny()
                .orElse(null);
    }
}
