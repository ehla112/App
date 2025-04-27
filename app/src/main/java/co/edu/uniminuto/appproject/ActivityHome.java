package co.edu.uniminuto.appproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityHome extends AppCompatActivity {
    private Button btnInfoHumano;
    private Button btnInfoMascota;
    private Button btnCitasMedicas;
    private Button btnVacunas;
    private Button btnCerrarSesion;
    private int idDueno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        start();
        btnInfoMascota.setOnClickListener(this::startInfoMascota);
    }

    private void startInfoMascota(View view) {
        Intent intent = new Intent(this, dataPetsActivity.class);
        intent.putExtra("idDueno", idDueno);


        startActivity(intent);
        Toast.makeText(this, "idDueno enviado: " + idDueno, Toast.LENGTH_SHORT).show();


    }


    private void start() {
        btnInfoHumano = findViewById(R.id.btnInfoHumano);
        btnInfoMascota = findViewById(R.id.btnInfoMascota);
        btnCitasMedicas = findViewById(R.id.btnCitasMedicas);
        btnVacunas = findViewById(R.id.btnVacunas);
        idDueno = getIntent().getIntExtra("idDueno", -1);
        if (idDueno == -1) {
            Toast.makeText(this, "No se pudo obtener el ID del dueño", Toast.LENGTH_SHORT).show();
        }
    }
}