package com.facundojaton.marvelcomicschallenge.ui.character_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facundojaton.marvelcomicschallenge.model.MarvelCharacter
import com.facundojaton.marvelcomicschallenge.model.MarvelComic
import com.facundojaton.marvelcomicschallenge.model.MarvelEvent
import com.facundojaton.marvelcomicschallenge.model.RequestStatus
import com.facundojaton.marvelcomicschallenge.repositories.MarvelRepository
import com.facundojaton.marvelcomicschallenge.utils.APIConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(private val repository: MarvelRepository) :
    ViewModel() {

    private val _comicsList = MutableLiveData<ArrayList<MarvelComic>>()
    val comicsList: LiveData<ArrayList<MarvelComic>>
        get() = _comicsList

    private val _status = MutableLiveData<RequestStatus>()
    val status: LiveData<RequestStatus>
        get() = _status

    lateinit var character : MarvelCharacter

    fun getComicsList() = viewModelScope.launch {
        if (_status.value != RequestStatus.LOADING) {
            try {
                _status.value = RequestStatus.LOADING
                val comics = ArrayList<MarvelComic>()
                withContext(Dispatchers.IO) {
                    val response: List<MarvelComic> = repository.getCharacterComics(character.id.toString())
                    comics.addAll(response)
                }

                val newList = ArrayList<MarvelComic>()
                if (!_comicsList.value.isNullOrEmpty()) newList.addAll(_comicsList.value!!)
                newList.addAll(comics)
                _comicsList.value = newList
                _status.value = RequestStatus.SUCCESS
            } catch (e: Exception) {
                _status.value = RequestStatus.ERROR
                Log.e(CharacterDetailsViewModel::class.java.simpleName, e.message.toString())
            }
        }
    }
}