package fingerfire.com.valoguide.ui.screens.agents

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import fingerfire.com.valoguide.features.agents.data.response.AbilitiesResponse
import fingerfire.com.valoguide.features.agents.data.response.AgentDataResponse
import fingerfire.com.valoguide.features.agents.ui.AgentDetailViewModel
import fingerfire.com.valoguide.ui.components.BackButton
import fingerfire.com.valoguide.ui.components.ErrorMessage
import fingerfire.com.valoguide.ui.components.LoadingIndicator
import fingerfire.com.valoguide.ui.components.SectionTitle
import fingerfire.com.valoguide.ui.components.parseHexColor
import fingerfire.com.valoguide.ui.theme.AlataFontFamily
import fingerfire.com.valoguide.ui.theme.ValorantBackgroundDark
import fingerfire.com.valoguide.ui.theme.ValorantDarkBlue
import fingerfire.com.valoguide.ui.theme.ValorantLightBlue
import fingerfire.com.valoguide.ui.theme.ValorantRed
import fingerfire.com.valoguide.ui.theme.ValorantRedAlpha
import org.koin.androidx.compose.koinViewModel

@Composable
fun AgentDetailScreen(
    uuid: String,
    onBack: () -> Unit,
    viewModel: AgentDetailViewModel = koinViewModel()
) {
    val viewState by viewModel.agentDetailState.collectAsStateWithLifecycle()

    LaunchedEffect(uuid) {
        viewModel.getAgentDetail(uuid)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ValorantBackgroundDark)
    ) {
        val success = viewState.sucess
        when {
            success != null -> AgentDetailContent(agent = success.data, onBack = onBack)
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
private fun AgentDetailContent(
    agent: AgentDataResponse,
    onBack: () -> Unit
) {
    val baseGradient = remember(agent.backgroundGradientColors) {
        agent.backgroundGradientColors
            .take(3)
            .map { parseHexColor(it) }
            .ifEmpty { listOf(ValorantLightBlue, ValorantDarkBlue, ValorantBackgroundDark) }
    }

    val abilities = remember(agent.abilities) {
        agent.abilities.filter { it.displayName.isNotBlank() }
    }
    var selectedIndex by remember(agent.uuid) { mutableIntStateOf(0) }
    val selectedAbility = abilities.getOrNull(selectedIndex)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                .background(Brush.verticalGradient(baseGradient))
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(agent.fullPortrait)
                    .crossfade(300)
                    .build(),
                contentDescription = agent.displayName,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp, bottom = 40.dp)
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
                color = Color.Black.copy(alpha = 0.4f),
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(16.dp)
                    .align(Alignment.TopStart)
            ) {
                BackButton(onClick = onBack, modifier = Modifier.padding(8.dp))
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(20.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = agent.displayName,
                    color = Color.White,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = AlataFontFamily
                )
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = ValorantRedAlpha.copy(alpha = 0.25f),
                    border = BorderStroke(1.dp, ValorantRed.copy(alpha = 0.5f))
                ) {
                    Text(
                        text = agent.role.displayName,
                        color = ValorantRed,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = AlataFontFamily,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                    )
                }
            }
        }

        Column(modifier = Modifier.padding(20.dp)) {
            SectionTitle(text = stringResource(R.string.biography))
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = agent.description,
                color = Color.White.copy(alpha = 0.88f),
                fontSize = 15.sp,
                lineHeight = 22.sp,
                fontFamily = AlataFontFamily
            )

            Spacer(modifier = Modifier.height(28.dp))
            SectionTitle(text = stringResource(R.string.special_abilities))
            Spacer(modifier = Modifier.height(16.dp))

            if (abilities.isNotEmpty()) {
                AbilitySelector(
                    abilities = abilities,
                    selectedIndex = selectedIndex,
                    onSelect = { selectedIndex = it }
                )
                Spacer(modifier = Modifier.height(14.dp))
                selectedAbility?.let { ability ->
                    AbilityDetailPanel(ability = ability)
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))
    }
}

@Composable
private fun AbilitySelector(
    abilities: List<AbilitiesResponse>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        abilities.forEachIndexed { index, ability ->
            val selected = index == selectedIndex
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            if (selected) ValorantRed.copy(alpha = 0.18f)
                            else ValorantDarkBlue
                        )
                        .border(
                            width = if (selected) 2.dp else 1.dp,
                            color = if (selected) ValorantRed else Color.White.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable { onSelect(index) },
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(ability.displayIcon.ifBlank { null })
                            .crossfade(false)
                            .build(),
                        contentDescription = ability.displayName,
                        modifier = Modifier.size(34.dp)
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = abilitySlotLabel(ability.slot),
                    color = if (selected) ValorantRed else Color.White.copy(alpha = 0.55f),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = AlataFontFamily,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
private fun AbilityDetailPanel(ability: AbilitiesResponse) {
    AnimatedContent(
        targetState = ability.displayName,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        label = "abilityDetail"
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(ValorantDarkBlue)
                .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Text(
                text = ability.displayName.uppercase(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = AlataFontFamily,
                letterSpacing = 0.6.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = ability.description,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = AlataFontFamily,
                maxLines = 8,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

private fun abilitySlotLabel(slot: String): String = when (slot.uppercase()) {
    "ABILITY1" -> "Q"
    "ABILITY2" -> "E"
    "GRENADE" -> "C"
    "ULTIMATE" -> "X"
    "PASSIVE" -> "P"
    else -> slot.take(1).uppercase().ifBlank { "•" }
}
