package com.example.app_50510;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_50510.setup.ServiceLocator;

import java.text.SimpleDateFormat;
import java.util.Locale;

import grupo10.medicalappointments.model.entities.Doctor;
import grupo10.medicalappointments.model.entities.MedicalAppointment;
import grupo10.medicalappointments.model.exceptions.NotFoundException;
import grupo10.medicalappointments.model.repositories.DoctorsRepository;
import grupo10.medicalappointments.model.repositories.MedicalAppointmentsRepository;

public class ConsultActivity extends AppCompatActivity {
    private TextView txtPatientFullName;
    private TextView txtDoctorName;
    private TextView txtDoctorSpeciality;
    private TextView txtAppointmentDate;

    private EditText txtPatientIdentification;

    private final DoctorsRepository doctorsRepository = ServiceLocator.getInstance().getDoctorsRepository();

    private final MedicalAppointmentsRepository medicalAppointmentsRepository = ServiceLocator.getInstance().getMedicalAppointmentsRepository();

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");

    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        inicializar_componentes();

        btnRegister.setOnClickListener(v -> {
            try {
                clearAppointment();
                String identification = txtPatientIdentification.getText().toString();
                MedicalAppointment appointment = medicalAppointmentsRepository.getNextByIdentificationNumber(identification);

                if (appointment != null) {
                    renderAppointment(appointment);
                } else {
                    Toast.makeText(
                                    ConsultActivity.this,
                                    "No se encontró ninguna cita para '" + identification + "'",
                                    Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(ConsultActivity.this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void inicializar_componentes() {
        txtPatientFullName = findViewById(R.id.txtPatientFullName);
        txtDoctorName = findViewById(R.id.txtDoctorName);
        txtDoctorSpeciality = findViewById(R.id.txtDoctorSpeciality);
        txtAppointmentDate = findViewById(R.id.txtAppointmentDate);
        txtPatientIdentification = findViewById(R.id.txtPatientIdentification);

        btnRegister = findViewById(R.id.btnRegister);
    }

    private void renderAppointment(MedicalAppointment appointment) {
        txtPatientFullName.setText(appointment.getName() + " " + appointment.getLastname());
        doctorsRepository.getById(appointment.getDoctor())
                .then(doctor -> {
                    doctorsRepository.getById(appointment.getDoctor());
                    txtDoctorName.setText(doctor.getName());
                    txtDoctorSpeciality.setText(doctor.getSpecialty());
                })
                .catched(e -> {
                    txtDoctorName.setText("N/A");
                    txtDoctorSpeciality.setText("N/A");
                });

        txtAppointmentDate.setText(dateFormat.format(appointment.getDate()));
    }

    private void clearAppointment() {
        txtPatientFullName.setText("");
        txtDoctorName.setText("");
        txtDoctorSpeciality.setText("");
        txtAppointmentDate.setText("");
    }

}