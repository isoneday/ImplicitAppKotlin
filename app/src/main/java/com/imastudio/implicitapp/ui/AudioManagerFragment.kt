package com.imastudio.implicitapp.ui


import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.imastudio.implicitapp.R
import kotlinx.android.synthetic.main.fragment_audiomanager.view.*
import org.jetbrains.anko.notificationManager
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class AudioManagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =inflater.inflate(R.layout.fragment_audiomanager, container, false)
        callPermission()
        actionClick(v)
        return v
    }

    private fun actionClick(v: View) {
        val manager = activity?.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        //mode audio normal/ring
        v.ring.onClick {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                manager.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_UNMUTE, 0);
                manager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_UNMUTE, 0);
                manager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, 0);
                manager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_UNMUTE, 0);
                manager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_UNMUTE, 0);
            }else{
                manager.ringerMode = AudioManager.RINGER_MODE_NORMAL

            }
            toast("dalam mode normal")
        }
        //mode audio getar
        v.vibrate.onClick {
            val  nm =activity?.getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && nm.isNotificationPolicyAccessGranted)
                manager.ringerMode = AudioManager.RINGER_MODE_VIBRATE
            toast("dalam mode vibrate")
        }
        v.silent.onClick {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                manager.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_MUTE, 0);
                manager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_MUTE, 0);
                manager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, 0);
                manager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_MUTE, 0);
                manager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_MUTE, 0);
            }else{
                manager.ringerMode = AudioManager.RINGER_MODE_SILENT

            }
            toast("dalam mode silent")
        }
    }

    private fun callPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            !activity!!.notificationManager.isNotificationPolicyAccessGranted()) {

            val intent = Intent(
                Settings
                    .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS
            )

            startActivity(intent)
        }
    }


}
