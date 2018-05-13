package BaseDeDatos;

import Modelo.Evento;
import Modelo.Usuario;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManagerEventos {

    //CN = Colum Name
    private static final String NOMBRE_TABLA = "Eventos";
    private static final String CN_ID = "Id";
    private static final String CN_NOMBRE = "Nombre";
    private static final String CN_FECHAINICIO = "FechaInicio";
    private static final String CN_FECHAFIN = "FechaFin";
    private static final String CN_LUGAR = "Lugar";
    private static final String CN_URL = "URL";
    private static final String CN_DESCRIPCION = "Descripcion";
    private static final String CN_PRECIO = "Precio";

    private SQLiteDatabase db;
    private DataBaseManager dataBaseManager;

    public DataBaseManagerEventos(Context context) {
        dataBaseManager = new DataBaseManager(context);
        db = dataBaseManager.getDb();
    }

    public static final String CREATE_TABLE = "create table " + NOMBRE_TABLA + " ("
            + CN_ID + " integer PRYMARY KEY AUTOINCREMENT, "
            + CN_NOMBRE + " text NOT NULL, "
            + CN_FECHAINICIO + " text NOT NULL, "
            + CN_FECHAFIN + "text NOT NULL"
            + CN_LUGAR + "text NOT NULL"
            + CN_URL + "text NOT NULL"
            + CN_DESCRIPCION + "text NOT NULL"
            + CN_PRECIO + "text NOT NULL"
            + ");";

    public Cursor CargarCursor(){
        String [] columnas = new String[]{CN_ID,CN_NOMBRE,CN_FECHAINICIO,CN_FECHAFIN,CN_LUGAR,CN_URL,CN_DESCRIPCION,CN_PRECIO};
        //Hacemos una consulta, en este caso un select, a la BD trayendo los registros que consideremos necesarios
        return db.query(NOMBRE_TABLA,columnas,null,null,null,null,null,null);
    }

    public Boolean ComprobarRegistro(String id){
        boolean EXISTE = false;
        Cursor resultadoRegistro = db.rawQuery("Select * from " + NOMBRE_TABLA + " WHERE " + CN_ID + "=" + id,null);

        if (resultadoRegistro.getCount() > 0)
            EXISTE= true;

        return EXISTE;
    }

    public ContentValues generarContentValues(String nombre, String fechaInicio, String fechaFin, String lugar, String url,
                                              String descripcion, String precio){
        //Colección de tipo diccionario, almacena parejas clave-valor, la clave será el nombre de cada campo
        // y el valor será el dato correspondiente a insertar en dicho campo
        ContentValues valores = new ContentValues();
        valores.put(CN_NOMBRE,nombre);
        valores.put(CN_FECHAINICIO,fechaInicio);
        valores.put(CN_FECHAFIN,fechaFin);
        valores.put(CN_LUGAR,lugar);
        valores.put(CN_URL,url);
        valores.put(CN_DESCRIPCION,descripcion);
        valores.put(CN_PRECIO,precio);

        return valores;
    }

    public void Eliminar(String id) {
        String[] argumentos = new String[]{id};
        db.delete(NOMBRE_TABLA, "Id=?",argumentos);
    }

    public void InsertarEvento(String nombre, String fechaInicio, String fechaFin, String lugar, String url,
                               String descripcion, String precio) {
        db.insert(NOMBRE_TABLA,null,generarContentValues(nombre,fechaInicio,fechaFin,lugar,url,descripcion,precio));
    }

    public void ActualizarEvento(String id, String nombre, String fechaInicio, String fechaFin, String lugar, String url,
                                 String descripcion, String precio) {
        ContentValues valores = new ContentValues();
        valores.put(CN_NOMBRE,nombre);
        valores.put(CN_FECHAINICIO,fechaInicio);
        valores.put(CN_FECHAFIN,fechaFin);
        valores.put(CN_LUGAR,lugar);
        valores.put(CN_URL,url);
        valores.put(CN_DESCRIPCION,descripcion);
        valores.put(CN_PRECIO,precio);
        //Los argumentos SQL se indicarán con el símbolo ‘?‘, y los valores de dichos argumentos deben pasarse en el array
        // en el mismo orden que aparecen en la sentencia SQL (cuando debemos pasar mas de un argumento).
        String[] argumentos = new String[]{id};
        db.update(NOMBRE_TABLA,valores,"Id=?",argumentos);
    }

    public void CerrarBD(){
        db.close();
    }

    public List<Evento> getListaUsuarios(){
        List<Evento> listaEvento = new ArrayList<>();
        Cursor cursor = CargarCursor();

        //Si hay siguiente devuelve True, sino devuelve False
        while (cursor.moveToNext()){

            Evento evento = new Evento();

            //Obtengo el String de la columna 0, el id, y se lo grabo al objeto evento en el id, paso de cursor a objeto
            evento.setId(cursor.getString(0));
            evento.setNombre(cursor.getString(1));
            evento.setFechaInicio(cursor.getString(2));
            evento.setFechaFin(cursor.getString(3));
            evento.setLugar(cursor.getString(4));
            evento.setUrl(cursor.getString(5));
            evento.setDescripcion(cursor.getString(6));
            evento.setPrecio(cursor.getString(7));

            listaEvento.add(evento);
        }

        return listaEvento;
    }

    public SQLiteDatabase getDb(){
        return db;
    }
}
