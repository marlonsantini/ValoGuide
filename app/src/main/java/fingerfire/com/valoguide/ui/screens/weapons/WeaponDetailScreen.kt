package fingerfire.com.valoguide.ui.screens.weapons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import fingerfire.com.valoguide.R
import fingerfire.com.valoguide.features.weapons.data.response.DamageRangesResponse
import fingerfire.com.valoguide.features.weapons.data.response.SkinsResponse
import fingerfire.com.valoguide.features.weapons.data.response.WeaponDetailDataResponse
import fingerfire.com.valoguide.features.weapons.ui.WeaponDetailViewModel
import fingerfire.com.valoguide.features.weapons.ui.viewstate.WeaponDetailViewState
import fingerfire.com.valoguide.ui.components.BackButton
import fingerfire.com.valoguide.ui.components.ErrorMessage
import fingerfire.com.valoguide.ui.components.LoadingIndicator
import fingerfire.com.valoguide.ui.components.SectionTitle
import fingerfire.com.valoguide.ui.theme.AlataFontFamily
import fingerfire.com.valoguide.ui.theme.ValorantBackgroundDark
import fingerfire.com.valoguide.ui.theme.ValorantDarkBlue
import fingerfire.com.valoguide.ui.theme.ValorantLightBlue
import fingerfire.com.valoguide.ui.theme.ValorantRed
import fingerfire.com.valoguide.ui.theme.ValorantRedAlpha
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeaponDetailScreen(
    uuid: String,
    onBack: () -> Unit,
    viewModel: WeaponDetailViewModel = koinViewModel()
) {
    val viewState by viewModel.weaponDetailState.collectAsStateWithLifecycle()

    LaunchedEffect(uuid) {
        viewModel.getWeaponDetail(uuid)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(ValorantLightBlue, ValorantBackgroundDark, Color(0xFF0A1018))
                )
            )
    ) {
        val success = viewState.sucess
        when {
            success != null -> WeaponDetailContent(
                weapon = success.data,
                isMelee = viewState.isMelee,
                skins = viewState.visibleSkins,
                damageRanges = viewState.damageRanges,
                onBack = onBack
            )
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
private fun WeaponDetailContent(
    weapon: WeaponDetailDataResponse,
    isMelee: Boolean,
    skins: List<SkinsResponse>,
    damageRanges: List<DamageRangesResponse>,
    onBack: () -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .shadow(16.dp, RoundedCornerShape(bottomStart = 48.dp, bottomEnd = 48.dp))
                    .clip(RoundedCornerShape(bottomStart = 48.dp, bottomEnd = 48.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFFC0ACA6), Color(0xFF8A7C78), Color(0xFF1A1A1A))
                        )
                    )
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(weapon.displayIcon)
                        .crossfade(true)
                        .build(),
                    contentDescription = weapon.displayName,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(36.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
                            )
                        )
                )

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
                        .align(Alignment.BottomStart)
                        .padding(20.dp)
                ) {
                    Text(
                        text = weapon.displayName.uppercase(),
                        color = Color.White,
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = AlataFontFamily
                    )
                    if (!isMelee) {
                        val category = weapon.shopData?.categoryText.orEmpty()
                        if (category.isNotBlank()) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Surface(
                                shape = RoundedCornerShape(20.dp),
                                color = ValorantRedAlpha.copy(alpha = 0.2f),
                                border = BorderStroke(1.dp, ValorantRed.copy(alpha = 0.5f))
                            ) {
                                Text(
                                    text = category,
                                    color = ValorantRed,
                                    fontSize = 13.sp,
                                    fontFamily = AlataFontFamily,
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        if (!isMelee && damageRanges.isNotEmpty()) {
            item {
                Column(modifier = Modifier.padding(20.dp)) {
                    SectionTitle(text = stringResource(R.string.damage_range))
                    Spacer(modifier = Modifier.height(14.dp))
                    DamageTable(ranges = damageRanges)
                }
            }
        }

        if (skins.isNotEmpty()) {
            item {
                Column(modifier = Modifier.padding(20.dp)) {
                    SectionTitle(text = stringResource(R.string.skins))
                    Spacer(modifier = Modifier.height(14.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(skins) { skin ->
                            SkinCard(skin = skin)
                        }
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(32.dp)) }
    }
}

@Composable
private fun DamageTable(ranges: List<DamageRangesResponse>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.width(72.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(stringResource(R.string.head), color = Color.White, fontSize = 13.sp, fontFamily = AlataFontFamily)
            Text(stringResource(R.string.body), color = Color.White, fontSize = 13.sp, fontFamily = AlataFontFamily)
            Text(stringResource(R.string.leg), color = Color.White, fontSize = 13.sp, fontFamily = AlataFontFamily)
        }

        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            items(ranges) { range ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(ValorantDarkBlue.copy(alpha = 0.55f))
                        .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(14.dp))
                        .padding(10.dp)
                ) {
                    Text(
                        text = "${range.rangeStartMeters.toIntSafe()} - ${range.rangeEndMeters.toIntSafe()}M",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = AlataFontFamily
                    )
                    Text(range.headDamage.toInt().toString(), color = ValorantRed, fontWeight = FontWeight.Bold)
                    Text(range.bodyDamage.toInt().toString(), color = ValorantRed, fontWeight = FontWeight.Bold)
                    Text(range.legDamage.toInt().toString(), color = ValorantRed, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun SkinCard(skin: SkinsResponse) {
    Column(
        modifier = Modifier
            .width(168.dp)
            .height(128.dp)
            .shadow(6.dp, RoundedCornerShape(16.dp), clip = false)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFECECEC))
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(skin.displayIcon)
                .crossfade(true)
                .build(),
            contentDescription = skin.displayName,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )
        Text(
            text = skin.displayName,
            color = ValorantBackgroundDark,
            fontSize = 11.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            fontFamily = AlataFontFamily
        )
    }
}

private fun String.toIntSafe(): Int =
    substringBefore('.').toIntOrNull() ?: 0
