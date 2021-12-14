package com.example.proyecto3cm.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface PokeApi {

    //Establecemos los endpoints de las conexiones

    @GET
    fun getGames(
        @Url url: String?
    ): Call<Game>

    @GET("api/v2/pokemon/{id}")
    fun getPokemonDetail(
        @Path("id") id:String?
    ): Call<PokeDetail>



}