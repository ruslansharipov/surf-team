package ru.surfstudio.surf_team.f_main

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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

@ExperimentalPagerApi
@Composable
private fun MainSuccessState(state: MainState) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    Column {
        TabRow(
            // Our selected tab is our current page
            selectedTabIndex = pagerState.currentPage,
            // Override the indicator, using the provided pagerTabIndicatorOffset modifier
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            // Add tabs for all of our pages
            MainTab.values().forEachIndexed { index: Int, tab: MainTab ->
                Tab(
                    text = { Text(stringResource(id = tab.titleRes)) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                          scope.launch {
                              pagerState.scrollToPage(index)
                          }
                    },
                )
            }
        }
        HorizontalPager(
            count = MainTab.values().size,
            state = pagerState
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
fun MainLoadingState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
        Text(
            text = stringResource(id = R.string.main_loading),
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )
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