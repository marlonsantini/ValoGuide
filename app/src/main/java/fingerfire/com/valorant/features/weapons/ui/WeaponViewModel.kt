package fingerfire.com.valorant.features.weapons.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valorant.features.weapons.data.repository.WeaponsRepository
import fingerfire.com.valorant.features.weapons.data.response.WeaponResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class WeaponViewModel(private val weaponsRepository: WeaponsRepository) : ViewModel() {

    private val weaponsMutableLiveData: MutableLiveData<Response<WeaponResponse>> =
        MutableLiveData<Response<WeaponResponse>>()
    val weaponsLiveData: LiveData<Response<WeaponResponse>>
        get() {
            return weaponsMutableLiveData
        }

    fun getWeapons() {
        viewModelScope.launch {
            val weaponResponse = weaponsRepository.loadWeapons()
            weaponsMutableLiveData.postValue(weaponResponse)
        }
    }
}