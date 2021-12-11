package ru.surfstudio.surf_team.f_main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.surfstudio.surf_team.dependencies.ServiceLocator
import ru.surfstudio.surf_team.domain.TeamUnit
import ru.surfstudio.surf_team.i_team.TeamInteractor

class MainViewModel(
    private val interactor: TeamInteractor
): ViewModel() {

    private val mainStateFlow = MutableStateFlow<MainState>(MainState.Loading)

    init {
        loadMainData()
    }

    fun observeState() : Flow<MainState> {
        return mainStateFlow.asStateFlow()
    }

    fun handleRetry() {
        loadMainData()
    }

    private fun loadMainData() {
        viewModelScope.launch {
            mainStateFlow.emitAll(createMainStateFlow())
        }
    }

    private fun createMainStateFlow() : Flow<MainState> {
        return flow<MainState> {
            emit(MainState.Success(interactor.getTeamData()))
        }.catch {
            emit(MainState.Error)
        }.onStart {
            emit(MainState.Loading)
        }
    }

    class Factory: ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(interactor = ServiceLocator.teamInteractor) as T
        }
    }
}

sealed class MainState {
    object Error: MainState()
    object Loading: MainState()
    data class Success(val teamUnits: List<TeamUnit>): MainState()
}