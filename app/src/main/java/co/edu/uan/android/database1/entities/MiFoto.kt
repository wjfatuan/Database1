package co.edu.uan.android.database1.entities

class MiFoto(
    val id: Int,
    val fecha: String,
    val lugar: String
) {
    override fun toString(): String {
        return "{id:$id, fecha: $fecha, lugar: $lugar}"
    }
}