package com.example.bitfitpart1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AddFood : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_food)

        findViewById<Button>(R.id.recordFoodBTN).setOnClickListener {
            val newFood = findViewById<EditText>(R.id.addFoodName).text.toString()
            val newCalories = findViewById<EditText>(R.id.addFoodCalories).text.toString()
            val newFoodGroup = findViewById<EditText>(R.id.addFoodGroup).text.toString()

            /** Adds new food item to the database */
            lifecycleScope.launch(IO) {
                (application as BitFitApplication).db.foodDao().insert(
                    FoodEntity(
                        nameFood = newFood,
                        caloriesFood = newCalories,
                        categoryFood = newFoodGroup
                    )
                )
            }

            /** return to previous screen */
            finish()
        }
    }
}