package com.example.footballmatchtrackertest.ui.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballmatchtrackertest.data.api.RetrofitInstance
import com.example.footballmatchtrackertest.data.model.Data
import com.example.footballmatchtrackertest.data.model.MatchesInfo
import com.example.footballmatchtrackertest.databinding.FragmentMatchesInfoBinding
import com.example.footballmatchtrackertest.db.MainApp
import com.example.footballmatchtrackertest.db.model.MatchesInfoEntity
import com.example.footballmatchtrackertest.ui.adapters.MatchesAdapter
import com.example.footballmatchtrackertest.ui.viewmodels.MainFactory
import com.example.footballmatchtrackertest.ui.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*


class MatchesInfoFragment : Fragment(), MatchesAdapter.MatchListener {
    lateinit var binding: FragmentMatchesInfoBinding
    lateinit var myCalendar :Calendar;
    lateinit var viewModel: MainViewModel
    var ids: List<Int>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatchesInfoBinding.inflate(inflater, container, false)
        var db = (requireActivity().application as MainApp).database
        val tempViewModel by viewModels<MainViewModel> { MainFactory(db) }
        viewModel = tempViewModel
        myCalendar = viewModel.myCalendar.value!!
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateLabel()
        setupObservers()
        binding.etDatePicker.setOnClickListener {
            setupDatePicker()
        }
    }

    private fun setupDatePicker() {
        var date = DatePickerDialog(
            requireActivity(),
            { datePicker, year, month, day ->
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            },
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        )
        viewModel.myCalendar.value = myCalendar
        date.show()
    }

    private fun updateLabel() {
        var myFormat = "yyyy-MM-dd"
        var dateFormat = SimpleDateFormat(myFormat, Locale.getDefault())
        viewModel.myCalendar.observe(viewLifecycleOwner) {
            myCalendar = it
        }
        binding.etDatePicker.setText(dateFormat.format(myCalendar.time))
        viewModel.getAllMatchesInfo(binding.etDatePicker.text.toString())
    }

    private fun setupObservers() {
        viewModel.allMatches.observe(requireActivity()) {
            setupRV(it.body())
        }
        viewModel.allMatchesFromDB.observe(requireActivity()) {
            val matches = it
            ids = matches?.map { it.id }
        }
    }

    private fun setupRV(matchesInfo: MatchesInfo?) {
        if (matchesInfo?.data != null) {
            val adapter = MatchesAdapter(matchesInfo.data, requireContext(), this)
            binding.rvMatches.adapter = adapter
            val layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            binding.rvMatches.layoutManager = layoutManager
            adapter.notifyDataSetChanged()
        } else {
            Toast.makeText(
                requireActivity(),
                "There aren't matches on this date! ",
                Toast.LENGTH_SHORT
            ).show()
            binding.rvMatches.adapter = null
        }
    }

    override fun onClick(match: Data) {
        if (checkIfExistsSameMatch(match.id) != true && checkIfExistsSameMatch(match.id) != null) {
            viewModel.saveMatchInfo(MatchesInfoEntity(match.id, stringFromObject(match)!!))
            Snackbar.make(requireView(), "Match was successfully saved!", Snackbar.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(
                requireActivity(),
                "This match has already been saved! ",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun checkIfExistsSameMatch(id: Int): Boolean? {
        Log.d("MyLog: ", "IDs - $ids , id - $id")
        return ids?.contains(id)
    }

    fun stringFromObject(match: Data): String? {
        val gson = Gson()
        return gson.toJson(match)
    }

    fun getObjectFromString(jsonString: String?): Data? {
        val type: Type = object : TypeToken<Data?>() {}.type
        return Gson().fromJson<Data>(jsonString, type)
    }
}