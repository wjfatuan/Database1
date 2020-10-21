package co.edu.uan.android.database1

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import co.edu.uan.android.database1.databases.FotosDatabase
import co.edu.uan.android.database1.entities.Foto

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //sqlite()

        room()

    }

    fun room() {
        val foto = Foto("2020-10-20", "Bogota")
        CreateFoto(this, foto).execute()
        ConsultarFotos(this).execute()
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
        }
        cursor.close()
    }
}

class CreateFoto(var applicationContext: MainActivity, var foto: Foto) :
    AsyncTask<Void, Void, Boolean>() {
    override fun doInBackground(vararg params: Void?): Boolean {
        val db = Room.databaseBuilder(applicationContext,
            FotosDatabase::class.java, "db_fotos_room1"
        ).build()
        db.fotosDao().create(foto)
        db.close()
        return true
    }
    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
        // include here your UI code
        Log.v("MITAG", "Foto created $foto")
    }
}


class ConsultarFotos(var applicationContext: MainActivity) :
    AsyncTask<Void, Void, List<Foto>>() {
    override fun doInBackground(vararg params: Void?): List<Foto> {
        val db = Room.databaseBuilder(applicationContext,
            FotosDatabase::class.java, "db_fotos_room1"
        ).build()
        val data =  db.fotosDao().findAll()
        db.close()
        return data
    }
    override fun onPostExecute(result: List<Foto>?) {
        super.onPostExecute(result)
        // include here your UI code
        Log.v("MITAG", "Available fotos $result")
    }
}



/**
 * TABLE mis_fotos
 *     fecha DATE         15-10-2020
 *     lugar VARCHAR2     Bogota
 *     foto  VARCHAR2     https://miservidor.com/misimagenes/fotojkhdajhgd.jpg
 * */