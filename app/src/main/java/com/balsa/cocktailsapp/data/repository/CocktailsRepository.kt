package com.balsa.cocktailsapp.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.balsa.cocktailsapp.api.CocktailsApi
import com.balsa.cocktailsapp.api.CocktailsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CocktailsRepository @Inject constructor(private var cocktailsApi: CocktailsApi) {

    suspend fun getCocktails(type: String) : CocktailsResponse {
        return cocktailsApi.getAllCocktails(type)
    }

    suspend fun getCocktailsByName(name: String) : CocktailsResponse {
        return cocktailsApi.getCocktailsByName(name)
    }

    suspend fun getFullCocktailDetailsByID(id: String) : CocktailsResponse {
        return cocktailsApi.getFullCocktailDetailsByID(id)
    }

}