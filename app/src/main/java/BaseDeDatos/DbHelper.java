package BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NOMBRE = "Congreso.sqlite";
    private static int DB_VERSION = 1;

    public DbHelper(Context context) {

        super(context, DB_NOMBRE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Aqui se deben crear todas las tablas, en este caso creamos la tabla Usuario
        db.execSQL(DataBaseManagerUsuario.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
