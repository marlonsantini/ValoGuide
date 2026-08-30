package fingerfire.com.valoguide.ui.screens.maps

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import fingerfire.com.valoguide.features.maps.ui.MapsViewModel
import fingerfire.com.valoguide.ui.components.ErrorMessage
import fingerfire.com.valoguide.ui.components.LoadingIndicator
import fingerfire.com.valoguide.ui.theme.ValorantBackgroundDark
import org.koin.androidx.compose.koinViewModel

@Composable
fun MapsScreen(
    onMapClick: (String) -> Unit,
    viewModel: MapsViewModel = koinViewModel()
) {
    val viewState by viewModel.mapsState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getMaps()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ValorantBackgroundDark)
            .padding(horizontal = 8.dp)
    ) {
        val success = viewState.sucess
        when {
            success != null -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(success.maps, key = { it.uuid }) { map ->
                        MapCard(map = map, onClick = { onMapClick(map.uuid) })
                    }
                }
            }
            viewState.failure -> ErrorMessage()
            else -> LoadingIndicator()
        }
    }
}

@Composable
private fun MapCard(
    map: MapDataResponse,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(map.splash)
                    .crossfade(true)
                    .build(),
                contentDescription = map.displayName,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = map.displayName.uppercase(),
                color = ValorantBackgroundDark,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.8f))
                    .padding(vertical = 8.dp)
            )
        }
    }
}
