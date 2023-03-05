package fingerfire.com.valorant.features.weapons.data.repository

import fingerfire.com.valorant.api.ValorantApi
import fingerfire.com.valorant.features.weapons.data.response.WeaponDetailResponse
import fingerfire.com.valorant.features.weapons.data.response.WeaponResponse
import fingerfire.com.valorant.util.Util

class WeaponsRepository(private val valorantApi: ValorantApi) {

    suspend fun loadWeapons(): WeaponResponse {
        return valorantApi.getWeapons(Util.setAppLanguage)
    }

    suspend fun getWeaponDetail(weaponUuid: String): WeaponDetailResponse {
        return valorantApi.getWeaponsUuid(weaponUuid, Util.setAppLanguage)
    }
}