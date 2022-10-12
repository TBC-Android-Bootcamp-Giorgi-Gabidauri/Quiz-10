package com.gabo.quiz10.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabo.quiz10.comon.extensions.launchStarted
import com.gabo.quiz10.databinding.ActivityMainBinding
import com.gabo.quiz10.ui.adapter.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var chatAdapter: ChatAdapter
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
        setupObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getChatInfo()
        }
    }

    private fun setupObservers() {
        launchStarted {
            viewModel.state.collect {
                binding.swipeRefreshLayout.isRefreshing = it.loading
                controlRefresh()
                when {
                    it.data.isNotEmpty() -> chatAdapter.submitList(it.data)
                    it.errorMsg.isNotEmpty() -> {
                        Toast.makeText(this, it.errorMsg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun controlRefresh() {
        if (binding.swipeRefreshLayout.isRefreshing) {
            binding.rvChat.visibility = View.GONE
        } else {
            binding.rvChat.visibility = View.VISIBLE
        }
    }

    private fun setupAdapter() {
        chatAdapter = ChatAdapter()
        binding.rvChat.adapter = chatAdapter
        binding.rvChat.layoutManager = LinearLayoutManager(this)
    }
}