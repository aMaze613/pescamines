class Casella(
    private var mines: Int = 0,
    private var esMina: Boolean = false,
    private var descoberta: Boolean = false,
    private var marcadaMina: Boolean = false
) {

    fun getEsMina() = esMina

    fun setEsMina() {
        esMina = true
    }

    fun getDescoberta() = descoberta

    fun setDescoberta() {
        descoberta = true
    }

    fun getMinaMarcada() = marcadaMina

    fun setMarcaMina() {
        marcadaMina = !marcadaMina
    }

    fun getMines() = mines

    fun setMines(mines: Int) {
        this.mines = mines
    }

    override fun toString(): String =
        if (!descoberta) {
            if (marcadaMina) "*"
            else "·"
        } else if (esMina) "#"
        else "$mines"
}
