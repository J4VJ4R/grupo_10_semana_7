package com.example.app_50510;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_50510.setup.ServiceLocator;

import grupo10.medicalappointments.model.entities.Doctor;
import grupo10.medicalappointments.model.exceptions.SaveFailedException;
import grupo10.medicalappointments.model.repositories.DoctorsRepository;

public class DoctorActivity extends AppCompatActivity {

    private EditText txtName;
    private EditText txtId;
    private EditText txtSpeciality;

    private DoctorsRepository doctorsRepository = ServiceLocator.getInstance().getDoctorsRepository();
    private Doctor doctor;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        inicializar_componentes();

        btnRegister.setOnClickListener(v -> {
            stablishUser();

            try {
                doctorsRepository.add(doctor);
                clearComponents();

                Toast.makeText(DoctorActivity.this, "Doctor saved successfully", Toast.LENGTH_SHORT).show();
            } catch (SaveFailedException e) {
                Toast.makeText(DoctorActivity.this, "Failed to save doctor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearComponents(){
        txtName.setText("");
        txtSpeciality.setText("");
        doctor = new Doctor();

        txtName.requestFocus();
    }

    private void inicializar_componentes(){
        txtName = findViewById(R.id.txtName);
        txtSpeciality = findViewById(R.id.txtSpeciality);
        btnRegister = findViewById(R.id.btnRegister);
        doctor = new Doctor();
    }

    private  void stablishUser(){
        doctor.setName(txtName.getText().toString());
        doctor.setSpecialty(txtSpeciality.getText().toString());
    }
}