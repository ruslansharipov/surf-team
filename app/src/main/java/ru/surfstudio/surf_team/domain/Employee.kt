package ru.surfstudio.surf_team.domain

data class Employee(
    val createdDate: Int = 0,
    val currentProject: String = "",
    val department: String = "",
    val experience: String = "",
    val id: String = "",
    val position: String = "",
    val skills: List<String> = listOf(),
    val userInfo: UserInfo = UserInfo()
){
    val photoUrl: String = "https://avatars.mds.yandex.net/get-kino-vod-films-gallery/28788/47e2fd514411e18b76af786d7417062d/100x64_3"
}

