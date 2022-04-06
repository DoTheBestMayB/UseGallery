package com.github.dodobest.usegallery

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.github.dodobest.usegallery.databinding.ActivityMainBinding
import com.github.dodobest.usegallery.util.requestPermissionsCompat
import com.github.dodobest.usegallery.util.showSnackbar
import com.github.dodobest.usegallery.util.shouldShowRequestPermissionRationaleCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: GalleryViewModel by viewModels { GalleryViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.checkPermission.observe(this) {
            if (it.consumed) return@observe
            setupPermission()
            it.consume()
        }
    }

    private fun setupPermission() {
        val permission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
            return
        }
    }

    private fun makeRequest() {
        if (shouldShowRequestPermissionRationaleCompat(
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            binding.root.showSnackbar(R.string.storage_access_required,
                Snackbar.LENGTH_INDEFINITE, R.string.ok
            ) {
                requestPermissionsCompat(
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_READ_EXTERNAL_STORAGE
                )
            }
        } else {
            binding.root.showSnackbar(R.string.storage_permission_not_available,
                Snackbar.LENGTH_SHORT
            )

            requestPermissionsCompat(
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_READ_EXTERNAL_STORAGE
            )
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 0
    }
}