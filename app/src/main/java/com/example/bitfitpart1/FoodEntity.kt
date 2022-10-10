package com.example.bitfitpart1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class FoodEntity (
    @ColumnInfo val nameFood : String,
    @ColumnInfo val caloriesFood : String,
    @ColumnInfo val categoryFood: String?,
    @PrimaryKey(autoGenerate = true) val id : Long =0,
)