package com.example.madlevel4task2.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.view.isVisible
import com.example.madlevel4task2.R
import com.example.madlevel4task2.model.RockPaper
import com.example.madlevel4task2.repository.RockPaperRepository
import kotlinx.android.synthetic.main.fragment_rock_paper.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

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

        rockPaperRepositoy = RockPaperRepository(requireContext())

        ivRock.setOnClickListener{
            matchGame(RockPaper.Pick.ROCK)
        }

        ivPaper.setOnClickListener{
            matchGame(RockPaper.Pick.PAPER)
        }

        ivScissors.setOnClickListener{
            matchGame(RockPaper.Pick.SCISSORS)
        }
    }


    private fun matchGame(usersPick: RockPaper.Pick){
        ivComp.isVisible = true
        ivPlayer.isVisible = true
        tvNameComp.isVisible = true
        tvNamePlayer.isVisible = true
        tvVs.isVisible = true
        val compPick = RockPaper.Pick.values()[RockPaper.Pick.values().indices.random()]
        val match = RockPaper(
            getImage(compPick),
            getImage(usersPick),
            getResult(compPick, usersPick),
            Date()
        )
        ivComp.setImageDrawable(getDrawable(requireContext(), match.compResult))
        ivPlayer.setImageDrawable(getDrawable(requireContext(), match.playerResult))

        if(match.result == RockPaper.Result.WIN){
            tvWinLoss.text = getString(R.string.win)
        }
        if(match.result == RockPaper.Result.DRAW){
            tvWinLoss.text = getString(R.string.draw)
        }
        if(match.result == RockPaper.Result.LOSS){
            tvWinLoss.text = getString(R.string.loss)
        }
        addToDatabase(match)
    }


    private fun getImage(pick: RockPaper.Pick): Int{
         var image = 0

        if(pick == RockPaper.Pick.ROCK){
            image = R.drawable.rock
        }
        if(pick == RockPaper.Pick.PAPER){
            image = R.drawable.paper
        }
        if(pick == RockPaper.Pick.SCISSORS){
            image = R.drawable.scissors
        }
        return image
    }

    private fun getResult(compPick : RockPaper.Pick, usersPick: RockPaper.Pick): RockPaper.Result{
        var result: RockPaper.Result = RockPaper.Result.LOSS

        if(usersPick == compPick){
            result = RockPaper.Result.DRAW
        }

        if(usersPick == RockPaper.Pick.ROCK && compPick == RockPaper.Pick.SCISSORS) {
            result = RockPaper.Result.WIN
        }

        if(usersPick == RockPaper.Pick.PAPER && compPick == RockPaper.Pick.ROCK){
            result = RockPaper.Result.WIN
        }

        if(usersPick == RockPaper.Pick.SCISSORS && compPick == RockPaper.Pick.PAPER){
            result = RockPaper.Result.WIN
        }

        return result
    }

    private fun addToDatabase(rockPaper: RockPaper){
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO){
                rockPaperRepositoy.insertMatch(rockPaper)
            }
        }
    }


}