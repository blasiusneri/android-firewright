package com.blas.android_firewright.main.data.repository

import com.blas.android_firewright.main.data.contract.NetworkService
import com.blas.android_firewright.main.ui_layer.model.Pokemon
import com.blas.android_firewright.main.util.NetworkResponseMapper
import com.blas.android_firewright.core.network.Result
import okio.IOException
import javax.inject.Inject

class PokedexRepository @Inject constructor(
    private val api: NetworkService
) {

    suspend fun getPokedex(id: Int): Result<List<Pokemon>> {
        return try {
            val response = api.getPokedex(id)
            val pokemonList = NetworkResponseMapper.pokedexResponseToPokemon(response)
            Result.Success(pokemonList)
        } catch (exception: IOException) {
            Result.Error(exception)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}