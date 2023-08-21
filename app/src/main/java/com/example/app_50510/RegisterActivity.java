package com.example.app_50510;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.app_50510.setup.ServiceLocator;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.stream.Stream;

import grupo10.medicalappointments.model.entities.Doctor;
import grupo10.medicalappointments.model.entities.MedicalAppointment;
import grupo10.medicalappointments.model.repositories.DoctorsRepository;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtName;
    private EditText txtLastname;
    private EditText txtIdentification;
    private EditText txtPhone;
    private EditText txtDate;
    private Spinner txtDoctor;

    private MedicalAppointment objUser;
    private Button btnRegister;
    private AdministratorSQL objBase;

    private DoctorsRepository doctorsRepository = ServiceLocator.getInstance().getDoctorsRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        inicializar_componentes();

        ArrayAdapter<Doctor> adapter =new ArrayAdapter(
                this,
                R.layout.activity_register,
                doctorsRepository.getAll().toArray(Doctor[]::new)
        );
        adapter.setDropDownViewResource(R.layout.activity_register);

        txtDoctor.setAdapter(adapter);

        btnRegister.setOnClickListener(v -> {
            //stablishUser();
            boolean confirm = objBase.connectSQL();
            if(confirm){
                Toast.makeText(RegisterActivity.this, "Succesfully connection", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(RegisterActivity.this, "Fail connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void inicializar_componentes(){
        txtName = findViewById(R.id.txtName);
        txtLastname = findViewById(R.id.txtLastname);
        txtIdentification = findViewById(R.id.txtIdentification);
        txtPhone = findViewById(R.id.txtPhone);
        txtDate = findViewById(R.id.txtDate);
        txtDoctor = findViewById(R.id.txtDoctor);
        btnRegister = findViewById(R.id.btnRegister);
        objUser = new MedicalAppointment();
        objBase = new AdministratorSQL();
    }

    private  void stablishUser(){
        objUser.setName(txtName.getText().toString());
        objUser.setLastname(txtLastname.getText().toString());
        objUser.setIdentification(txtIdentification.toString());
        objUser.setPhone(txtPhone.getText().toString());
        objUser.setDate(String.valueOf(Date.valueOf(txtDate.getText().toString())));
        objUser.setDoctor(Integer.parseInt(txtDoctor.getPrompt().toString()));
    }
}