package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        /* get cost from textView with method chaining
        * -- text will return textView, not String, so convert with toString() method */
        val cost = binding.costOfService.text.toString().toDouble()

        /* Get tip percentage from radio group
        * -- get from attribute: checked radio id */
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost

        if(binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip) // use kotlin math to round up the tip
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip) // use java.text NumberFormat

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip) // set the calculated tip
    }
}