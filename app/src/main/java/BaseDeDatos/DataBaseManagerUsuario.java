package BaseDeDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Modelo.Usuario;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseManagerUsuario{

    //CN = Colum Name
    private static final String NOMBRE_TABLA = "Usuario";
    private static final String CN_ID = "Id";
    private static final String CN_NOMBRE = "Nombre";
    private static final String CN_DNI = "DNI";
    private static final String CN_EMAIL = "Email";
    private static final String CN_PASSWORD = "Password";

    private SQLiteDatabase db;
    private DataBaseManager dataBaseManager;

    public DataBaseManagerUsuario(Context context) {
        dataBaseManager = new DataBaseManager(context);//Obtenemos el permiso para entrar a la BD (Ctrl + Click en super)
        db = dataBaseManager.getDb();

    }

    public static final String CREATE_TABLE = "create table " + NOMBRE_TABLA + " ("
            + CN_ID + " integer PRYMARY KEY AUTOINCREMENT, "
            + CN_NOMBRE + " text NOT NULL, "
            + CN_DNI + " text NOT NULL, "
            + CN_EMAIL + "text NOT NULL"
            + CN_PASSWORD + "text NOT NULL"
            + ");";

    //Al objeto Cursor, lo vamos a tener que recorrer, ya que contiene uno o muchos registros de una consulta de BD
    public Cursor CargarCursor(){
        String [] columnas = new String[]{CN_ID,CN_NOMBRE,CN_EMAIL,CN_DNI,CN_PASSWORD};
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


    public ContentValues generarContentValues(String nombre, String dni, String email,String password){
    //Colección de tipo diccionario, almacena parejas clave-valor, la clave será el nombre de cada campo
        // y el valor será el dato correspondiente a insertar en dicho campo
        ContentValues valores = new ContentValues();
        valores.put(CN_NOMBRE,nombre);
        valores.put(CN_DNI,dni);
        valores.put(CN_EMAIL,email);
        valores.put(CN_PASSWORD,password);

        return valores;
    }

    public void Eliminar(String id) {
        String[] argumentos = new String[]{id};
        db.delete(NOMBRE_TABLA, "Id=?",argumentos);
    }

    public void InsertarUsuario(String nombre, String dni, String email, String password) {
        db.insert(NOMBRE_TABLA,null,generarContentValues(nombre,dni,email,password));
    }

    public void ActualizarUsuario( String id, String nombre, String dni, String email, String password) {
        ContentValues valores = new ContentValues();
        valores.put(CN_NOMBRE,nombre);
        valores.put(CN_DNI,dni);
        valores.put(CN_EMAIL,email);
        valores.put(CN_PASSWORD,password);
        //Los argumentos SQL se indicarán con el símbolo ‘?‘, y los valores de dichos argumentos deben pasarse en el array
        // en el mismo orden que aparecen en la sentencia SQL (cuando debemos pasar mas de un argumento).
        String[] argumentos = new String[]{id};
        db.update(NOMBRE_TABLA,valores,"Id=?",argumentos);
    }

    public void CerrarBD(){
        db.close();
    }

    public List<Usuario> getListaUsuarios(){
        List<Usuario> listaUsuario = new ArrayList<>();
        Cursor cursor = CargarCursor();

        //Si hay siguiente devuelve True, sino devuelve False
        while (cursor.moveToNext()){

            Usuario usuario = new Usuario();

            //Obtengo el String de la columna 0, el id, y se lo grabo al objeto usuario en el id, paso de cursor a objeto
            usuario.setId(cursor.getString(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setDni(cursor.getString(2));
            usuario.setEmail(cursor.getString(3));

            listaUsuario.add(usuario);
        }

        return listaUsuario;
    }

    public SQLiteDatabase getDb(){
        return db;
    }
}
