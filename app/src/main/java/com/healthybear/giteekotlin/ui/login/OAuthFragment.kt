package com.healthybear.giteekotlin.ui.login

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.LinearLayout
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.healthybear.giteekotlin.BuildConfig
import com.healthybear.giteekotlin.R
import com.healthybear.giteekotlin.databinding.FragmentOauthBinding
import com.healthybear.library.base.fragment.BaseFragment
import com.just.agentweb.AgentWeb
import com.just.agentweb.WebChromeClient
import com.just.agentweb.WebViewClient
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import okio.Buffer
import java.io.StringWriter

/**
 * A simple [Fragment] subclass.
 * Use the [OAuthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OAuthFragment : BaseFragment<FragmentOauthBinding>() {
    private val clientId = BuildConfig.GITEE_CLIENT_ID
    private val redirectUri = "http://119.29.247.108:8089/authentication/gitee"

    private lateinit var mAgentWeb: AgentWeb

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOauthBinding
        get() = FragmentOauthBinding::inflate


    companion object {
        @JvmStatic
        fun newInstance() = OAuthFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var OAuthUrl =
            "https://gitee.com/oauth/authorize?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code"
        mAgentWeb = AgentWeb.with(mActivityWR.get())
            .setAgentWebParent(mBinding.agentWebView, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .setWebChromeClient(mWebChromeClient)
            .setWebViewClient(mWebViewClient)
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
            .createAgentWeb()
            .ready()
            .go(OAuthUrl)
    }


    private val mWebViewClient = object : WebViewClient() {
        override fun onPageStarted(p0: WebView?, p1: String?, p2: Bitmap?) {
            super.onPageStarted(p0, p1, p2)
            if (p1?.startsWith(redirectUri) == true) {
                p0?.stopLoading()
                val uri = Uri.parse(p1)
                val queryParameterNames = uri.queryParameterNames

                val jsonObject = JsonObject()
                for (name in queryParameterNames) {
                    val value = uri.getQueryParameter(name)
                    jsonObject.addProperty(name, value)
                }
                if (jsonObject.has("code")) {
                    SPUtils.getInstance().put("code", jsonObject.get("code").asString)
                } else {

                }
                mActivityWR.get()?.finish()
            }
            LogUtils.d("onPageStarted: $p1")

        }
        override fun onPageFinished(view: android.webkit.WebView?, url: String?) {
            super.onPageFinished(view, url)
            LogUtils.i("onPageFinished: $url")
        }
    }
    private val mWebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(p0: WebView?, p1: Int) {
            super.onProgressChanged(p0, p1)
        }
    }


    override fun onResume() {
        mAgentWeb.webLifeCycle.onResume()
        super.onResume()
    }
    override fun onPause() {
        mAgentWeb.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onDestroyView() {
        mAgentWeb.webLifeCycle.onDestroy()
        super.onDestroyView()
    }
}