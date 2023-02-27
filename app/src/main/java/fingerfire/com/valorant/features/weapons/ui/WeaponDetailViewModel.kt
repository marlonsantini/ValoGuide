package fingerfire.com.valorant.features.weapons.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valorant.features.weapons.data.repository.WeaponsRepository
import fingerfire.com.valorant.features.weapons.data.response.WeaponDetailResponse
import kotlinx.coroutines.launch

class WeaponDetailViewModel(private val weaponsRepository: WeaponsRepository) : ViewModel() {

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