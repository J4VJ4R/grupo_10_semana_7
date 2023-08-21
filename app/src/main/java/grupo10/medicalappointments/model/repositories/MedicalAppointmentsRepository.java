package grupo10.medicalappointments.model.repositories;

import grupo10.medicalappointments.model.entities.MedicalAppointment;

public interface MedicalAppointmentsRepository {
    public void add(MedicalAppointment medicalAppointment);

    public MedicalAppointment getNextByIdentificationNumber(String id);
}
