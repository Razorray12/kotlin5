package com.example.kotlin5

import androidx.lifecycle.LiveData
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val recipeApi: RecipeApi, private val recipeDao: RecipeDao) {

    suspend fun fetchAndSaveRecipes() {
        val recipes = recipeApi.getRecipes(3).recipes
        recipeDao.insertRecipes(recipes)
    }

    fun getAllRecipes(): LiveData<List<Recipe>> {
        return recipeDao.getAllRecipes()
    }
}