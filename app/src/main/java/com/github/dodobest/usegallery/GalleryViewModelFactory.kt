package com.github.dodobest.usegallery

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GalleryViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            GalleryViewModel::class.java -> createGalleryViewModel()
            else -> throw IllegalArgumentException()
        } as T
    }

    private fun createGalleryViewModel(): GalleryViewModel {
        return GalleryViewModel()
    }
}