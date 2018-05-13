package BaseDeDatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseManager {

    private DbHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();//Pide permisos de escritura, y es una puerta de acceso a la BD
    }

    public void CerrarBD(){
        db.close();
    }

    //Aqui tendriamos que poner las operaciones de insertar, eliminar y actualizar de la BD

    /*
    * GETTER y SSETTER
    */


    public DbHelper getHelper() {
        return helper;
    }

    public void setHelper(DbHelper helper) {
        this.helper = helper;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public Cursor CargarCursor(){
        return null;
    }

    public Boolean ComprobarRegistro(String id){
        return false;
    }
}
