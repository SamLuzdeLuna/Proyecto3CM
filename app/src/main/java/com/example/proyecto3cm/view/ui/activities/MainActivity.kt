package com.example.proyecto3cm.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto3cm.databinding.ActivityMainBinding
import com.example.proyecto3cm.model.Game
import com.example.proyecto3cm.model.PokeApi
import com.example.proyecto3cm.view.adapter.Adaptador
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), Adaptador.OnItemListener {

    private val BASE_URL = "https://pokeapi.co/"
    private val LOGTAG = "LOGS"

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokeApi: PokeApi = retrofit.create(PokeApi::class.java)

        val call: Call<Game> = pokeApi.getGames("api/v2/pokemon?limit=151")

        call.enqueue(object: Callback<Game> {
            override fun onResponse(call: Call<Game>, response: Response<Game>) {
                //Se ejecuta para conexion exitosa
                //Log.d(LOGTAG,"Respuesta del servidor: ${response.toString()}")

                binding.pbConexion.visibility = View.INVISIBLE

                val adaptador = Adaptador(this@MainActivity,response.body()!!,this@MainActivity)
                with(binding){
                    rvMenu.layoutManager = LinearLayoutManager(this@MainActivity)
                    rvMenu.adapter = adaptador
                }
            }

            override fun onFailure(call: Call<Game>, t: Throwable) {
                //Se ejecuta cuando algo falla
                Log.d(LOGTAG,"Error")
                Toast.makeText(this@MainActivity, "Error en la Conexion", Toast.LENGTH_LONG).show()
                binding.pbConexion.visibility = View.INVISIBLE
            }

        })

    }

    override fun onItemClick(game: Game.Animal) {
        val parametros = Bundle()

        parametros.putString("name", game.name)
        val intent = Intent(this, PokeDetalles::class.java)

        intent.putExtras(parametros)

        startActivity(intent)
    }
}