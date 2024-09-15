package com.example.kotlin5

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun providesRecipesDb(app: Application): RecipesDatabase{
        return Room.databaseBuilder(
            app,
            RecipesDatabase::class.java, "recipe-database"
        ).build()
    }

    @Provides
    fun provideRecipeDao(database: RecipesDatabase): RecipeDao {
        return database.recipeDao()
    }
}