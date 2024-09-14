package com.example.kotlin5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: RecipeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecipeAdapter(emptyList())
        recyclerView.adapter = adapter

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val recipeApi = retrofit.create(RecipeApi::class.java)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "recipe-database"
        ).build()

        val repository = RecipeRepository(recipeApi, db.recipeDao())

        val viewModelFactory = RecipeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[RecipeViewModel::class.java]

        viewModel.recipes.observe(this) { recipes ->
            adapter.updateRecipes(recipes)
        }

        viewModel.fetchRecipes()
    }
}