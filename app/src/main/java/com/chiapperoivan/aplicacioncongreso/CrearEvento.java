package com.chiapperoivan.aplicacioncongreso;

import BaseDeDatos.DataBaseManagerEventos;
import BaseDeDatos.DataBaseManagerUsuario;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CrearEvento extends AppCompatActivity {

    private EditText nombre;
    private EditText fechaInicio;
    private EditText fechaFin;
    private EditText lugar;
    private EditText url;
    private EditText descripcion;
    private EditText precio;

    public DataBaseManagerEventos dbEventos = new DataBaseManagerEventos(this);
    private SQLiteDatabase db = dbEventos.getDb();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);

        nombre = (EditText)findViewById(R.id.txtNombreEvento);
        fechaInicio = (EditText)findViewById(R.id.txtFechInicio);
        fechaFin = (EditText)findViewById(R.id.txtFechaFin);
        lugar = (EditText)findViewById(R.id.txtLugar);
        url = (EditText)findViewById(R.id.txtURL);
        descripcion = (EditText)findViewById(R.id.txtDescripcion);
        precio = (EditText)findViewById(R.id.txtPrecio);
    }

    public void crearEvento(View view){
        String name = nombre.getText().toString();
        String fechaIni = fechaInicio.getText().toString();
        String fechaFinal = fechaFin.getText().toString();
        String lugarEvento = lugar.getText().toString();
        String urlEvento = url.getText().toString();
        String descripc = descripcion.getText().toString();
        String price = precio.getText().toString();

        dbEventos.InsertarEvento(name,fechaIni,fechaFinal,lugarEvento,urlEvento,descripc,price);
        dbEventos.CerrarBD();
        nombre.setText("");
        fechaInicio.setText("");
        fechaFin.setText("");
        lugar.setText("");
        descripcion.setText("");
        precio.setText("");

    }


}
