package com.balsa.cocktailsapp.ui.detailActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.balsa.cocktailsapp.data.repository.CocktailsRepository
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailActivityViewModelFactory @Inject constructor(private val cocktailsRepository: CocktailsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailActivityViewModel::class.java)) {
            return DetailActivityViewModel(cocktailsRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}