package com.facundojaton.marvelcomicschallenge.ui.events_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class EventsViewModel @Inject constructor(private val repository: MarvelRepository) :
    ViewModel() {

    private val _eventsList = MutableLiveData<ArrayList<MarvelEvent>>()
    val eventsList: LiveData<ArrayList<MarvelEvent>>
        get() = _eventsList

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

    private fun resetPages() {
        _eventsList.value = ArrayList()
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
            getMoreEvents()
            isScrolling = false
        }
    }


    private fun getMoreEvents() {
        page++
        queryParams[APIConstants.QueryParams.PAGE] = page.toString()
        getEventsList()
    }

    fun onScrollStateTrue() {
        isScrolling = true
    }

    fun refresh() {
        queryParams.clear()
        resetPages()
        getEventsList()
    }

}