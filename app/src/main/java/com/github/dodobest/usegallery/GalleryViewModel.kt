package com.github.dodobest.usegallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {
    private val _checkPermission = MutableLiveData<Event<Unit>>()
        val checkPermission: LiveData<Event<Unit>>
            get() =_checkPermission

    fun loadGallery() {
        _checkPermission.value = Event(Unit)
    }
}