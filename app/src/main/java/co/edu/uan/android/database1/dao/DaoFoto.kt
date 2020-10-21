package co.edu.uan.android.database1.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import co.edu.uan.android.database1.entities.Foto

@Dao
interface DaoFoto {
    @Query("SELECT * FROM fotos")
    fun findAll() : List<Foto>
    @Query("SELECT * FROM fotos WHERE lugar LIKE :lugar")
    fun findByLugar(lugar: String): List<Foto>
    @Insert
    fun create(data: Foto)
}