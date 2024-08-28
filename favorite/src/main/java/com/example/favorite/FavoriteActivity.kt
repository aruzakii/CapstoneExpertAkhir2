package com.example.favorite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone.detail.DetailActivity
import com.example.core.domain.model.Github
import com.example.core.domain.model.GithubDetail
import com.example.core.ui.GithubAdapter
import com.example.favorite.databinding.ActivityFavoriteBinding
import com.google.android.material.appbar.MaterialToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    val githubAdapter = GithubAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(favoriteModule)

        val toolbar = findViewById<MaterialToolbar>(com.example.favorite.R.id.menuActivity2)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        githubAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("EXTRA_USERNAME", selectedData.login)
            startActivity(intent)
        }

        favoriteViewModel.gettAllFavorite().observe(this){data ->
               setDataAdaptaer(data)
        }



        with(binding.favorite) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = githubAdapter
        }

    }

    private fun setDataAdaptaer(githubDetail: List<GithubDetail>){
        val listGithub = githubDetail.map { detail ->
            Github(
                id = detail.id, // Sesuaikan dengan nama properti yang sesuai
                avatarUrl = detail.avatarUrl,
                login = detail.login,
            )
        }
        githubAdapter.setData(listGithub)
    }


}