package com.example.footballmatchtrackertest.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footballmatchtrackertest.data.model.Data
import com.example.footballmatchtrackertest.databinding.FragmentSavedMatchesBinding
import com.example.footballmatchtrackertest.db.MainApp
import com.example.footballmatchtrackertest.db.model.MatchesInfoEntity
import com.example.footballmatchtrackertest.ui.adapters.MatchesAdapter
import com.example.footballmatchtrackertest.ui.viewmodels.MainFactory
import com.example.footballmatchtrackertest.ui.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SavedMatchesFragment : Fragment(), MatchesAdapter.MatchListener {

    lateinit var binding: FragmentSavedMatchesBinding
    lateinit var viewModel: MainViewModel
    lateinit var savedMatchesAdapter: MatchesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSavedMatchesBinding.inflate(inflater, container, false)
        var db = (requireActivity().application as MainApp).database
        val tempViewModel by viewModels<MainViewModel> { MainFactory(db) }
        viewModel = tempViewModel

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val match = savedMatchesAdapter.matchList[position]
                viewModel.deleteMatch(match.id.toString())
                Snackbar.make(view, "Succefully deleted match", Snackbar.LENGTH_SHORT).apply {
                    setAction("Undo") {
                        viewModel.saveMatchInfo(
                            MatchesInfoEntity(
                                match.id,
                                stringFromObject(match)!!
                            )
                        )
                    }
                    show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvSavedMatches)
        }

    }

    private fun setupObservers() {
        viewModel.allMatchesFromDB.observe(requireActivity()) { list ->
            val strings = list.map { it.matchJson }
            Log.d("MyLog: ", "Strings - $strings")

            val dataList = mutableListOf<Data>()
            strings.forEach { dataList.add(getObjectFromString(it)!!) }
            setupRV(dataList)
//            ids = matches?.map { it.id }
        }
    }

    private fun setupRV(matches: List<Data>) {
        if (matches != null) {
            savedMatchesAdapter = MatchesAdapter(matches, requireContext(), this)
            binding.rvSavedMatches.adapter = savedMatchesAdapter
            val layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            binding.rvSavedMatches.layoutManager = layoutManager
            savedMatchesAdapter.notifyDataSetChanged()
        } else {
            Toast.makeText(
                requireActivity(),
                "You haven't saved matches! ",
                Toast.LENGTH_SHORT
            ).show()
            binding.rvSavedMatches.adapter = null
        }
    }

    fun stringFromObject(match: Data): String? {
        val gson = Gson()
        return gson.toJson(match)
    }

    fun getObjectFromString(jsonString: String?): Data? {
        val type: Type = object : TypeToken<Data?>() {}.type
        return Gson().fromJson<Data>(jsonString, type)
    }

    companion object {
        fun newInstance() = SavedMatchesFragment()
    }

    override fun onClick(match: Data) {

    }
}