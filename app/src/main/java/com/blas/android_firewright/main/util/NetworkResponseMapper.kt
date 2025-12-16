package com.blas.android_firewright.main.util

import com.blas.android_firewright.main.data.model.PokedexResponse
import com.blas.android_firewright.main.ui_layer.model.Pokemon

class NetworkResponseMapper {

    companion object {

        fun pokedexResponseToPokemon(pokedexResponse: PokedexResponse): List<Pokemon> {
            return pokedexResponse.pokemonEntries.map { pokemonEntry ->
                Pokemon(name = pokemonEntry.pokemonSpecies.name)
            }
        }
    }
}