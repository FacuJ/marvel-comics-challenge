package com.facundojaton.marvelcomicschallenge.ui.characters_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facundojaton.marvelcomicschallenge.model.MarvelCharacter
import com.facundojaton.marvelcomicschallenge.model.RequestStatus
import com.facundojaton.marvelcomicschallenge.repositories.MarvelRepository
import com.facundojaton.marvelcomicschallenge.utils.APIConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: MarvelRepository) :
    ViewModel() {

    private val _characterList = MutableLiveData<ArrayList<MarvelCharacter>>()
    val marvelCharacterList: LiveData<ArrayList<MarvelCharacter>>
        get() = _characterList

    private val _status = MutableLiveData<RequestStatus>()
    val status: LiveData<RequestStatus>
        get() = _status

   /* private val _selectedcharacter = MutableLiveData<characterDetail>()
    val selectedcharacter: LiveData<characterDetail>
        get() = _selectedcharacter
*/
    var isSearching: Boolean = false
    private val queryParams = HashMap<String, String>()
    private var isScrolling: Boolean = false
    private var page: Int = 1

    init {
        refresh()
    }

    private fun getCharacterList() = viewModelScope.launch {
        if (_status.value != RequestStatus.LOADING) {
            try {
                _status.value = RequestStatus.LOADING
                val characterList = ArrayList<MarvelCharacter>()
                withContext(Dispatchers.IO) {
                    val response: List<MarvelCharacter> = repository.getCharacters()
                    characterList.addAll(response)
                }

                val newList = ArrayList<MarvelCharacter>()
                if (!_characterList.value.isNullOrEmpty()) newList.addAll(_characterList.value!!)
                newList.addAll(characterList)
                _characterList.value = newList
                _status.value = RequestStatus.SUCCESS
            } catch (e: Exception) {
                _status.value = RequestStatus.ERROR
                Log.e(CharactersViewModel::class.java.simpleName, e.message.toString())
            }
        }
    }

    private fun resetPages() {
        _characterList.value = ArrayList()
        page = 1
        queryParams[APIConstants.QueryParams.PAGE] = page.toString()
    }

    fun paginateIfNeeded(
        firstVisibleItemPosition: Int,
        visibleItemCount: Int,
        totalItemCount: Int
    ) {
        val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
        val isNotAtBeginning = firstVisibleItemPosition >= 0
        val shouldPaginate = (status.value != RequestStatus.LOADING) && isAtLastItem &&
                isNotAtBeginning && isScrolling && !isSearching
        if (shouldPaginate) {
            getMoreCharacters()
            isScrolling = false
        }
    }


    private fun getMoreCharacters() {
        page++
        queryParams[APIConstants.QueryParams.PAGE] = page.toString()
        getCharacterList()
    }

    fun onScrollStateTrue() {
        isScrolling = true
    }

    fun refresh() {
        queryParams.clear()
        resetPages()
        getCharacterList()
    }

}