package fingerfire.com.valoguide.ui.screens.agents

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import fingerfire.com.valoguide.R
import fingerfire.com.valoguide.features.agents.data.response.AgentDataResponse
import fingerfire.com.valoguide.features.agents.ui.AgentViewModel
import fingerfire.com.valoguide.ui.components.ErrorMessage
import fingerfire.com.valoguide.ui.components.LoadingIndicator
import fingerfire.com.valoguide.ui.components.htmlStringResource
import fingerfire.com.valoguide.ui.theme.ValorantBackgroundDark
import fingerfire.com.valoguide.ui.theme.ValorantLightBlue
import fingerfire.com.valoguide.ui.theme.ValorantRed
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AgentsScreen(
    onAgentClick: (String) -> Unit,
    viewModel: AgentViewModel = koinViewModel()
) {
    val viewState by viewModel.agentsState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getAgents()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ValorantBackgroundDark)
            .padding(top = 8.dp)
    ) {
        AsyncImage(
            model = R.drawable.valorant_logo,
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .height(48.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = htmlStringResource(R.string.title_agents),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        val success = viewState.sucess
        when {
            success != null -> {
                val roles = listOf(
                    stringResource(R.string.sentinel),
                    stringResource(R.string.controller),
                    stringResource(R.string.initiator),
                    stringResource(R.string.duelist)
                )
                RoleChips(
                    roles = roles,
                    selectedRole = viewState.selectedRole,
                    onRoleSelected = viewModel::onRoleSelected
                )

                val filtered = viewState.filteredAgents

                if (filtered.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = stringResource(R.string.failResponse), color = Color.White)
                    }
                } else {
                    AgentsPager(
                        agents = filtered,
                        onAgentClick = onAgentClick,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            viewState.failure -> ErrorMessage(modifier = Modifier.weight(1f))
            else -> LoadingIndicator(modifier = Modifier.weight(1f))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RoleChips(
    roles: List<String>,
    selectedRole: String?,
    onRoleSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        roles.forEach { role ->
            val selected = selectedRole == role
            FilterChip(
                selected = selected,
                onClick = { onRoleSelected(role) },
                label = { Text(role) },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = ValorantLightBlue,
                    labelColor = Color.White,
                    selectedContainerColor = Color.White,
                    selectedLabelColor = ValorantBackgroundDark
                )
            )
        }
    }
}

@Composable
private fun AgentsPager(
    agents: List<AgentDataResponse>,
    onAgentClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { agents.size })

    LaunchedEffect(agents) {
        pagerState.scrollToPage(0)
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 24.dp),
            pageSpacing = 12.dp,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            AgentCard(
                agent = agents[page],
                onClick = { onAgentClick(agents[page].uuid) }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            repeat(agents.size) { index ->
                val selected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .size(if (selected) 8.dp else 6.dp)
                        .clip(CircleShape)
                        .background(if (selected) Color.White else Color.White.copy(alpha = 0.35f))
                )
            }
        }
    }
}

@Composable
private fun AgentCard(
    agent: AgentDataResponse,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(420.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = ValorantRed),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(agent.background)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(agent.fullPortrait)
                    .crossfade(true)
                    .build(),
                contentDescription = agent.displayName,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.8f))
                    .padding(bottom = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = agent.displayName.uppercase(),
                    color = ValorantBackgroundDark,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Box(
                    modifier = Modifier
                        .width(65.dp)
                        .height(5.dp)
                        .background(ValorantBackgroundDark)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = agent.role.displayName,
                    color = ValorantBackgroundDark,
                    fontSize = 18.sp
                )
            }
        }
    }
}
