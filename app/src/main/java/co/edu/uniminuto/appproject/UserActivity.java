package co.edu.uniminuto.appproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import co.edu.uniminuto.appproject.entities.User;
import co.edu.uniminuto.appproject.repository.MascotasRepository;
import co.edu.uniminuto.appproject.repository.UserRepository;

public class UserActivity extends AppCompatActivity {
    private EditText etUsuario;
    private EditText etNombreUsuario;
    private EditText etContrasena;
    private EditText etCorreo;
    private EditText etTelefono;
    private EditText etDireccion;
    private Button btnActualizarUsuario;
    private Button btnEliminarUsuario;
    private UserRepository userRepository;
    private User user;
    private int idUsuario;

    private int idDueno;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
        getExtras();
        cargarDatosUsuario();
        setListeners();
    }

    private void initViews() {
        etUsuario = findViewById(R.id.etUsuario);
        etNombreUsuario = findViewById(R.id.etNombreUsuario);
        etContrasena = findViewById(R.id.etContrasena);
        etCorreo = findViewById(R.id.etCorreo);
        etTelefono = findViewById(R.id.etTelefono);
        etDireccion = findViewById(R.id.etDireccion);
        btnActualizarUsuario = findViewById(R.id.btnActualizarUsuario);
        btnEliminarUsuario = findViewById(R.id.btnEliminarUsuario);
        userRepository = new UserRepository(null, this);
    }

    private void getExtras() {
        Intent intent = getIntent();
        idDueno = intent.getIntExtra("idDueno", -1);
        idUsuario = idDueno;
    }

    private void cargarDatosUsuario() {
        if (idUsuario == -1) {
            Toast.makeText(this, "No se recibió ID de usuario", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        User user = userRepository.getUserById(idUsuario);
        if (user != null) {
            etUsuario.setText(user.getUser());
            etNombreUsuario.setText(user.getName());
            etContrasena.setText(user.getPassword());
            etCorreo.setText(user.getEmail());
            etTelefono.setText(user.getPhone());
            etDireccion.setText(user.getAddress());
        } else {
            Toast.makeText(this, "No se encontró el usuario", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    private void setListeners() {
        btnActualizarUsuario.setOnClickListener(v -> editarUsuario());
        btnEliminarUsuario.setOnClickListener(this::eliminarUsuario);
    }

    private void editarUsuario() {
        if (!etUsuario.isEnabled()) {
            habilitarCampos(true);
            btnActualizarUsuario.setText("Guardar");
        } else {
            String usuario = etUsuario.getText().toString().trim();
            String nombre = etNombreUsuario.getText().toString().trim();
            String contrasena = etContrasena.getText().toString().trim();
            String correo = etCorreo.getText().toString().trim();
            String telefono = etTelefono.getText().toString().trim();
            String direccion = etDireccion.getText().toString().trim();

            boolean updated = userRepository.updateUser(idUsuario, usuario, nombre, contrasena, correo, telefono, direccion);

            if (updated) {
                Toast.makeText(this, "Usuario actualizado correctamente", Toast.LENGTH_SHORT).show();
                habilitarCampos(false);
                btnActualizarUsuario.setText("Actualizar");
            } else {
                Toast.makeText(this, "Error al actualizar el usuario", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void eliminarUsuario(View view) {
        boolean correcto = userRepository.disableUser(idUsuario);
        if (correcto) {
            cerrarSesion();
            Snackbar.make(view, "Usuario eliminado. Cerrando sesión...", Snackbar.LENGTH_SHORT).show();
            Intent intent = new Intent(UserActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        } else {
            Snackbar.make(view, "Error al eliminar el usuario", Snackbar.LENGTH_SHORT).show();
        }
    }
    private void cerrarSesion() {
        getSharedPreferences("user_session", MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }
    private void habilitarCampos(boolean habilitar) {
        etUsuario.setEnabled(habilitar);
        etNombreUsuario.setEnabled(habilitar);
        etContrasena.setEnabled(habilitar);
        etCorreo.setEnabled(habilitar);
        etTelefono.setEnabled(habilitar);
        etDireccion.setEnabled(habilitar);
    }
}/// fin del codigo

