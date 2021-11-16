package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btn.setOnClickListener {
            calculateTip()
        }

    }

    private fun calculateTip() {

        //Get the Input Cost
        val cost= binding.cost.text.toString().toDoubleOrNull()

        if (cost == null || cost == 0.00) {
            binding.tipResult.text = ""
            Toast.makeText(this, "Please Enter a Valid cost of Service", Toast.LENGTH_LONG).show()
            return
        }
        
        //If User doesn't enter any value

        //Get the Button checked

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_20 -> 0.20
            R.id.option_18 -> 0.18
            else  -> 0.15
        }

        var tip = tipPercentage * cost


        //Checking If Roundup

        if (binding.switchToggle.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        //Getting the formatted Result.
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.Tip_amount, formattedTip)
    }

}