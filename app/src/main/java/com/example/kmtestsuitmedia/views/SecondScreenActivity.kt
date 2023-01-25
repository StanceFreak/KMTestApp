package com.example.kmtestsuitmedia.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kmtestsuitmedia.R
import com.example.kmtestsuitmedia.adapter.UserListAdapter
import com.example.kmtestsuitmedia.databinding.ActivitySecondScreenBinding
import com.example.kmtestsuitmedia.model.Data

class SecondScreenActivity : AppCompatActivity() {
    companion object {
        const val NAME_EXTRAS = "name_extras"
    }
    private lateinit var binding: ActivitySecondScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setupViews()
        setupToolbar()
        setContentView(binding.root)
    }

    private fun setupToolbar() {
        binding.secondToolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        setSupportActionBar(binding.secondToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.secondToolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupViews() {
        val nameExtras = intent.getStringExtra(NAME_EXTRAS)
        val selectedNameExtras = intent.getParcelableExtra<Data>(UserListAdapter.SELECTED_USER_NAME)
        binding.apply {
            secondTvUsername.text = nameExtras
            if (selectedNameExtras != null) {
                secondTvSelectedUser.text = "${selectedNameExtras.firstName}" + " " + "${selectedNameExtras.lastName}"
            }
            secondBtn.setOnClickListener {
                val i = Intent(this@SecondScreenActivity, ThirdScreenActivity::class.java)
                startActivity(i)
            }
        }
    }

}