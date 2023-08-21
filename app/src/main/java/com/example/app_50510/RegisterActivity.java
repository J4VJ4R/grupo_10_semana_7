package com.example.app_50510;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.app_50510.setup.ServiceLocator;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.stream.Stream;

import grupo10.medicalappointments.model.entities.Doctor;
import grupo10.medicalappointments.model.entities.MedicalAppointment;
import grupo10.medicalappointments.model.repositories.DoctorsRepository;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText txtName;
    private EditText txtLastname;
    private EditText txtIdentification;
    private EditText txtPhone;
    private EditText txtDate;

    private Spinner doctorSpinner;

    private MedicalAppointment objUser;

    private Doctor selectedDoctor;

    private Button btnRegister;

    private DoctorsRepository doctorsRepository = ServiceLocator.getInstance().getDoctorsRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        inicializar_componentes();

        Doctor[] doctors = doctorsRepository.getAll().toArray(Doctor[]::new);

        ArrayAdapter<Doctor> doctorArrayAdapter = new ArrayAdapter<>(
                this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                doctors
        );
        doctorArrayAdapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item);

        doctorSpinner.setAdapter(doctorArrayAdapter);
        doctorSpinner.setOnItemSelectedListener(this);

        btnRegister.setOnClickListener(v -> {
            try {
                stablishUser();
                Toast.makeText(RegisterActivity.this, "Succesfully connection", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(RegisterActivity.this, "Fail connection", Toast.LENGTH_SHORT).show();
                System.out.println(e.getMessage());
            }
        });
        btnRegister.setEnabled(selectedDoctor != null);
    }

    private void inicializar_componentes() {
        txtName = findViewById(R.id.txtName);
        txtLastname = findViewById(R.id.txtLastname);
        txtIdentification = findViewById(R.id.txtIdentification);
        txtPhone = findViewById(R.id.txtPhone);
        txtDate = findViewById(R.id.txtDate);
        btnRegister = findViewById(R.id.btnRegister);
        doctorSpinner = findViewById(R.id.spinnerDoctor);

        objUser = new MedicalAppointment();
    }

    private void stablishUser() {
        objUser.setName(txtName.getText().toString());
        objUser.setLastname(txtLastname.getText().toString());
        objUser.setIdentification(txtIdentification.toString());
        objUser.setPhone(txtPhone.getText().toString());
        objUser.setDate(String.valueOf(Date.valueOf(txtDate.getText().toString())));
        objUser.setDoctor(selectedDoctor.getId());
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedDoctor = (Doctor) adapterView.getSelectedItem();
        btnRegister.setEnabled(selectedDoctor != null);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        selectedDoctor = null;
        btnRegister.setEnabled(selectedDoctor != null);
    }
}