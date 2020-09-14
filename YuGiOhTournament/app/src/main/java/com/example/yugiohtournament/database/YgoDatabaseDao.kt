package com.example.yugiohtournament.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.yugiohtournament.model.Tournament
import com.example.yugiohtournament.model.User

@Dao
interface YgoDatabaseDao {
    @Insert
    fun insertUser(user: User)
    @Delete
    fun deleteUser(user: User)
    @Query ("DELETE FROM users")
    fun deleteTableUsers()

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>
    @Query("SELECT * FROM users WHERE userIdUsers = :id")
    fun getUserById(id: Int): User
    @Query("SELECT * FROM users WHERE email = :email")
    fun getUserByEmail(email: String): User

    ///////////////////////////////////// BORDER /////////////////////////////////////

    @Insert
    fun insertTournament(tournament: Tournament)
    @Delete
    fun deleteTournament(tournament: Tournament)


    @Query("SELECT * FROM tournaments")
    fun getAllTournaments(): List<Tournament>
    @Query("SELECT * FROM tournaments WHERE tournamentIdTournament = :id")
    fun getTournamentById(id: Int): List<Tournament>
    @Query("SELECT * FROM tournaments WHERE name = :name")
    fun getTournamentByName(name: String): List<Tournament>
    @Query("DELETE FROM tournaments")
    fun deleteTableTournaments()


}