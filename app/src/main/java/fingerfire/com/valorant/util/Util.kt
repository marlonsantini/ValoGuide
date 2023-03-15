package fingerfire.com.valorant.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.net.ConnectivityManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import java.util.*

class Util() {

    companion object {

        val setAppLanguage: String
            get() {
                return Locale.getDefault().toLanguageTag()
            }
    }

    fun createGradientDrawable(
        startColor: String,
        centerColor: String,
        endColor: String
    ): GradientDrawable {
        val startColorInt = Color.parseColor(startColor)
        val centerColor = Color.parseColor(centerColor)
        val endColorInt = Color.parseColor(endColor)
        val colors = intArrayOf(startColorInt, centerColor, endColorInt)
        return GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, colors)
    }

    fun initAdMob(adView: AdView) {
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    fun showDialog(activity: Activity, msg: String) {
        val dialog = AlertDialog.Builder(activity)
            .setTitle("Valorant")
            .setMessage(msg)
            .setPositiveButton("OK") { _, _ ->
                activity.finish()
            }
            .create()

        dialog.show()
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        capabilities.also {
            return it != null
        }
    }
}