package com.balsa.cocktailsapp

import android.app.Application
import com.balsa.cocktailsapp.di.component.CocktailComponent
import com.balsa.cocktailsapp.di.component.DaggerCocktailComponent

class CocktailsApplication : Application() {

//    TODO
    companion object {
        lateinit var cocktailComponent : CocktailComponent
    }

    override fun onCreate() {
        super.onCreate()
        initID()
    }

    private fun initID() {
        cocktailComponent = DaggerCocktailComponent
            .builder()
            .build()
    }

}