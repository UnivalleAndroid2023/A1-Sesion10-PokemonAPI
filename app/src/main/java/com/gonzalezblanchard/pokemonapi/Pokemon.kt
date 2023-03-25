package com.gonzalezblanchard.pokemonapi

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id:Int,
    @SerializedName("base_experience") val base_experience:Int,
    @SerializedName("weight") val weight:Int,
    @SerializedName("sprites") val sprites: PokemonSprites
)
