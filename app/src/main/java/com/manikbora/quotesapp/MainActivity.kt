package com.manikbora.quotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.manikbora.quotesapp.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var quoteNo = 0
    private lateinit var quotesArray: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quotesArray = resources.getStringArray(R.array.quotes)

        binding.nextQuoteBtn.setOnClickListener {
            if(binding.nextQuoteBtn.text.equals("Close App")) {
                val builder = AlertDialog.Builder(this)
                    .setTitle("Confirm")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", null)
                    .setNegativeButton("No", null)
                    .show()

                val positiveButton = builder.getButton(AlertDialog.BUTTON_POSITIVE)
                positiveButton.setOnClickListener {
                    exitProcess(0)
                }
            } else {
                if(quoteNo < 10) {
                    setNextQuote(quoteNo)
                    quoteNo++
                } else {
                    binding.quoteTv.text = getString(R.string.last_quotes_message)
                    binding.nextQuoteBtn.text = getString(R.string.close_app)
                }
            }
        }
    }

    private fun setNextQuote(nextQuoteNo: Int) {
        binding.quoteTv.text = quotesArray[nextQuoteNo]
    }
}