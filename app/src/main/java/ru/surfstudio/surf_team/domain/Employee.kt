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
    val photoUrl: String = "https://pbs.twimg.com/profile_images/378800000666093975/cbe970ce9a19d2c6bec0d06c6004ccf0_400x400.png"
}

