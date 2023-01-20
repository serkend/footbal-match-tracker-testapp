package com.example.footballmatchtrackertest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.footballmatchtrackertest.R
import com.example.footballmatchtrackertest.databinding.FragmentMainBinding
import com.example.footballmatchtrackertest.ui.adapters.VpAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {

    lateinit var binding:FragmentMainBinding
    private val fragList:List<Fragment> = listOf(
        MatchesInfoFragment(),
        SavedMatchesFragment()
    )

    private val tabList = listOf(
        "Matches",
        "Saved"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vpAdapter = VpAdapter(activity as FragmentActivity, fragList)
        binding.vpFragments.adapter = vpAdapter

        TabLayoutMediator(binding.tabLayout, binding.vpFragments) { tab, p ->
            tab.text = tabList[p]
        }.attach()

    }

    companion object {
        fun newInstance() = MainFragment()
    }
}