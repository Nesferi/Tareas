package DBHELPER;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nestor.fernandez on 16/11/2017.
 */

public class sqlite extends SQLiteOpenHelper {

    public String usuarios="CREATE TABLE usuarios(idusuario INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT,email TEXT, password TEXT)";
    public String tareas="CREATE TABLE tareas(idtareas INTEGER PRIMARY KEY AUTOINCREMENT, titulo TXT, descripcion TEXT, idusuario INTEGER, Foreign key (idusuario) references usuarios(idusuario))";

    public sqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(usuarios);
        db.execSQL(tareas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}