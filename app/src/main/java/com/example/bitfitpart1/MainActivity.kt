package com.example.bitfitpart1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val food = mutableListOf<FoodEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** Set up the Recycle View Food Adapter with articles */
        val foodRecyclerView : RecyclerView = findViewById(R.id.rvFoodList)
        val foodAdapter = FoodRecycleViewAdapter(this, food)

        lifecycleScope.launch {
            (application as BitFitApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    FoodEntity(entity.nameFood, entity.caloriesFood, entity.categoryFood)
                }.also { mappedList ->
                    food.clear()
                    food.addAll(mappedList)
                    foodAdapter.notifyDataSetChanged()
                }
            }
        }

        foodRecyclerView.adapter = foodAdapter
        foodRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            foodRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        /** Set button onclick listener to navigate to AddFood screen*/
        findViewById<Button>(R.id.addFoodBTN).setOnClickListener{
            val intent = Intent(this, AddFood::class.java)
            startActivity(intent)
        }
    }
}