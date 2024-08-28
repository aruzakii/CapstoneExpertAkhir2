package com.example.capstone.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone.R
import com.example.capstone.databinding.ActivityMainBinding
import com.example.capstone.detail.DetailActivity
import com.example.core.data.Resource
import com.example.core.ui.GithubAdapter
import com.google.android.material.appbar.MaterialToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()
   private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = findViewById<MaterialToolbar>(R.id.menuActivity)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            finish()
        }
        val githubAdapter = GithubAdapter()
        githubAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("EXTRA_USERNAME", selectedData.login)
            startActivity(intent)
        }

        binding.searchBar.setOnClickListener  {
            binding.searchView.setupWithSearchBar(binding.searchBar)
            binding.searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event->
                    val query = textView.text.toString()
                    binding.searchBar.setText(query)
                    binding.searchView.hide()
                    mainViewModel.getAllGithub(query).observe(this){github ->
                        if (github != null) {
                            when (github) {
                                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                                is Resource.Success -> {
                                    binding.progressBar.visibility = View.GONE
                                    githubAdapter.setData(github.data)
                                }
                                is Resource.Error -> {
                                    binding.progressBar.visibility = View.GONE
//                        binding.viewError.root.visibility = View.VISIBLE
//                        binding.viewError.tvError.text = tourism.message ?: getString(R.string.something_wrong)
                                }
                            }
                        }
                    }
                    false
                }
        }


        with(binding.rvUsers) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = githubAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu_appbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_favorite ->{
                val uri = Uri.parse("capstonapp://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}