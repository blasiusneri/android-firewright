package com.blas.android_firewright.main.data.model

import com.google.gson.annotations.SerializedName

data class PokedexResponse(
    val id: Int,
    val name: String,
    @SerializedName("pokemon_entries")
    val pokemonEntries: List<PokemonEntry>
)

data class PokemonEntry(
    @SerializedName("entry_number")
    val entryNumber: Int,
    @SerializedName("pokemon_species")
    val pokemonSpecies: PokemonSpecies
)

data class PokemonSpecies(
    val name: String,
    val url: String
)