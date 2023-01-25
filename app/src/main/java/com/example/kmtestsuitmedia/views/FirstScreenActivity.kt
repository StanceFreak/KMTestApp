package com.example.kmtestsuitmedia.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kmtestsuitmedia.databinding.ActivityFirstScreenBinding

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setupViews()
        setContentView(binding.root)
    }

    private fun setupViews() {
        binding.firstBtnCheck.setOnClickListener {
            val plaindromeInput = binding.firstEtPalindrome.text.toString()
            val palindromeCheck = plaindromeInput.reversed()
            if (plaindromeInput.equals(palindromeCheck, ignoreCase = true)) {
                Toast.makeText(this, "isPalindrome", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this, "not palindrome", Toast.LENGTH_LONG).show()
            }
        }

        binding.firstBtnNext.setOnClickListener {
            val nameInput = binding.firstEtName.text.toString()
            if (nameInput.isEmpty()) {
                Toast.makeText(this, "Please insert your name!", Toast.LENGTH_LONG).show()
            }
            else {
                val i = Intent(this, SecondScreenActivity::class.java)
                i.putExtra(SecondScreenActivity.NAME_EXTRAS, nameInput)
                startActivity(i)
            }
        }
    }

}