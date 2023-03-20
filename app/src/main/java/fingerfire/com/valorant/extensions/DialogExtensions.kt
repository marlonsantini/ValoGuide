package fingerfire.com.valorant.extensions

import android.app.Activity
import android.app.AlertDialog
import androidx.annotation.StringRes


fun Activity.showDialogValorant(@StringRes msg: Int) {
    val dialog = AlertDialog.Builder(this)
        .setTitle("Valorant")
        .setMessage(msg)
        .setPositiveButton("OK") { _, _ ->
            finish()
        }
        .create()
    dialog.show()
}