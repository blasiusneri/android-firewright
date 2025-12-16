package com.blas.android_firewright.main.data.repository

import com.blas.android_firewright.main.data.contract.NetworkService
import com.blas.android_firewright.main.data.model.PokedexResponse
import com.blas.android_firewright.main.ui_layer.model.Pokemon
import com.blas.android_firewright.main.util.NetworkResponseMapper
import javax.inject.Inject

class PokedexRepository @Inject constructor(
    private val api: NetworkService
) {

    suspend fun getPokedex(id: Int): List<Pokemon> {
        return NetworkResponseMapper.pokedexResponseToPokemon(api.getPokedex(id))
    }
}