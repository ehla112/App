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

import co.edu.uniminuto.appproject.entities.User;
import co.edu.uniminuto.appproject.repository.UserRepository;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private static final int RC_SIGN_IN = 1;
    private EditText etUsername;
    private  EditText etPassword;
    private Button btnIniciar;
    private  Button btnRegister;
    SQLiteDatabase sqLiteDatabase;
    private int id;
    private String usuario;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        begin();
        btnIniciar.setOnClickListener(this::iniciarSesion);
        btnRegister.setOnClickListener(this::register);

    }

    private void iniciarSesion(View view) {
        capData();
        if(usuario.isEmpty() || password.isEmpty()){
            Snackbar.make(view, "Debe ingresar usuario y contraseña", Snackbar.LENGTH_LONG).show();
            return;
        }
        UserRepository userRepository = new UserRepository(view, context);
        User user = userRepository.login(usuario, password);
        if(user != null){
            Snackbar.make(view, "Bienvenido " + user.getName(), Snackbar.LENGTH_LONG).show();
            Intent intent = new Intent(context, ActivityHome.class);
            intent.putExtra("idDueno", user.getId());
            startActivity(intent);
            finish();
        }else{
            Snackbar.make(view, "Usuario o contraseña incorrectos", Snackbar.LENGTH_LONG).show();
        }
    }

    private void register(View view) {
        Intent intent = new Intent(context, RegisterActivity.class);
        startActivity(intent);
    }

    private void capData(){
        this.usuario = etUsername.getText().toString();
        this.password = etPassword.getText().toString();

    }

    private void begin() {
        this.context = this;
        this.etUsername = findViewById(R.id.etUsername);
        this.etPassword = findViewById(R.id.etPassword);
        this.btnIniciar = findViewById(R.id.btnIniciar);
        this.btnRegister = findViewById(R.id.btnRegister);



    }
}