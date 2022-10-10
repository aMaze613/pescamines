class Tauler(
    private var n: Int = 0,
    private var nm: Int = 0
) {
    private var tauler = Array(n) { Array(n) { Casella() } }

    init {
        posaMines()
        comptaMines()
    }

    private fun posaMines() {
        val casellesOk = mutableListOf<Casella>()
        tauler.forEach { casellesOk.addAll(it) }
        for (mina in 0 until nm) {
            val casellaRand = (0 until casellesOk.size).random()
            casellesOk[casellaRand].setEsMina()
            casellesOk.removeAt(casellaRand)
        }
    }

    private fun comptaMines() {
        tauler.forEachIndexed { x, fila ->
            fila.forEachIndexed { y, casella ->
                var mines = 0
                for (i in (if (x - 1 >= 0) x - 1 else 0)..(if (x + 1 < n) x + 1 else n - 1)) {
                    for (j in (if (y - 1 >= 0) y - 1 else 0)..(if (y + 1 < n) y + 1 else n - 1)) {
                        if ((x != i || y != j) && tauler[i][j].getEsMina()) mines++
                    }
                }
                casella.setMines(mines)
            }
        }
    }

    fun hiHaMina(x: Int, y: Int) = tauler[x][y].getEsMina()

    fun descobert() = !tauler.any { it.any { casella -> !casella.getDescoberta() && !casella.getEsMina() } }

    fun descobreixCasella(x: Int, y: Int) {
        tauler[x][y].setDescoberta()
        if (!tauler[x][y].getEsMina() && tauler[x][y].getMines() == 0) {
            for (i in (if (x - 1 >= 0) x - 1 else 0)..(if (x + 1 < n) x + 1 else n - 1)) {
                for (j in (if (y - 1 >= 0) y - 1 else 0)..(if (y + 1 < n) y + 1 else n - 1)) {
                    if (!descoberta(i, j) && !minaMarcada(i, j)) descobreixCasella(i, j)
                }
            }
        }
    }

    fun descobreixTauler() = tauler.forEach { it.forEach { casella -> casella.setDescoberta() } }

    private fun descoberta(x: Int, y: Int) = tauler[x][y].getDescoberta()

    fun marcaMina(x: Int, y: Int) = tauler[x][y].setMarcaMina()

    private fun minaMarcada(x: Int, y: Int) = tauler[x][y].getMinaMarcada()

    override fun toString(): String {
        var taulerStr = " "
        for (i in 0 until n) taulerStr += " ${(97 + i).toChar()}"
        taulerStr += "\n"
        tauler.forEachIndexed { x, fila ->
            taulerStr += (97 + x).toChar()
            fila.forEach { casella -> taulerStr += " $casella" }
            taulerStr += "\n"
        }
        return taulerStr
    }
}