package com.example.madlevel4task2.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.R
import com.example.madlevel4task2.model.RockPaper
import com.example.madlevel4task2.repository.RockPaperRepository
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HistoryFragment : Fragment() {

    private val rockPaper = arrayListOf<RockPaper>()
    private val historyAdapter = HistoryAdapter(rockPaper)
    private lateinit var rockPaperRepository: RockPaperRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        rockPaperRepository = RockPaperRepository(requireContext())
    }

    private fun initViews(){
        rvHistory.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvHistory.adapter = historyAdapter
        rvHistory.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        getFromDatabase()
    }

    private fun getFromDatabase(){
        CoroutineScope(Dispatchers.Main).launch {
            val history = withContext(Dispatchers.IO){
                rockPaperRepository.getAllMatches()
            }
            this@HistoryFragment.rockPaper.clear()
            this@HistoryFragment.rockPaper.addAll(history)
            this@HistoryFragment.historyAdapter.notifyDataSetChanged()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_delete_all -> {
                deleteAll()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun deleteAll(){
        CoroutineScope(Dispatchers.Main).launch{
            withContext(Dispatchers.IO){
                rockPaperRepository.deleteAllMatches()
            }
            getFromDatabase()
        }
    }
}