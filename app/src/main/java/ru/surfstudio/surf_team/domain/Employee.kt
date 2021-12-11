package ru.surfstudio.surf_team.domain

data class Employee(
    val createdDate: Int = 0,
    val currentProject: String = "",
    val department: String = "",
    val experience: String = "",
    val id: String = "",
    val photoUrl: String = "",
    val position: String = "",
    val skills: List<String> = listOf(),
    val userInfo: UserInfo = UserInfo()
)