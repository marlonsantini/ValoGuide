package fingerfire.com.valorant.features.maps.ui.viewstate

import fingerfire.com.valorant.features.maps.data.response.MapResponse

data class MapViewState(val sucess: MapResponse? = null, val failure: Boolean = false)