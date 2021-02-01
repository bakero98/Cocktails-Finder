package com.balsa.cocktailsapp.ui.detailActivity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balsa.cocktailsapp.data.model.Cocktail
import com.balsa.cocktailsapp.data.repository.CocktailsRepository
import kotlinx.coroutines.launch

class DetailActivityViewModel(private val cocktailsRepository: CocktailsRepository) : ViewModel() {

    var currentCocktail = MutableLiveData<Cocktail>()

    fun fillInformationById(id : String) {
        viewModelScope.launch {
            currentCocktail.value = cocktailsRepository.getFullCocktailDetailsByID(id).drinks[0]
        }
    }

}