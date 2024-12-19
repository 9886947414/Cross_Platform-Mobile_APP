package com.example.foodapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class FoodItemsView : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_items_view)
        val foodItems = listOf(
            FoodItem("Pizza", 8.99),
            FoodItem("Burger", 5.49),
            FoodItem("Pasta", 7.99),
            FoodItem("Salad", 4.50),
            FoodItem("Sushi", 12.00)
        )

        val listView: ListView = findViewById(R.id.food_list_view)
        val adapter = FoodAdapter(this, foodItems)
        listView.adapter = adapter
    }
}