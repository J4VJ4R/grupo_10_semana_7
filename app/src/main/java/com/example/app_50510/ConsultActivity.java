package com.example.app_50510;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import grupo10.medicalappointments.model.entities.MedicalAppointment;

public class ConsultActivity extends AppCompatActivity {
    private EditText txtName;
    private EditText txtIdentification;
    private EditText txtDate;

    private MedicalAppointment objUser;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        inicializar_componentes();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(true){
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

        objUser = new MedicalAppointment();
    }

}