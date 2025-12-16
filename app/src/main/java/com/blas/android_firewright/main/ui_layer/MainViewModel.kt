package com.blas.android_firewright.main.ui_layer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blas.android_firewright.main.data.repository.PokedexRepository
import com.blas.android_firewright.main.ui_layer.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PokedexRepository
) : ViewModel() {

    private val _message = MutableStateFlow("from View Model")
    val message: StateFlow<String> = _message

    private var _pokemon = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonSize: StateFlow<List<Pokemon>> = _pokemon

    fun loadPokedex() {
        viewModelScope.launch {
            val pokemons = repository.getPokedex(1)
            _pokemon.value = pokemons
        }
    }
}