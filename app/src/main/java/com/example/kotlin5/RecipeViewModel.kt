package com.example.kotlin5

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {

    val recipes: LiveData<List<Recipe>> = repository.getAllRecipes()

    fun fetchRecipes() {
        viewModelScope.launch {
            repository.fetchAndSaveRecipes()
        }
    }
}