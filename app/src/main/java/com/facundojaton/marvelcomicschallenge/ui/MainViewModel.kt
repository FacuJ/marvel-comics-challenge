package com.facundojaton.marvelcomicschallenge.ui

import androidx.lifecycle.ViewModel
import com.facundojaton.marvelcomicschallenge.repositories.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val repository: MarvelRepository
    ) : ViewModel() {

    /*private val _spinner = MutableStateFlow(true)
    val spinner: StateFlow<Boolean> get() = _spinner

    val movies: Flow<List<Movie>> get() = repository.getMovies()

    init {
        viewModelScope.launch { notifyLastVisible(0) }
    }

    suspend fun notifyLastVisible(lastVisible: Int) {
        repository.checkRequireNewPage(lastVisible)
        _spinner.value = false
    }*/
}