package com.example.yugiohtournament.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.yugiohtournament.R
import com.example.yugiohtournament.database.YgoDatabase
import com.example.yugiohtournament.repository.UserRepository
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    private val ygoDB = YgoDatabase.getInstance().ygoDatabaseDao()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
//        val userId = UserRepository.userId
//        val user = ygoDB.getUserById(userId)
//
//        username_textView_profile.text = user.username.replaceFirst("username:", "Username:")
//        team_textView_profile.text = user.team.replaceFirst("team:", "Team:")
//        konamiID_textView_profile.text = user.konamiID.replaceFirst("konamiID:", "Konami ID:")
//        email_textView_profile.text = user.email.replaceFirst("email:", "Email:")

    }

}