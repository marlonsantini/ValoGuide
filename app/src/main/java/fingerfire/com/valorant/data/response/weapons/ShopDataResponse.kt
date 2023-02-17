package fingerfire.com.valorant.data.response.weapons

import com.squareup.moshi.Json

data class ShopDataResponse(
    @Json(name = "cost")
    val cost: Int,
    @Json(name = "categoryText")
    val categoryText: String
)