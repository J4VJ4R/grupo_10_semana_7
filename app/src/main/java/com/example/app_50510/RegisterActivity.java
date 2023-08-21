package com.example.app_50510;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.app_50510.setup.ServiceLocator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import grupo10.medicalappointments.model.entities.Doctor;
import grupo10.medicalappointments.model.entities.MedicalAppointment;
import grupo10.medicalappointments.model.repositories.DoctorsRepository;
import grupo10.medicalappointments.model.repositories.MedicalAppointmentsRepository;

public class RegisterActivity
        extends AppCompatActivity
        implements
        AdapterView.OnItemSelectedListener,
        TextWatcher {

    private EditText txtName;
    private EditText txtLastname;
    private EditText txtIdentification;
    private EditText txtPhone;
    private EditText txtDate;

    private Spinner doctorSpinner;

    private MedicalAppointment medicalAppointment;

    private Doctor selectedDoctor;

    private Date selectedDate;

    private Button btnRegister;

    private DoctorsRepository doctorsRepository = ServiceLocator.getInstance().getDoctorsRepository();
    private MedicalAppointmentsRepository medicalAppointmentsRepository = ServiceLocator.getInstance().getMedicalAppointmentsRepository();

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

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
                medicalAppointmentsRepository.add(medicalAppointment);
                clearComponents();

                Toast.makeText(RegisterActivity.this, "Succesfully connection", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(RegisterActivity.this, "Fail connection", Toast.LENGTH_SHORT).show();
                System.out.println(e.toString());
            }
        });
    }

    private void clearComponents() {
        txtName.setText("");
        txtLastname.setText("");
        txtIdentification.setText("");
        txtPhone.setText("");
        txtDate.setText("");

        checkSubmitEnabled();
        txtName.requestFocus();
    }

    private void inicializar_componentes() {
        txtName = findViewById(R.id.txtName);
        txtLastname = findViewById(R.id.txtLastname);
        txtIdentification = findViewById(R.id.txtIdentification);
        txtPhone = findViewById(R.id.txtPhone);
        txtDate = findViewById(R.id.txtDate);
        btnRegister = findViewById(R.id.btnRegister);
        doctorSpinner = findViewById(R.id.spinnerDoctor);

        txtName.addTextChangedListener(this);
        txtLastname.addTextChangedListener(this);
        txtIdentification.addTextChangedListener(this);
        txtPhone.addTextChangedListener(this);
        txtDate.addTextChangedListener(this);

        medicalAppointment = new MedicalAppointment();
        checkSubmitEnabled();
    }

    private void stablishUser() {
        medicalAppointment.setName(txtName.getText().toString());
        medicalAppointment.setLastname(txtLastname.getText().toString());
        medicalAppointment.setIdentification(txtIdentification.getText().toString());
        medicalAppointment.setPhone(txtPhone.getText().toString());
        medicalAppointment.setDate(selectedDate);
        medicalAppointment.setDoctor(selectedDoctor.getId());
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedDoctor = (Doctor) adapterView.getSelectedItem();
        checkSubmitEnabled();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        selectedDoctor = null;
        checkSubmitEnabled();
    }

    private void checkSubmitEnabled() {
        btnRegister.setEnabled(
                txtName.getText().length() > 0
                        && txtLastname.getText().length() > 0
                        && txtIdentification.getText().length() > 0
                        && txtPhone.getText().length() > 0
                        && selectedDate != null
                        && selectedDoctor != null
        );
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        try {
            selectedDate = dateFormat.parse(txtDate.getText().toString());
        } catch (ParseException e) {
            selectedDate = null;
        }
        checkSubmitEnabled();
    }
}