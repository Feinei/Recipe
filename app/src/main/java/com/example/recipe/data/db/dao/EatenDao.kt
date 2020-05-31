package com.example.recipe.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.recipe.data.entities.EatenFood

@Dao
interface EatenDao {

    @Insert
    fun insert(food: EatenFood)

    @Query("SELECT * FROM eatenFood")
    fun getTodayFood(): List<EatenFood>

    @Query("SELECT * FROM eatenFood WHERE date >= :from & date <= :to")
    fun getEatenFoodByDate(from: Long, to: Long): List<EatenFood>

    @Delete
    fun deleteFood(food: EatenFood)

    @Query("DELETE FROM eatenFood WHERE id = :id")
    fun deleteFood(id: Long)
}
