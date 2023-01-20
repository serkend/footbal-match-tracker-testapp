package com.example.footballmatchtrackertest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.footballmatchtrackertest.R
import com.example.footballmatchtrackertest.ui.viewmodels.MainFactory
import com.example.footballmatchtrackertest.ui.viewmodels.MainViewModel
import com.example.footballmatchtrackertest.databinding.FragmentSplashBinding
import com.example.footballmatchtrackertest.ui.activities.MainActivity

class SplashFragment : Fragment() {

    lateinit var binding:FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGoToMatches.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_mainFragment)
        }

        binding.btnGoToWebView.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_webViewFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }
}