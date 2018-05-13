package com.chiapperoivan.aplicacioncongreso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//Libreria que debo importar
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //Declaramos las vbles que queremos traer del Activity, e importo la libreria sino me la sugiere
    private EditText usuario;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Enlazamos estas variables con los objetos definidos en el archivo XML
        //La clase findViewById retorna un tipo View,utilizaremos el operador cast (le antecedemos entre paréntesis el nombre de la clase)
        usuario = (EditText)findViewById(R.id.txtUsuario);
        password = (EditText) findViewById(R.id.txtPassword);
    }

    //La funcion sera llamada desde el evento onClick en el diseño del boton, y le pasara el objeto vista
    public void verificarUsuario (View view){
        //Obtengo los valores de los textboxs
        String valorUsuario = usuario.getText().toString();
        String valorPassword = password.getText().toString();
        
    }

    public void abrirCrearUsuario(View view){
        //La Intent describe la actividad que se debe iniciar y contiene los datos necesarios para ello.
        Intent i = new Intent(this, CrearUsuario.class);
        startActivity(i);
    }

}
