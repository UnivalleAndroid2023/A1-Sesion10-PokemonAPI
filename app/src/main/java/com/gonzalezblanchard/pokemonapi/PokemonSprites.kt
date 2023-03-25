package com.gonzalezblanchard.pokemonapi

import com.google.gson.annotations.SerializedName

data class PokemonSprites(
    @SerializedName("front_default")  val front_default: String,
)
