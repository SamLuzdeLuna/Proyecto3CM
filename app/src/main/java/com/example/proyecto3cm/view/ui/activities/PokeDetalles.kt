package com.example.proyecto3cm.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.proyecto3cm.databinding.ActivityPokeDetallesBinding
import com.example.proyecto3cm.model.PokeApi
import com.example.proyecto3cm.model.PokeDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeDetalles : AppCompatActivity() {

    private lateinit var binding: ActivityPokeDetallesBinding

    private val BASE_URL = "https://pokeapi.co/"
    private val LOGTAG = "LOGS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPokeDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        val name = bundle?.getString("name","0")

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokeApi: PokeApi = retrofit.create(PokeApi::class.java)

        val call: Call<PokeDetail> = pokeApi.getPokemonDetail(name)

        call.enqueue(object: Callback<PokeDetail>{
            override fun onResponse(call: Call<PokeDetail>, response: Response<PokeDetail>) {
                with(binding){
                    pbConexion.visibility = View.INVISIBLE

                    tvName.text = response.body()?.name
                    tvBaseExperience.text = response.body()?.baseExperience
                    tvHeight.text = response.body()?.height
                    tvWeight.text = response.body()?.weight

                    Glide.with(this@PokeDetalles)
                        .load(response.body()?.sprites?.other?.officialArtwork?.frontDefault)
                        .into(binding.ivPokemon)

                }
            }

            override fun onFailure(call: Call<PokeDetail>, t: Throwable) {
                with(binding){
                    pbConexion.visibility = View.INVISIBLE

                    Toast.makeText(this@PokeDetalles,"La conexion ha fallado", Toast.LENGTH_LONG).show()
                }
            }

        })



    }
}