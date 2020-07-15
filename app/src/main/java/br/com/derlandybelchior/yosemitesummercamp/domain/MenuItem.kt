package br.com.derlandybelchior.yosemitesummercamp.domain

data class MenuItem (
    val label: String,
    val icon: Int,
    var isSelected: MenuItemStaus = MenuItemStaus.NOT_SELECTED
)