package com.resurrection.appbase.data.model.cheese


sealed class CheeseListItem(val name: String) {
    data class Item(val cheese: Cheese) : CheeseListItem(cheese.name)
    data class Separator(private val letter: Char) : CheeseListItem(letter.toUpperCase().toString())
}