package com.balsa.cocktailsapp.api
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailsApi {

    companion object {
        const val BASE_URL = "https://www.thecocktaildb.com/"
    }

    @GET("api/json/v1/1/filter.php")
    suspend fun getAllCocktails(
        @Query("a") type : String
    ) : CocktailsResponse

    @GET("api/json/v1/1/search.php")
    suspend fun getCocktailsByName(
        @Query("s") name : String
    ) : CocktailsResponse

    @GET("api/json/v1/1/lookup.php")
    suspend fun getFullCocktailDetailsByID(
        @Query("i") id : String
    ) : CocktailsResponse
}