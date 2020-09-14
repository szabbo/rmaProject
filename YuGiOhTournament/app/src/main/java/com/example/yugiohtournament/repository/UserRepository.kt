package com.example.yugiohtournament.repository

import com.example.yugiohtournament.database.YgoDatabase
import com.example.yugiohtournament.model.User


object UserRepository {
    val users: MutableList<User>
    var userId: Int = 0

    private val ygoDB = YgoDatabase.getInstance().ygoDatabaseDao()

    init {
        users = retrieveTournaments()
    }

    private fun retrieveTournaments(): MutableList<User> = ygoDB.getAllUsers().toMutableList()


    fun removeUser(id: Int) = users.removeAll { user -> user.userIdUsers == id }
    fun getUser(id: Int): User? {
        userId = id
        return users.find { user -> user.userIdUsers == id }
    }
    fun addUser(user: User) = users.add(user)
    fun getSelectedId(): Int = userId
}