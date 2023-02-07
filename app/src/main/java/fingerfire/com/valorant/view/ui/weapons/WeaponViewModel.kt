package fingerfire.com.valorant.view.ui.weapons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.valorant.data.model.WeaponResponse
import fingerfire.com.valorant.data.repository.ValorantRepository
import kotlinx.coroutines.launch

class WeaponViewModel(private val weaponsRepository: ValorantRepository) : ViewModel() {

    private val weaponsMutableLiveData: MutableLiveData<WeaponResponse> = MutableLiveData<WeaponResponse>()
    val weaponsLiveData: LiveData<WeaponResponse>
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