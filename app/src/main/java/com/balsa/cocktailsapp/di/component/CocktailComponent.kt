package com.balsa.cocktailsapp.di.component

import com.balsa.cocktailsapp.ui.mainActivity.MainActivity
import com.balsa.cocktailsapp.di.module.AppModule
import com.balsa.cocktailsapp.ui.detailActivity.DetailActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface CocktailComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(detailActivity: DetailActivity)
}