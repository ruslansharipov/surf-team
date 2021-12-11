package ru.surfstudio.surf_team.dependencies

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import ru.surfstudio.surf_team.i_team.TeamApi
import ru.surfstudio.surf_team.i_team.TeamInteractor

object ServiceLocator {

    val json: Json = Json {
        ignoreUnknownKeys = true
    }

    private val teamApi: TeamApi = Retrofit.Builder()
        .baseUrl(TeamApi.BASE_URL)
        .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
        .build()
        .create(TeamApi::class.java)

    val teamInteractor: TeamInteractor = TeamInteractor(teamApi)
}