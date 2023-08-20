package com.example.app_50510;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorActivity extends AppCompatActivity {

    private EditText txtName;
    private EditText txtId;
    private EditText txtSpeciality;

    private  ModelDoctor objUser;
    private Button btnRegister;
    private AdministratorSQL objBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        inicializar_componentes();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stablishUser();
                boolean confirm = objBase.connectSQL();
                if(confirm){
                    Toast.makeText(DoctorActivity.this, "Succesfully connection", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DoctorActivity.this, "Fail connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inicializar_componentes(){
        txtName = findViewById(R.id.txtName);
        txtId = findViewById(R.id.txtId);
        txtSpeciality = findViewById(R.id.txtSpeciality);
        btnRegister = findViewById(R.id.btnRegister);
        objUser = new ModelDoctor();
        objBase = new AdministratorSQL();
    }

    private  void stablishUser(){
        objUser.setName(txtName.getText().toString());
        objUser.setId(Integer.parseInt(txtId.getText().toString()));
        objUser.setSpecialty(txtSpeciality.getText().toString());
    }
}