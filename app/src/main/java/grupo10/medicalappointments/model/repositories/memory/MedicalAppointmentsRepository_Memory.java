package grupo10.medicalappointments.model.repositories.memory;

import grupo10.medicalappointments.model.entities.MedicalAppointment;
import grupo10.medicalappointments.model.repositories.MedicalAppointmentsRepository;

public class MedicalAppointmentsRepository_Memory implements MedicalAppointmentsRepository {
    @Override
    public void add(MedicalAppointment medicalAppointment) {
        MemoryStorage.addMedicalAppointment(medicalAppointment);
    }
}
