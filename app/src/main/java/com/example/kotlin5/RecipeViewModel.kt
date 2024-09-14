package com.example.kotlin5

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {

    val recipes: LiveData<List<Recipe>> = repository.getAllRecipes()

    fun fetchRecipes() {
        viewModelScope.launch {
            repository.fetchAndSaveRecipes()
        }
    }
}