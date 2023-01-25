package com.example.kmtestsuitmedia.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kmtestsuitmedia.R
import com.example.kmtestsuitmedia.adapter.UserListAdapter
import com.example.kmtestsuitmedia.databinding.ActivityThirdScreenBinding
import com.example.kmtestsuitmedia.factory.UserViewModelFactory
import com.example.kmtestsuitmedia.network.ApiClient
import com.example.kmtestsuitmedia.network.ApiHelper
import com.example.kmtestsuitmedia.util.Status
import com.example.kmtestsuitmedia.viewmodel.UserViewModel

class ThirdScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var userListAdapter: UserListAdapter
    private var currentPage = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setupRecycler()
        retrieveData()
        setupToolbar()
        setupViews()
        setContentView(binding.root)
    }

    private fun setupRecycler() {
        userListAdapter = UserListAdapter()
        binding.thirdRv.apply {
            layoutManager = LinearLayoutManager(
                this@ThirdScreenActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = userListAdapter
        }
    }

    private fun setupToolbar() {
        binding.thirdToolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        setSupportActionBar(binding.thirdToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.thirdToolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupViews() {
        binding.thirdRvContainer.setOnRefreshListener {
            binding.thirdRvContainer.isRefreshing = false
            retrieveData()
            userListAdapter.notifyDataSetChanged()
        }
    }

    private fun retrieveData() {
        viewModel = ViewModelProvider(
            this,
            UserViewModelFactory(ApiHelper(ApiClient.instance))
        ).get(UserViewModel::class.java)
        viewModel.getUserList(currentPage).observe(this) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.thirdActivityPb.visibility = View.GONE
                        binding.thirdRv.visibility = View.VISIBLE
                        resource.data?.let { response ->
                            userListAdapter.setData(response.data)
                        }
                    }
                    Status.ERROR -> {
                        binding.thirdActivityPb.visibility = View.INVISIBLE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.thirdActivityPb.visibility = View.VISIBLE
                        binding.thirdRv.visibility = View.GONE
                    }
                }
            }
        }

    }
}