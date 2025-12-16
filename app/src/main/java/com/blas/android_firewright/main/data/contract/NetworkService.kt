package com.blas.android_firewright.main.data.contract

import com.blas.android_firewright.main.data.model.PokedexResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {

    @GET("pokedex/{id}/")
    suspend fun getPokedex(
        @Path("id") id: Int
    ): PokedexResponse
}