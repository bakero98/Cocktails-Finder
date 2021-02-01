package com.balsa.cocktailsapp.api

import com.balsa.cocktailsapp.data.model.Cocktail

data class CocktailsResponse (
    val drinks : List<Cocktail>
)