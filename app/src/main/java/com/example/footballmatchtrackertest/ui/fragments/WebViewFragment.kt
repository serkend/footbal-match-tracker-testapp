package com.example.footballmatchtrackertest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.footballmatchtrackertest.databinding.FragmentWebViewBinding


class WebViewFragment : Fragment() {

    lateinit var myWebView: WebView
    lateinit var binding: FragmentWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebViewBinding.inflate(inflater, container, false)
        myWebView = binding.webview
        myWebView.settings.loadsImagesAutomatically = true
        myWebView.settings.javaScriptEnabled = true
        myWebView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        myWebView.webViewClient = WebViewClient()
        myWebView.loadUrl("https://google.com")
        return binding.root
    }

    companion object {
        fun newInstance() = WebViewFragment()
    }
}