package com.gonzalezblanchard.pokemonapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gonzalezblanchard.pokemonapi.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var pokemon:Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getPokemon(nombre:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getPokemon("pokemon/$nombre")
            val pokemonresultado = call.body()
            runOnUiThread {
                if(call.isSuccessful){
                    pokemon = pokemonresultado!!
                    mostrarResultado()
                }else{
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    fun mostrarResultado() {
        binding.nombre.text =  "Nombre: ${pokemon.name}"
        binding.peso.text = "Peso: ${pokemon.weight.toString()}"
        binding.txtexperiencia.text = "Experiencia: ${pokemon.base_experience.toString()}"
        binding.txtid.text = "Id: ${pokemon.id.toString()}"

        val foto = pokemon.sprites.front_default

        Picasso.get().load(foto).into(binding.fotopokemon);
    }

    fun buscar(view: android.view.View) {
        getPokemon(binding.txtbusqueda.text.toString())
    }

}