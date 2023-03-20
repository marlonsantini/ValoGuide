package fingerfire.com.valorant.extensions

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager

fun Activity.isInternetAvailable(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    capabilities.also {
        return it != null
    }
}