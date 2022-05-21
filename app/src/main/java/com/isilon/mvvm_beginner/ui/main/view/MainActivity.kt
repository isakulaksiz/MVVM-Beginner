package com.isilon.mvvm_beginner.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.isilon.mvvm_beginner.R
import com.isilon.mvvm_beginner.data.api.ApiHelper
import com.isilon.mvvm_beginner.data.api.ApiServiceImpl
import com.isilon.mvvm_beginner.data.model.User
import com.isilon.mvvm_beginner.ui.base.ViewModelFactory
import com.isilon.mvvm_beginner.ui.main.adapter.MainAdapter
import com.isilon.mvvm_beginner.ui.main.viewmodel.MainViewModel
import com.isilon.mvvm_beginner.utils.Status

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidNetworking.initialize(getApplicationContext());

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        setUpUI()
        setUpViewModel()
        setUpObserver()
    }

    private fun setUpObserver() {
        mainViewModel.getUsers().observe(this, Observer {
            when (it.status){
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING ->{
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }

                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setUpViewModel() {
        mainViewModel = ViewModelProviders.of(this,
            ViewModelFactory(ApiHelper(ApiServiceImpl())))
            .get(MainViewModel::class.java)
    }

    private fun setUpUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation)
        )

        recyclerView.adapter = adapter
    }

    private fun renderList(users: List<User>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }
}