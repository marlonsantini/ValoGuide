package fingerfire.com.valorant.extensions

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView


fun AdView.initAdMob() {
    val adRequest = AdRequest.Builder().build()
    loadAd(adRequest)
}