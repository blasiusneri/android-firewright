package com.blas.android_firewright.main.ui_layer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.decode.ImageSource
import com.blas.android_firewright.main.ui_layer.model.Pokemon
import com.blas.android_firewright.main.util.ImageLoaderUtil
import com.blas.android_firewright.router.AppNavHost
import com.blas.android_firewright.ui.theme.AndroidfirewrightTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            viewModel.loadPokedex()
            AppNavHost(
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    onItemClick: (Pokemon) -> Unit,
) {
    AndroidfirewrightTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val pokemon by viewModel.pokemonSize.collectAsState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (pokemon.isNotEmpty()) {
                    Text("Pokemon count: ${pokemon.size}")
                }
                LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(3)) {
                    items(pokemon.size) {
                        PokemonItem(name = pokemon[it].name, entryNumber = pokemon[it].entryNumber, onItemClick = onItemClick)
                    }
                }
            }
        }
    }
}

@Composable
fun DetailScreen(
    entryNumber: String,
    modifier: Modifier = Modifier
) {
    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${entryNumber}.png"

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "",
            modifier = Modifier.size(360.dp)
        )
    }
}

@Composable
fun PokemonItem(name: String, entryNumber: Int, modifier: Modifier = Modifier, onItemClick: (Pokemon) -> Unit) {

    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${entryNumber}.png"

    Card(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = MaterialTheme.shapes.medium,
        onClick = {
            onItemClick(Pokemon(entryNumber, name))
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "",
                modifier = Modifier.size(72.dp)
            )
            Text(
                text = name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun PokemonItemPreview() {
    PokemonItem("Dummy Pokemon", 1) {

    }
}