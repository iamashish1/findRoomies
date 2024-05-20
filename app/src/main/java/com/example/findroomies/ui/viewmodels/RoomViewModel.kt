package com.example.findroomies.ui.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.findroomies.data.model.RoomModel
import com.example.findroomies.data.repository.RoomRepository
import com.example.findroomies.data.repository.RoomRepositoryImpl
import kotlinx.coroutines.launch
class RoomViewModel(private val repository: RoomRepository) : ViewModel() {
    private val _rooms = MutableLiveData<List<RoomModel>>()

    private val _initalRooms = MutableLiveData<List<RoomModel>>()

    val rooms: LiveData<List<RoomModel>> get() = _rooms

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    //GET SINGLE ROOM
    private val _singleRoom = MutableLiveData<RoomModel?>()
    val singleRoom: LiveData<RoomModel?> get() = _singleRoom

    init {
        getRooms()
    }

    var searchQuery:ObservableField<String> = ObservableField()




//GET THE LIST OF ALL ROOMS
    private fun getRooms() {
        viewModelScope.launch {
            _loading.value = true
            val roomList = repository.getRooms()
            _initalRooms.value=roomList
            _rooms.value = roomList
            _loading.value = false
        }
    }

//GET THE FILTERED ROOMS
 fun filterRooms(query: String) {
     println(query)
     if(query.trim().isNullOrEmpty()){
         _rooms.value=_initalRooms.value
     }else{
         val filteredRooms = _initalRooms.value?.filter {
             it.title?.contains(query, ignoreCase = true) ?: false
         }
         println("here")
         _rooms.value = filteredRooms ?: emptyList()
     }

}
    //GET SINGLE ROOM
    fun getRoomDetail(documentId: String) {
        viewModelScope.launch {
            _loading.value = true
            val roomDetail = repository.getRoomDetail(documentId)
            _singleRoom.value = roomDetail
            _loading.value = false
        }
    }

    // Define ViewModel factory in a companion object
    // Required to instantiate RoomViewModel with dependencies
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                return RoomViewModel(
                   RoomRepositoryImpl()
                ) as T
            }
        }
    }
}

