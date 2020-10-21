package co.edu.uan.android.database1

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import co.edu.uan.android.database1.entities.MiFoto

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    fun sqlite() {
        val db: SQLiteDatabase = openOrCreateDatabase(
            "db_fotos1", MODE_PRIVATE, null)
//        db.execSQL("DROP TABLE mis_fotos")
//        db.execSQL("CREATE TABLE mis_fotos (" +
//                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                            "fecha DATE," +
//                            "lugar VARCHAR)")


//        db.execSQL("INSERT INTO mis_fotos(fecha,lugar) VALUES(" +
//                        "'2020/10/15', 'Bogota')")
        val values = ContentValues()
        values.put("fecha","2020/10/15")
        values.put("lugar","Bogota")
        db.insert("mis_fotos", null, values)

        val cursor = db.rawQuery("SELECT * FROM mis_fotos", null)
        while(cursor.moveToNext()) {
            // read the data
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val fecha = cursor.getString(cursor.getColumnIndex("fecha"))
            val lugar = cursor.getString(cursor.getColumnIndex("lugar"))
            // do whatever you want
            val foto = MiFoto(id, fecha, lugar)
            Log.v("MYLOG","$foto")
        }
        cursor.close()
    }
}

/**
 * TABLE mis_fotos
 *     fecha DATE         15-10-2020
 *     lugar VARCHAR2     Bogota
 *     foto  VARCHAR2     https://miservidor.com/misimagenes/fotojkhdajhgd.jpg
 * */