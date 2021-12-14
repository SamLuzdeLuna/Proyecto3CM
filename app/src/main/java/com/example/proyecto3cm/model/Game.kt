package com.example.proyecto3cm.model

import com.google.gson.annotations.SerializedName

class Game {

    @SerializedName("results")
    var results: List<Animal>? = null  //Lista que contendra todos los pokemones

    class Animal{
        @SerializedName("name")
        var name: String? = null
    }

}