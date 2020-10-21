package co.edu.uan.android.database1.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="fotos")
data class Foto(
    @ColumnInfo(name = "fecha") val fecha: String,
    @ColumnInfo val lugar: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) {
    override fun toString(): String {
        return "{id:$id, fecha: $fecha, lugar: $lugar}"
    }
}