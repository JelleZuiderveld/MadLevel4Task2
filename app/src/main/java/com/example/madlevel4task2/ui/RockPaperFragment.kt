package com.example.madlevel4task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.madlevel4task2.R
import com.example.madlevel4task2.repository.RockPaperRepository

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RockPaperFragment : Fragment() {

    private lateinit var rockPaperRepositoy: RockPaperRepository

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rock_paper, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var resultImg = view.findViewById<ImageView>(R.id.ivPlayer)

    }
}