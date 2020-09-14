package com.example.yugiohtournament.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yugiohtournament.R
import com.example.yugiohtournament.database.YgoDatabase
import com.example.yugiohtournament.repository.TournamentRepository
import com.example.yugiohtournament.model.Tournament
import kotlinx.android.synthetic.main.activity_add_new.*

class AddNewTournamentActivity : AppCompatActivity() {

    private val ygoDB = YgoDatabase.getInstance().ygoDatabaseDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new)

        add_button_add_new.setOnClickListener {
            addNew()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addNew() {
        val name = name_editText_add_new.text.toString()
        val address = address_editText_add_new.text.toString()
        val city = city_editText_add_new.text.toString()
        val date = date_editText_add_new.text.toString()
        val time = time_editText_add_new.text.toString()

        val tournament = Tournament(
            0,
            0,
            "name: $name",
            "address: $address",
            "city: $city",
            "date: $date",
            "time: $time")
        TournamentRepository.addTournament(tournament)
        ygoDB.insertTournament(tournament)
    }
}