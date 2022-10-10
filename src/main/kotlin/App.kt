fun main() {
    println("Benvingut al pescamines!\n")

    var n: Int?
    while (true) {
        print("Introdueix la mida dels costats del taulell (entre 1 i 26): ")
        n = readln().toIntOrNull()
        if (n != null && n in 1..26) break
        println("El valor introduït no és vàlid!")
    }

    var nm: Int?
    while (true) {
        print("Introdueix el nombre de mines dessitjades (entre 1 i ${n!! * n}): ")
        nm = readln().toIntOrNull()
        if (nm != null && nm in 1..(n * n)) break
        println("El valor introduït no és vàlid!")
    }

    val tauler = Tauler(n, nm!!)
    var guanyes = true
    while (true) {
        print("\n$tauler\nIntrodueix l'acció a realitzar: ")
        val accio = readln().trim().lowercase()
        if (!((accio.length == 2 || accio.length == 3 && accio[2] == 'm')
                    && accio[0].code in 97 until 97 + n
                    && accio[1].code in 97 until 97 + n)
        ) {
            println("L'acció introduïda no és vàlida!")
            continue
        }
        val x = accio[0].code - 97
        val y = accio[1].code - 97
        if (accio.length == 3) tauler.marcaMina(x, y)
        else {
            tauler.descobreixCasella(x, y)
            if (tauler.hiHaMina(x, y)) {
                guanyes = false
                break
            }
            if (tauler.descobert()) break
        }
    }

    tauler.descobreixTauler()
    println("\n$tauler\n${if (guanyes) "Felicitats! Has guanyat!" else "Game over!"}")
}