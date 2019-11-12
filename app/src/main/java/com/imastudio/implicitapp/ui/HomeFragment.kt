package com.imastudio.implicitapp.ui


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.imastudio.implicitapp.R
import com.seljabali.library.intents.Intents
import com.seljabali.library.intents.getPlayYouTubeQuery
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.act

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    var fragment: Fragment?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val v = inflater.inflate(R.layout.fragment_home, container, false)
        v.btnaudiomanager.onClick {
            fragment = AudioManagerFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as AudioManagerFragment)?.commit()
        }
        v.btnaudiorecorder.onClick {
            fragment = AudioRecorderFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as AudioRecorderFragment)?.commit()

        }
        v.btnbrowser.onClick {
            fragment = BrowserFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as BrowserFragment)?.commit()

        }
        v.btncamera.onClick {
            fragment = CameraFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as CameraFragment)?.commit()


        }
        v.btnemail.onClick {
            fragment = EmailFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as EmailFragment)?.commit()


        }
        v.btnphone.onClick {
            fragment = PhoneFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as PhoneFragment)?.commit()


        }
        v.btnsms.onClick {
            fragment = SmsFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as SmsFragment)?.commit()


        }
        v.btntts.onClick {
            fragment = TTSFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as TTSFragment)?.commit()


        }
        v.btnvideo.onClick {
            fragment = VideoFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as VideoFragment)?.commit()

        }
        v.btnwifi.onClick {
            fragment = WifiFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as WifiFragment)?.commit()

        }
        v.btnwa.onClick {
            val wa = act.packageManager.getLaunchIntentForPackage("com.whatsapp")
            if (wa!=null){
                startActivity(wa)
            }else{
                val i = Intent()
                i.action = Intent.ACTION_VIEW
                i.data= Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
                startActivity(i)
            }
        }
        v.btnyoutube.onClick {
            startActivity(activity?.let { it1 -> Intents.getPlayYouTubeQuery(it1, "Indonesia") })
        }


        return v
    }


}
