import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findroomies.data.model.FurnishingType
import com.example.findroomies.data.model.PropertyType
import com.example.findroomies.data.model.RoomModel
import com.example.findroomies.data.repositoy.RoomRepository
import kotlinx.coroutines.launch
class RoomViewModel() : ViewModel() {
    private val _rooms = MutableLiveData<List<RoomModel>>()
    val rooms: LiveData<List<RoomModel>> get() = _rooms

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    init {
        getRooms()
    }

    private fun getRooms() {
        viewModelScope.launch {
            _loading.value = true
            val roomList = arrayOf(
                RoomModel("asdas", "asdas", "asdas", "asdas", "asdas", false, FurnishingType.FULLY_FURNISHED, PropertyType.HOUSE)
            ).toList()
            _rooms.value = roomList
            _loading.value = false
        }
    }
}
