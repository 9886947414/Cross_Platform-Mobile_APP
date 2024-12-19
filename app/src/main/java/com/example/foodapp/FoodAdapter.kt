package com.example.foodapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class FoodAdapter(private val context: Context, private val foodItems: List<FoodItem>) : BaseAdapter() {

    override fun getCount(): Int {
        return foodItems.size
    }

    override fun getItem(position: Int): Any {
        return foodItems[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.list_item_food, parent, false)

            holder = ViewHolder(
                view.findViewById(R.id.food_name),
                view.findViewById(R.id.food_price),
                view.findViewById(R.id.order_button)
            )

            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val foodItem = foodItems[position]

        holder.foodName.text = foodItem.name
        holder.foodPrice.text = "$${foodItem.price}"

        // Handle button click
        holder.orderButton.setOnClickListener {
            // Example action when the button is clicked
            // You can show a toast or do something else
            println("${foodItem.name} ordered!")
        }

        return view
    }

    private class ViewHolder(
        val foodName: TextView,
        val foodPrice: TextView,
        val orderButton: Button
    )
}
