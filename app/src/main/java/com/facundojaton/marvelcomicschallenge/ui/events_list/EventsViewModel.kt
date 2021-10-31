package com.facundojaton.marvelcomicschallenge.ui.events_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facundojaton.marvelcomicschallenge.model.MarvelComic
import com.facundojaton.marvelcomicschallenge.model.MarvelEvent
import com.facundojaton.marvelcomicschallenge.model.RequestStatus
import com.facundojaton.marvelcomicschallenge.repositories.MarvelRepository
import com.facundojaton.marvelcomicschallenge.ui.character_detail.CharacterDetailsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(private val repository: MarvelRepository) :
    ViewModel() {

    private val _eventsList = MutableLiveData<ArrayList<MarvelEvent>>()
    val eventsList: LiveData<ArrayList<MarvelEvent>>
        get() = _eventsList

    private val _status = MutableLiveData<RequestStatus>()
    val status: LiveData<RequestStatus>
        get() = _status

    private val _changedItemId = MutableLiveData<String>()
    val changedItemId: LiveData<String>
        get() = _changedItemId

    private val _emptyComicsResponse = MutableLiveData<Boolean>()
    val emptyComicsResponse: LiveData<Boolean>
        get() = _emptyComicsResponse

    var isSearching: Boolean = false
    private val queryParams = HashMap<String, String>()
    private var isScrolling: Boolean = false
    private var page: Int = 1
    private var comicsPage: Int = 1

    init {
        getEventsList()
    }

    private fun getEventsList() = viewModelScope.launch {
        if (_status.value != RequestStatus.LOADING) {
            try {
                _status.value = RequestStatus.LOADING
                val eventList = ArrayList<MarvelEvent>()
                withContext(Dispatchers.IO) {
                    val response: List<MarvelEvent> = repository.getEvents()
                    eventList.addAll(response)
                }
                val newList = ArrayList<MarvelEvent>()
                if (!_eventsList.value.isNullOrEmpty()) newList.addAll(_eventsList.value!!)
                newList.addAll(eventList)
                _eventsList.value = newList
                _status.value = RequestStatus.SUCCESS
            } catch (e: Exception) {
                _status.value = RequestStatus.ERROR
                Log.e(EventsViewModel::class.java.simpleName, e.message.toString())
            }
        }
    }

    fun getEventComics(eventId: String) = viewModelScope.launch {
        try {
            _status.value = RequestStatus.LOADING
            val comics = ArrayList<MarvelComic>()
            withContext(Dispatchers.IO) {
                val response: List<MarvelComic> =
                    repository.getSingleEventComics(eventId, comicsPage)
                comics.addAll(response)
            }
            addComicsToEvents(comics)
            if (comics.size > 98) getMoreComics(eventId)
            else comicsPage = 1
            if (comics.isEmpty()) _emptyComicsResponse.value = true
            _changedItemId.value = eventId
            _status.value = RequestStatus.SUCCESS
        } catch (e: Exception) {
            _status.value = RequestStatus.ERROR
            Log.e(CharacterDetailsViewModel::class.java.simpleName, e.message.toString())
        }
    }

    private fun addComicsToEvents(comics: ArrayList<MarvelComic>) {
        _eventsList.value?.let { events ->
            events.map { event ->
                comics.map { comic ->
                    comic.events?.items?.map { comicEventSummary ->
                        if (comicEventSummary.name == event.title) {
                            if (event.marvelComics == null) event.marvelComics = ArrayList()
                            if (event.marvelComics?.contains(comic) == false) event.marvelComics?.add(
                                comic
                            )
                        }
                    }
                }
            }
        }
        notifyEventListChanged()
    }

    private fun notifyEventListChanged() {
        val list = eventsList.value
        _eventsList.value = list!!
    }

    private fun getMoreComics(eventId: String) {
        comicsPage++
        getEventComics(eventId)
    }

    fun clearEmptyComicsResponse(){
        _emptyComicsResponse.value = null
    }

}