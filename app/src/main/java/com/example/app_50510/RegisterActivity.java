package com.example.app_50510;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtName;
    private EditText txtLastname;
    private EditText txtIdentification;
    private EditText txtPhone;
    private EditText txtDate;
    private EditText txtDoctor;

    private  ModelUser objUser;
    private Button btnRegister;
    private AdministratorSQL objBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        inicializar_componentes();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stablishUser();
                boolean confirm = objBase.connectSQL();
                if(confirm){
                    Toast.makeText(RegisterActivity.this, "Succesfully connection", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this, "Fail connection", Toast.LENGTH_SHORT).show();
                }
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
        objUser = new ModelUser();
        objBase = new AdministratorSQL();
    }

    private  void stablishUser(){
        objUser.setName(txtName.getText().toString());
        objUser.setLastname(txtLastname.getText().toString());
        objUser.setIdentification(txtIdentification.toString());
        objUser.setPhone(txtPhone.getText().toString());
        objUser.setDate(String.valueOf(Date.valueOf(txtDate.getText().toString())));
        objUser.setDoctor(Integer.parseInt(txtDoctor.getText().toString()));
    }

}