package fingerfire.com.valoguide.ui.screens.weapons

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import fingerfire.com.valoguide.R
import fingerfire.com.valoguide.features.weapons.data.response.WeaponDataResponse
import fingerfire.com.valoguide.features.weapons.ui.WeaponViewModel
import fingerfire.com.valoguide.ui.components.ErrorMessage
import fingerfire.com.valoguide.ui.components.LoadingIndicator
import fingerfire.com.valoguide.ui.theme.ValorantBackgroundDark
import fingerfire.com.valoguide.ui.theme.ValorantRed
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeaponsScreen(
    onWeaponClick: (String) -> Unit,
    viewModel: WeaponViewModel = koinViewModel()
) {
    val viewState by viewModel.weaponsState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getWeapons()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ValorantBackgroundDark)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(ValorantRed)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .height(20.dp)
            )
            BasicTextField(
                value = viewState.searchQuery,
                onValueChange = viewModel::onSearchQueryChanged,
                singleLine = true,
                cursorBrush = SolidColor(Color.White),
                textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 28.dp)
            )
        }

        when {
            viewState.sucess != null -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp)
                ) {
                    items(viewState.filteredWeapons, key = { it.uuid }) { weapon ->
                        WeaponCard(
                            weapon = weapon,
                            onClick = { onWeaponClick(weapon.uuid) }
                        )
                    }
                }
            }
            viewState.failure -> ErrorMessage()
            else -> LoadingIndicator()
        }
    }
}

@Composable
private fun WeaponCard(
    weapon: WeaponDataResponse,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.background_weapon),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(weapon.displayIcon)
                    .crossfade(true)
                    .build(),
                contentDescription = weapon.displayName,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            )
            Text(
                text = weapon.displayName.uppercase(),
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
