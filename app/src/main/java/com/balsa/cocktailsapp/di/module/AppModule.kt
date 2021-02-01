package com.balsa.cocktailsapp.di.module

import com.balsa.cocktailsapp.api.CocktailsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(CocktailsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideCocktailsApi(retrofit: Retrofit): CocktailsApi =
        retrofit.create(CocktailsApi::class.java)

}