package grupo10.medicalappointments.model.repositories.memory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import grupo10.medicalappointments.model.entities.Doctor;
import grupo10.medicalappointments.model.entities.MedicalAppointment;

final class MemoryStorage {
    private  static  int lastDoctorId = 0;
    private  static  int lastAppointmentId = 0;
    private  static  final List<Doctor> DOCTOR_LIST = new ArrayList<>();
    private  static  final List<MedicalAppointment> MEDICAL_APPOINTMENT_LIST = new ArrayList<>();

    public  static void addDoctor(Doctor doctor){
        doctor.setId(lastDoctorId++);
        DOCTOR_LIST.add(doctor);
    }

    public static Collection<Doctor> getAllDoctors() {
        return DOCTOR_LIST;
    }

    public  static void addMedicalAppointment(MedicalAppointment appointment) {
        appointment.setId(lastAppointmentId);
        MEDICAL_APPOINTMENT_LIST.add(appointment);
    }
}
