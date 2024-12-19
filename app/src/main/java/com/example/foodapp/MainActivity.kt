package com.example.foodapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodapp.ui.theme.FoodAppTheme

class MainActivity : ComponentActivity() {
    private lateinit var editTextEmployeeId: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var dbHelper: DatabaseHelper

    private fun isValidEmployee1(employeeId: String, password: String): Boolean {
        // Validate employee from database (simulated here)
        var triple = dbHelper.getEmployee(employeeId.toInt());
        /*if (triple != null) {
            print(triple.first)
            Toast.makeText(this, triple.first, Toast.LENGTH_SHORT).show()
        }
        if (triple != null) {
            print(triple.second)
            Toast.makeText(this, triple.second.toString(), Toast.LENGTH_SHORT).show()
        }
        if (triple != null) {
            print(triple.third)
        }*/
        if (triple != null) {
            Toast.makeText(this, triple.third.toString(), Toast.LENGTH_SHORT).show()
            var ret = employeeId == triple.third.toString()
            return ret ;//ben&& password == "password"
        }
        else
            return false;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editTextEmployeeId = findViewById(R.id.editTextEmployeeId)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)

        dbHelper = DatabaseHelper(this)

        // Insert an employee record
        var employeeId = 101
        var employeeName = "John Doe"
        var walletBalance = 250.75

        var rowId = dbHelper.insertEmployee(employeeId, employeeName, walletBalance)
        if (rowId != -1L) {
            Toast.makeText(this, "Employee inserted successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Failed to insert employee", Toast.LENGTH_SHORT).show()
        }

        dbHelper = DatabaseHelper(this)

        // Insert an employee record
        employeeId = 102
        employeeName = "John Doe"
        walletBalance = 250.75

        rowId = dbHelper.insertEmployee(employeeId, employeeName, walletBalance)
        if (rowId != -1L) {
            Toast.makeText(this, "Employee inserted successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Failed to insert employee", Toast.LENGTH_SHORT).show()
        }
        // Login button click listener
        buttonLogin.setOnClickListener {
            val employeeId = editTextEmployeeId.text.toString()
            val password = editTextPassword.text.toString()
            Toast.makeText(this, "valid credentials111", Toast.LENGTH_SHORT).show()
            if (isValidEmployee1(employeeId, password)) {
                Toast.makeText(this, "valid credentials", Toast.LENGTH_SHORT).show()
                //loadEmployeeData(employeeId)
                // Create an Intent to start the SecondActivity
                //val intent = Intent(this, FoodItemsView::class.java)
                //startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

}


    // Function to simulate employee validation (this should be replaced with actual database query)
// Function to load employee data (balance for the demo)
private fun loadEmployeeData(employeeId: String) {
    // Simulate retrieving employee balance from the database
    val employeeBalance = 50.0 // Example balance
   // textViewBalance.text = "Balance: $$employeeBalance"
}
private fun isValidEmployee(employeeId: String, password: String): Boolean {
    // Validate employee from database (simulated here)
    return employeeId == "123" && password == "password"
    //return true;
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodAppTheme {
        Greeting("Android")
    }
}