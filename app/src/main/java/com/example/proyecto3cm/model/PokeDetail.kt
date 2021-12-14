package com.example.proyecto3cm.model

import com.google.gson.annotations.SerializedName

class PokeDetail {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("base_experience")
    var baseExperience: String? = null

    @SerializedName("height")
    var height: String? = null

    @SerializedName("weight")
    var weight: String? = null

    @SerializedName("sprites")
    var sprites: Sprites? = null

    /*@SerializedName("types")
    var types: Types? = null*/

}

class Sprites{
    @SerializedName("other")
    var other: Other? = null

}

class Other{
    @SerializedName("official-artwork")
    var officialArtwork: OfficialArtwork? = null
}

class OfficialArtwork{
    @SerializedName("front_default")
    var frontDefault: String? = null
}

/*class Types{
    @SerializedName("name")
    var nameType: String? = null
}*/