package br.com.pedrohmv

import br.com.pedrohmv.domain.Filme

object MockData {
    val filmeA = Filme(
        299537,
        "Captain Marvel",
        "/AtsgWhDnHTq68L0lLsUrCnM7TjG.jpg",
        listOf(28,12,878),
        "Bla bla bla bla."
    )

    val filmeListMock = listOf(filmeA, filmeA.copy(id = 166428), filmeA.copy(id = 450001))
}