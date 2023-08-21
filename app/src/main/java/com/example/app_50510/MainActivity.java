package com.example.app_50510;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_50510.setup.ServiceLocator;

import grupo10.medicalappointments.model.repositories.memory.DoctorsRepository_Memory;

public class MainActivity extends AppCompatActivity {

    private Button btnConsultMain;
    private Button btnRegistertMain;
    private Button btnDoctorsMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConsultMain = findViewById(R.id.btnConsultMain);
        btnRegistertMain = findViewById(R.id.btnRegisterMain);
        btnDoctorsMain = findViewById(R.id.btnDoctorsMain);

        btnConsultMain.setOnClickListener((view) -> {
            Intent i = new Intent(MainActivity.this, ConsultActivity.class);
            startActivity(i);
        });

        btnRegistertMain.setOnClickListener((view) -> {
            Intent i = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(i);
        });

        btnDoctorsMain.setOnClickListener((view) -> {
            Intent i = new Intent(MainActivity.this, DoctorActivity.class);
            startActivity(i);
        });
    }
}