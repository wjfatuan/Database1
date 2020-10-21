package co.edu.uan.android.database1.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import co.edu.uan.android.database1.dao.DaoFoto
import co.edu.uan.android.database1.entities.Foto

@Database(entities = arrayOf(Foto::class), version=1)
abstract class FotosDatabase : RoomDatabase() {
    abstract fun fotosDao(): DaoFoto
}
