package com.chiapperoivan.aplicacioncongreso;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import BaseDeDatos.DataBaseManagerUsuario;

public class CrearUsuario extends AppCompatActivity {

    private EditText nombre;
    private EditText email;
    private EditText dni;
    private EditText password;
    private EditText validarPassword;

    public DataBaseManagerUsuario dbUsuario = new DataBaseManagerUsuario(this);
    private SQLiteDatabase db = dbUsuario.getDb();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        nombre = (EditText)findViewById(R.id.txtNombreUsuario);
        email = (EditText)findViewById(R.id.txtEmail);
        dni = (EditText)findViewById(R.id.txtDNI);
        password = (EditText)findViewById(R.id.txtPassword);
        validarPassword = (EditText)findViewById(R.id.txtValidarPassword);
    }

    public void crearUsuario(View view){
        String name = nombre.getText().toString();
        String mail = email.getText().toString();
        String Dni = dni.getText().toString();
        String contrasenia = password.getText().toString();
        String validarContrasenia = validarPassword.getText().toString();

        if ( contrasenia == validarContrasenia){
            dbUsuario.InsertarUsuario(name,Dni,mail,contrasenia);
            dbUsuario.CerrarBD();
            nombre.setText("");
            email.setText("");
            dni.setText("");
            password.setText("");
            validarPassword.setText("");
        }
        else{
            //Mostrar error
        }

    }


}
