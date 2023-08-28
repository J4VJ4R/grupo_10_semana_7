package grupo10.medicalappointments.model.repositories;

import grupo10.medicalappointments.model.Promise;
import grupo10.medicalappointments.model.entities.MedicalAppointment;

public interface MedicalAppointmentsRepository {
    public Promise add(MedicalAppointment medicalAppointment);

    public Promise<MedicalAppointment> getNextByIdentificationNumber(String id);
}
