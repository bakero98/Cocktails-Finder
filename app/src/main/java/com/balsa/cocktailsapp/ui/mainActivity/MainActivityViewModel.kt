package com.balsa.cocktailsapp.ui.mainActivity

import android.util.Log
import androidx.lifecycle.*
import com.balsa.cocktailsapp.data.model.Cocktail
import com.balsa.cocktailsapp.data.repository.CocktailsRepository
import kotlinx.coroutines.launch
import androidx.databinding.Observable

class MainActivityViewModel(private val cocktailsRepository: CocktailsRepository) : ViewModel(), Observable {

    private var currentQueryType = MutableLiveData(QUERY_TYPE)
    var cocktails : MutableLiveData<List<Cocktail>> = MutableLiveData()
    val searchQuery = MutableLiveData(DEFAULT_QUERY)



    fun resetCocktails() {
        viewModelScope.launch {
            if(searchQuery.value.isNullOrEmpty()){
                cocktails.value = currentQueryType.value?.let { cocktailsRepository.getCocktails(it).drinks }!!
            }else{
                cocktails.value = cocktailsRepository.getCocktailsByName(searchQuery.value!!).drinks
            }
        }

    }

    companion object {
        private const val QUERY_TYPE = "Alcoholic"
        private const val DEFAULT_QUERY = ""
    }

    override fun addOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {
        return
    }

    override fun removeOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {
        return
    }

}