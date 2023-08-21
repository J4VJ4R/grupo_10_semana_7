package com.example.app_50510;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class ConsultActivity extends AppCompatActivity {
    private EditText txtName;
    private EditText txtIdentification;
    private EditText txtDate;

    private  ModelUser objUser;
    private Button btnRegister;
    private AdministratorSQL objBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        inicializar_componentes();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stablishUser();
                boolean confirm = objBase.connectSQL();
                if(confirm){
                    Toast.makeText(ConsultActivity.this, "Succesfully connection", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ConsultActivity.this, "Fail connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inicializar_componentes(){
        txtName = findViewById(R.id.txtName);
        txtIdentification = findViewById(R.id.txtIdentification);
        txtDate = findViewById(R.id.txtDate);
        btnRegister = findViewById(R.id.btnRegister);
        objUser = new ModelUser();
        objBase = new AdministratorSQL();
    }

    private  void stablishUser(){
        objUser.setName(txtName.getText().toString());
        objUser.setIdentification(txtIdentification.getText().toString());
        objUser.setDate(txtDate.getText().toString());
    }
}