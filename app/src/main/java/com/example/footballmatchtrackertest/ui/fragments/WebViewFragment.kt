package com.example.footballmatchtrackertest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.footballmatchtrackertest.databinding.FragmentWebViewBinding
import com.onesignal.OneSignal

class WebViewFragment : Fragment() {

    lateinit var myWebView: WebView
    lateinit var binding: FragmentWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebViewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOneSignal()
        setupWebView()
    }

    private fun setupWebView() {
        myWebView = binding.webview
        myWebView.settings.loadsImagesAutomatically = true
        myWebView.settings.javaScriptEnabled = true
        myWebView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        myWebView.webViewClient = WebViewClient()
        myWebView.loadUrl("https://www.google.com/search?q=soccer")
    }

    private fun setupOneSignal() {
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(requireContext());
        OneSignal.setAppId("33dc503c-444c-494f-a91b-d985819d0823");

        // promptForPushNotifications will show the native Android notification permission prompt.
        // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
        OneSignal.promptForPushNotifications();
    }

    companion object {
        fun newInstance() = WebViewFragment()
    }
}