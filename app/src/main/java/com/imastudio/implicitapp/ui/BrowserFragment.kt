package com.imastudio.implicitapp.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient

import com.imastudio.implicitapp.R
import kotlinx.android.synthetic.main.fragment_browser.*
import kotlinx.android.synthetic.main.fragment_browser.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.browse

/**
 * A simple [Fragment] subclass.
 */
class BrowserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =inflater.inflate(R.layout.fragment_browser, container, false)
        // Inflate the layout for this fragment
        v.btngoogle.onClick { browse("http://google.com") }
        v.btnima.onClick { browse("http://imastudio.co.id") }
        v.btnsearch.onClick {
            if (edtlink.text.isEmpty()){
                edtlink.error="link tidak boleh kosong"
                edtlink.requestFocus()
            }else{
                webview.settings.javaScriptEnabled=true
                webview.webViewClient = WebViewClient()

                webview.loadUrl("http://${edtlink.text}")
            }
        }
        return v
    }


}
