package ru.surfstudio.surf_team.f_main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import ru.surfstudio.surf_team.R
import ru.surfstudio.surf_team.f_about.AboutScreen
import ru.surfstudio.surf_team.f_team_members.TeamMembersScreen
import ru.surfstudio.surf_team.ui.theme.SurfteamTheme

@ExperimentalUnitApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun MainScreen() {
    val viewModel =
        viewModel(modelClass = MainViewModel::class.java, factory = MainViewModel.Factory())
    val state by viewModel.observeState().collectAsState(initial = MainState.Loading)

    when (state) {
        MainState.Error -> MainErrorState { viewModel.handleRetry() }
        MainState.Loading -> MainLoadingState()
        is MainState.Success -> MainSuccessState(state)
    }
}

@ExperimentalUnitApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
private fun MainSuccessState(state: MainState) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
        TopBarRow()
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = @Composable { tabPositions ->
                Box(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                ) {
                    Box(
                        Modifier
                            .width(24.dp)
                            .height(3.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(Blue)
                            .align(Center)
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.background,
            divider = {}
        ) {
            // Add tabs for all of our pages
            MainTab.values().forEachIndexed { index: Int, tab: MainTab ->
                Tab(
                    text = {
                        Text(
                            text = stringResource(id = tab.titleRes).toUpperCase(Locale.current),
                            fontWeight = FontWeight.Bold,
                            fontSize = TextUnit(16f, TextUnitType.Sp),
                            color = if (pagerState.currentPage == index) {
                                Blue
                            } else {
                                Gray
                            }
                        )
                           },
                    selected = pagerState.currentPage == index,
                    onClick = {
                          scope.launch {
                              pagerState.scrollToPage(index)
                          }
                    }
                )
            }
        }
        HorizontalPager(
            count = MainTab.values().size,
            state = pagerState,
            modifier = Modifier.background(MaterialTheme.colors.background)
        ) {
            if (it == 0) {
                TeamMembersScreen(
                    members = (state as MainState.Success).teamUnits
                        .map { teamUnit -> teamUnit.employees }
                        .flatten()
                )
            } else {
                AboutScreen()
            }
        }
    }
}

@Composable
private fun TopBarRow() {
    Row {
        Image(
            painter = painterResource(id = R.drawable.ic_surf),
            contentDescription = null,
            modifier = Modifier.padding(start = 20.dp, top = 8.dp, bottom = 24.dp)
        )
    }
}

@Composable
fun MainLoadingState() {
    Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
        TopBarRow()
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
            Text(
                text = stringResource(id = R.string.main_loading)
            )
        }
    }
}

@Composable
fun MainErrorState(retryListener: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(id = R.string.main_error),
            modifier = Modifier
                .padding(20.dp)
                .align(CenterHorizontally)
        )
        Button(
            onClick = retryListener,
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.main_retry))
        }
    }
}

enum class MainTab(val titleRes: Int) {
    Members(R.string.main_tab_members),
    About(R.string.main_tab_about)
}

@ExperimentalPagerApi
@Composable
@Preview
fun MainScreenPreview() {
    SurfteamTheme {
        MainLoadingState()
    }
}