package fingerfire.com.valoguide.ui.screens.maps

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import fingerfire.com.valoguide.features.maps.data.response.MapDataResponse
import fingerfire.com.valoguide.features.maps.ui.MapDetailViewModel
import fingerfire.com.valoguide.ui.components.BackButton
import fingerfire.com.valoguide.ui.components.ErrorMessage
import fingerfire.com.valoguide.ui.components.LoadingIndicator
import fingerfire.com.valoguide.ui.theme.AlataFontFamily
import fingerfire.com.valoguide.ui.theme.ValorantBackgroundDark
import fingerfire.com.valoguide.ui.theme.ValorantLightBlue
import fingerfire.com.valoguide.ui.theme.ValorantRed
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun MapDetailScreen(
    uuid: String,
    onBack: () -> Unit,
    viewModel: MapDetailViewModel = koinViewModel()
) {
    val viewState by viewModel.mapDetailState.collectAsStateWithLifecycle()

    LaunchedEffect(uuid) {
        viewModel.getMapDetail(uuid)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(ValorantLightBlue, ValorantBackgroundDark)
                )
            )
    ) {
        val success = viewState.sucess
        when {
            success != null -> MapDetailContent(map = success.data, onBack = onBack)
            viewState.failure -> {
                Column(modifier = Modifier.statusBarsPadding()) {
                    BackButton(onClick = onBack, modifier = Modifier.padding(16.dp))
                    ErrorMessage()
                }
            }
            else -> LoadingIndicator()
        }
    }
}

@Composable
private fun MapDetailContent(
    map: MapDataResponse,
    onBack: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(map.uuid) {
        delay(40)
        visible = true
    }
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(450),
        label = "mapAlpha"
    )
    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else 0.94f,
        animationSpec = tween(450),
        label = "mapScale"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Surface(
            shape = CircleShape,
            color = Color.Black.copy(alpha = 0.35f),
            modifier = Modifier
                .statusBarsPadding()
                .padding(16.dp)
                .align(Alignment.TopStart)
                .border(1.dp, Color.White.copy(alpha = 0.15f), CircleShape)
        ) {
            BackButton(onClick = onBack, modifier = Modifier.padding(8.dp))
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp)
                .padding(top = 88.dp)
                .alpha(alpha)
                .scale(scale),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White.copy(alpha = 0.04f))
                    .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(24.dp))
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(map.displayIcon.ifBlank { map.splash })
                        .crossfade(true)
                        .build(),
                    contentDescription = map.displayName,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = map.displayName,
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = AlataFontFamily,
                textAlign = TextAlign.Center
            )
            if (map.coordinates.isNotBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = map.coordinates,
                    color = ValorantRed.copy(alpha = 0.9f),
                    fontSize = 15.sp,
                    fontFamily = AlataFontFamily,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
