package com.novandi.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.novandi.core.di.FavoriteModuleDependencies
import com.novandi.core.domain.model.Pray
import com.novandi.core.ui.PrayAdapter
import com.novandi.doaharianku.detail.DetailActivity
import com.novandi.favorite.databinding.ActivityFavoriteBinding
import com.novandi.favorite.di.DaggerFavoriteComponent
import com.novandi.favorite.viewmodel.FavoriteViewModel
import com.novandi.favorite.viewmodel.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val prayAdapter by lazy { PrayAdapter() }

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.appbar.setNavigationOnClickListener { finish() }

        with(binding.rvPray) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = prayAdapter
        }

        favoriteViewModel.favoritePray.observe(this) { pray ->
            binding.rvPray.visibility = if (pray.isNotEmpty()) View.VISIBLE else View.INVISIBLE
            prayAdapter.differ.submitList(pray)
            binding.viewEmpty.root.visibility = if (pray.isNotEmpty()) View.GONE else View.VISIBLE
        }

        prayAdapter.setOnItemClick(object : PrayAdapter.OnItemClick {
            override fun onItemClick(data: Pray) {
                val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, data)
                startActivity(intent)
            }
        })
    }
}