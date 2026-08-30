package fingerfire.com.valoguide.ui.components

import android.graphics.Typeface
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import fingerfire.com.valoguide.R
import fingerfire.com.valoguide.ui.theme.ValorantRed
import fingerfire.com.valoguide.ui.theme.ValorantTextGrey

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = ValorantRed
) {
    Icon(
        painter = painterResource(R.drawable.ic_back_white),
        contentDescription = null,
        tint = tint,
        modifier = modifier
            .size(32.dp)
            .clickable(onClick = onClick)
            .padding(4.dp)
    )
}

@Composable
fun SectionTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    val display = if (text.startsWith("//")) text else "// $text"
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(color = ValorantRed, fontWeight = FontWeight.Bold)) {
                append("//")
            }
            withStyle(SpanStyle(color = Color.White, fontWeight = FontWeight.Bold)) {
                append(display.removePrefix("//"))
            }
        },
        fontSize = 18.sp,
        letterSpacing = 0.5.sp,
        modifier = modifier
    )
}

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = ValorantRed)
    }
}

@Composable
fun ErrorMessage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.failResponse),
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.internet),
                color = ValorantTextGrey,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun htmlStringResource(@StringRes id: Int): AnnotatedString {
    val context = LocalContext.current
    return remember(id) {
        val html = context.getString(id)
        val spanned = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)
        spanned.toAnnotatedString()
    }
}

private fun Spanned.toAnnotatedString(): AnnotatedString = buildAnnotatedString {
    val text = this@toAnnotatedString.toString()
    append(text)
    getSpans(0, length, Any::class.java).forEach { span ->
        val start = getSpanStart(span)
        val end = getSpanEnd(span)
        when (span) {
            is ForegroundColorSpan -> addStyle(
                SpanStyle(color = Color(span.foregroundColor)),
                start,
                end
            )
            is StyleSpan -> if (span.style == Typeface.BOLD) {
                addStyle(SpanStyle(fontWeight = FontWeight.Bold), start, end)
            }
        }
    }
}

fun parseHexColor(hex: String): Color {
    val cleaned = hex.removePrefix("#").take(6)
    return try {
        Color(android.graphics.Color.parseColor("#$cleaned"))
    } catch (_: Exception) {
        ValorantRed
    }
}

@Composable
fun HighlightedTitle(
    prefix: String,
    highlight: String,
    suffix: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(color = Color.White, fontWeight = FontWeight.Bold)) {
                append(prefix)
            }
            withStyle(SpanStyle(color = ValorantRed, fontWeight = FontWeight.Bold)) {
                append(highlight)
            }
            withStyle(SpanStyle(color = Color.White, fontWeight = FontWeight.Bold)) {
                append(suffix)
            }
        },
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}
