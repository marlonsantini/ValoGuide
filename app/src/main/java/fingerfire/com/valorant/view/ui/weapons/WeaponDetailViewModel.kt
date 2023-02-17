package fingerfire.com.valorant.view.ui.weapons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valorant.data.response.WeaponDetailResponse
import fingerfire.com.valorant.data.repository.ValorantRepository
import kotlinx.coroutines.launch

class WeaponDetailViewModel(private val weaponsRepository: ValorantRepository) : ViewModel() {

    private val weaponsDetailMutableLiveData: MutableLiveData<WeaponDetailResponse> =
        MutableLiveData<WeaponDetailResponse>()
    val weaponsDetailLiveData: LiveData<WeaponDetailResponse>
        get() {
            return weaponsDetailMutableLiveData
        }

    fun getWeaponDetail(uuid: String) {
        viewModelScope.launch {
            val weaponDetailResponse = weaponsRepository.getWeaponDetail(uuid)
            weaponsDetailMutableLiveData.postValue(weaponDetailResponse)
        }
    }
}