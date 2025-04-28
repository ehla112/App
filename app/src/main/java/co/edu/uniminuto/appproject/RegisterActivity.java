package co.edu.uniminuto.appproject;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import co.edu.uniminuto.appproject.entities.Mascotas;
import co.edu.uniminuto.appproject.entities.User;
import co.edu.uniminuto.appproject.repository.MascotasRepository;
import co.edu.uniminuto.appproject.repository.UserRepository;

public class RegisterActivity extends AppCompatActivity {

    private Context context;

    private EditText etUser;
    private EditText etName;
    private EditText etPassword;
    private EditText etEmail;
    private EditText etPhone;
    private EditText etAddress;

    private EditText etNombreMascota;
    private EditText etTipoMascota;
    private EditText etEdad;
    private EditText etRaza;
    private EditText etPeso;

    SQLiteDatabase sqLiteDatabase;
    private Button btnSaveUser;
    private int id;
    private String usuario;
    private String nombre;
    private String password;
    private String email;
    private String telefono;
    private String direccion;
    private int idMascota;
    private String nombreMascota;
    private String tipoMascota;
    private String edad;
    private String raza;
    private double peso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        begin();
        btnSaveUser.setOnClickListener(this::saveUser);
    }

    private void saveUser(View view){
        capData();
        User user = new User(usuario, nombre, password, email, telefono, direccion, true);
        UserRepository userRepository = new UserRepository(view, context);
        long iddueno= userRepository.insertUser(user);
        if(iddueno !=-1){
            Mascotas mascota = new Mascotas(nombreMascota, tipoMascota, edad, raza, peso, 1, (int)iddueno);
            MascotasRepository mascotasRepository = new MascotasRepository(view,context);
            mascotasRepository.insertMascotas(mascota);
            Snackbar.make(view, "Usuario registrado", Snackbar.LENGTH_LONG).show();
            Intent intent = new Intent(context, ActivityHome.class);
            intent.putExtra("idDueno", (int) iddueno);


            startActivity(intent);
            finish();


        }else {
            Snackbar.make(view, "Error al registrar usuario", Snackbar.LENGTH_LONG).show();
        }

    }
    private void capData(){
        this.usuario = etUser.getText().toString();
        this.nombre = etName.getText().toString();
        this.password = etPassword.getText().toString();
        this.email = etEmail.getText().toString();
        this.telefono = etPhone.getText().toString();
        this.direccion = etAddress.getText().toString();
        this.nombreMascota = etNombreMascota.getText().toString();
        this.tipoMascota = etTipoMascota.getText().toString();
        this.edad = etEdad.getText().toString();
        this.raza = etRaza.getText().toString();
        this.peso = Double.parseDouble(etPeso.getText().toString());


    }

    private void begin() {
        this.context = this;
        this.etUser = findViewById(R.id.etUser);
        this.etName = findViewById(R.id.etName);
        this.etPassword = findViewById(R.id.etPassword);
        this.etEmail = findViewById(R.id.etEmail);
        this.etPhone = findViewById(R.id.etPhone);
        this.etAddress = findViewById(R.id.etAddress);
        this.etNombreMascota = findViewById(R.id.etNombreMascota);
        this.etTipoMascota = findViewById(R.id.etTipoMascota);
        this.etEdad = findViewById(R.id.etEdad);
        this.etRaza =findViewById(R.id.etRazaUp);
        this.etPeso = findViewById(R.id.etPeso);



        this.btnSaveUser = findViewById(R.id.btnSaveMascota);


    }
}