package com.example.yugiohtournament.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.yugiohtournament.MyApplication
import com.example.yugiohtournament.R
import com.example.yugiohtournament.database.YgoDatabase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    private val TAG = "SettingsFragment"
    private val ygoDB = YgoDatabase.getInstance().ygoDatabaseDao()
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        update_button_settings.setOnClickListener {
            changePass()
        }

        signout_button_settings.setOnClickListener {
            auth.signOut()
        }
    }

    private fun changePass() {
        val oldPass = old_pass_settings.text.toString()
        val newPass = new_pass_settings.text.toString()

        if (oldPass.isNotEmpty() && newPass.isNotEmpty()) {
            val user = auth.currentUser
            if (user != null){
                val credential = EmailAuthProvider
                    .getCredential(user.email.toString(), oldPass)


                user.reauthenticate(credential).addOnCompleteListener {
                    if (it.isSuccessful)
                    {
                        user.updatePassword(newPass)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(MyApplication.ApplicationContext, "Password changed successfully!", Toast.LENGTH_SHORT).show()
                                }
                            }
                    }

                     else Log.d(TAG, "failed")
                }
            }

        } else {
            Toast.makeText(MyApplication.ApplicationContext, "Email and password successfully updated", Toast.LENGTH_SHORT).show()
        }
    }
}