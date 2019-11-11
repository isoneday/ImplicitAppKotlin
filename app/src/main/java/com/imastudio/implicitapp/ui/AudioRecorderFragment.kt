package com.imastudio.implicitapp.ui


import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat

import com.imastudio.implicitapp.R

import com.imastudio.implicitapp.helper.Helper.Companion.currentDate

import kotlinx.android.synthetic.main.fragment_audiorecorder.*
import kotlinx.android.synthetic.main.fragment_audiorecorder.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 */
class AudioRecorderFragment : Fragment() {

    var recorder: MediaRecorder? = null
    var lokasifile: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_audiorecorder, container, false)
        callPermission()
        v.btnPlay.isEnabled=false
        actionClick(v)
        return v
    }

    private fun actionClick(v: View) {
        v.btnRecordStop.onClick {

            if (btnRecordStop.text.toString().equals("RECORD")){
                recorder = MediaRecorder()
                lokasifile = Environment.getExternalStorageDirectory()
                    .absolutePath+"/REC"+currentDate()+".3gp"
                recorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
                recorder?.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                recorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB)
                recorder?.setOutputFile(lokasifile)
                try {
                    recorder?.prepare()
                    recorder?.start()
                    btnRecordStop.text="STOP"
                }catch (e :IOException){
                    Log.d("error",e.printStackTrace().toString())
                }

            }else if (btnRecordStop.text.toString().equals("STOP")){
                try {
                    recorder?.stop()
                    recorder?.release()
                    recorder=null
                    btnRecordStop.text="RECORD"
                    btnPlay.isEnabled=true
                }catch (e :IOException){
                    Log.d("error",e.printStackTrace().toString())
                }
            }
        }
        v.btnPlay.onClick {
            val mp = MediaPlayer()
            try{
                mp.setDataSource(lokasifile)
                mp.prepare()
                mp.start()
            }catch (e :IOException){
                Log.d("cannot start: ",e.printStackTrace().toString())
            }
        }

    }

    private fun callPermission() {
        if (activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.RECORD_AUDIO
                )
            } != PackageManager.PERMISSION_GRANTED&& activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            } != PackageManager.PERMISSION_GRANTED&& activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity?.checkSelfPermission(
                    Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED&& activity?.checkSelfPermission(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED&& activity?.checkSelfPermission(
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE),
                    10
                )
            }
            return
        }
    }


}
