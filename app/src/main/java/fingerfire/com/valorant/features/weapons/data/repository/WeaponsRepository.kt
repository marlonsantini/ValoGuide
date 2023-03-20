package fingerfire.com.valorant.features.weapons.data.repository

import fingerfire.com.valorant.api.ValorantApi
import fingerfire.com.valorant.extensions.getAppLanguage
import fingerfire.com.valorant.features.weapons.data.response.WeaponDetailResponse
import fingerfire.com.valorant.features.weapons.data.response.WeaponResponse
import retrofit2.Response

class WeaponsRepository(private val valorantApi: ValorantApi) {

    suspend fun loadWeapons(): Response<WeaponResponse> {
        return valorantApi.getWeapons(getAppLanguage())
    }

    suspend fun getWeaponDetail(weaponUuid: String): WeaponDetailResponse {
        return valorantApi.getWeaponsUuid(weaponUuid, getAppLanguage())
    }
}