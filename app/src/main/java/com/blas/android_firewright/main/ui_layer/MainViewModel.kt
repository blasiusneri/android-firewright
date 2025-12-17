package com.blas.android_firewright.main.ui_layer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blas.android_firewright.core.network.Result
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

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // New state for showing a loading indicator
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadPokedex() {
        /*viewModelScope.launch {
            val pokemons = repository.getPokedex(1)
            _pokemon.value = pokemons
        }*/
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val result = repository.getPokedex(1)
            when (result) {
                is Result.Success -> {
                    _pokemon.value = result.data
                }
                is Result.Error -> {
                    _errorMessage.value = result.exception.message ?: "Unknown error"
                    _pokemon.value = emptyList()
                }

                is Result.Loading -> {
                    //No implementation needed
                }
            }

            _isLoading.value = false
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}