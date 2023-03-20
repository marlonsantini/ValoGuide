package fingerfire.com.valorant.extensions

import android.graphics.Color
import android.graphics.drawable.GradientDrawable

fun createGradientDrawable(
    startColor: String,
    centerColor: String,
    endColor: String
): GradientDrawable {
    val startColorInt = Color.parseColor(startColor)
    val centerColorInt = Color.parseColor(centerColor)
    val endColorInt = Color.parseColor(endColor)
    val colors = intArrayOf(startColorInt, centerColorInt, endColorInt)
    return GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, colors)
}