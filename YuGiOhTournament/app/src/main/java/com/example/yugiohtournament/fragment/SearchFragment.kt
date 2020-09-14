package com.example.yugiohtournament.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yugiohtournament.*
import com.example.yugiohtournament.activity.AddNewTournamentActivity
import com.example.yugiohtournament.adapter.RecycleViewAdapter
import com.example.yugiohtournament.extensions.showFragment
import com.example.yugiohtournament.listeners.TournamentInteractionListener
import com.example.yugiohtournament.repository.TournamentRepository
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()

        addBtn_floatingButton_main.setOnClickListener {
            val intent = Intent(activity, AddNewTournamentActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupUI() {

        tournamentDisplay.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        tournamentDisplay.itemAnimator = DefaultItemAnimator()
        tournamentDisplay.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        displayData()
    }

    private fun displayData() {
        val tournamentListener = object : TournamentInteractionListener {
            override fun onRemove(id: Int) {
                TournamentRepository.removeTournament(id)
                (tournamentDisplay.adapter as RecycleViewAdapter).refreshData(
                    TournamentRepository.tournaments
                )
            }

            override fun onShowDetails(id: Int) {
                val tournament = TournamentRepository.getTournament(id)
                activity?.showFragment(R.id.wrapper_frame_main_activity, TournamentDetailsFragment.newInstance())
            }

        }

        tournamentDisplay.adapter =
            RecycleViewAdapter(TournamentRepository.tournaments, tournamentListener)
    }

    companion object {
        fun newInstance(): Fragment {
            return SearchFragment()
        }
    }
}