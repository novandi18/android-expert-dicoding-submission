package com.novandi.doaharianku.detail

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.novandi.core.domain.model.Pray
import com.novandi.doaharianku.R
import com.novandi.doaharianku.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding
    private var isMenuClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.appbar.setNavigationOnClickListener { finish() }

        val detailPray = intent.getParcelableExtra<Pray>(EXTRA_DATA)
        showDetail(detailPray)

        detailViewModel.favorite.observe(this) { isFavorite ->
            binding.appbar.menu.findItem(R.id.set_favorite_menu).icon = setFavoriteIcon(isFavorite)
            if (isMenuClicked) showToast(isFavorite)
        }
    }

    private fun showDetail(detailPray: Pray?) {
        detailPray?.let {
            detailViewModel.setFavorite(it.isFavorite)

            binding.appbar.subtitle = it.doa
            binding.tvDetailAyat.text = it.ayat
            binding.tvDetailLatin.text = it.latin
            binding.tvDetailArtinya.text = it.artinya

            var favoriteStatus = it.isFavorite
            binding.appbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.set_favorite_menu -> {
                        isMenuClicked = true
                        menuItem.isChecked = !menuItem.isChecked
                        favoriteStatus = !favoriteStatus
                        detailViewModel.setFavoritePray(detailPray, favoriteStatus)

                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun setFavoriteIcon(isFavorite: Boolean): Drawable? {
        return ContextCompat.getDrawable(this,
            if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_outlined
        )
    }

    private fun showToast(isFavorite: Boolean) {
        Toast.makeText(
            this@DetailActivity,
            if (isFavorite) getString(R.string.alert_set_fav) else getString(R.string.alert_delete_fav),
            Toast.LENGTH_SHORT
        ).show()
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}