package br.com.derlandybelchior.yosemitesummercamp.data

import br.com.derlandybelchior.yosemitesummercamp.R
import br.com.derlandybelchior.yosemitesummercamp.domain.MenuItem

class Menu {

    companion object {
        const val NUMBER_COLUMNS = 3

        fun getItems() = listOf(
            MenuItem(
                label = "Acampamento",
                icon = R.drawable.ic_camp
            ),
            MenuItem(
                label = "Pescaria",
                icon = R.drawable.ic_fishing
            ),
            MenuItem(
                label = "Fazer as malas",
                icon = R.drawable.ic_packing
            ),
            MenuItem(
                label = "Trilha",
                icon = R.drawable.ic_forest
            ),
            MenuItem(
                label = "Transporte",
                icon = R.drawable.ic_transport
            ),
            MenuItem(
                label = "Rafting",
                icon = R.drawable.ic_rafting
            ),
            MenuItem(
                label = "Rádio",
                icon = R.drawable.ic_radio
            ),
            MenuItem(
                label = "Café",
                icon = R.drawable.ic_coffee
            ),
            MenuItem(
                label = "Telescópio",
                icon = R.drawable.ic_telescope
            ),
            MenuItem(
                label = "Rappel",
                icon = R.drawable.ic_rappel
            ),
            MenuItem(
                label = "Kart",
                icon = R.drawable.ic_kart
            ),
            MenuItem(
                label = "Surf",
                icon = R.drawable.ic_surfboard
            )
        )
    }
}