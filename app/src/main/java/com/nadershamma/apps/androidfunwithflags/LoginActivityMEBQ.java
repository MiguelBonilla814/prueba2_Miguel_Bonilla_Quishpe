package com.nadershamma.apps.androidfunwithflags;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivityMEBQ extends AppCompatActivity {

    private EditText editText_usuario;
    private EditText editText_contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_usuario = findViewById(R.id.editText_usuario);
        editText_contraseña = findViewById(R.id.editText_contraseña);
    }

    public void Login(View view){
        String usuarioLogin = editText_usuario.getText().toString();
        String contraseña = editText_contraseña.getText().toString();

        if(usuarioLogin.matches("") || contraseña.matches("")){

            Toast.makeText(this, "Ingresar usuario y contraseña.", Toast.LENGTH_SHORT).show();
            return;
        }else {
            if (usuarioLogin.matches("usuario1") && contraseña.matches("1234")) {
                Toast.makeText(this, "Login exitoso.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Contraseña incorrecta.", Toast.LENGTH_SHORT).show();
            }
        }
    }


}