package com.imastudio.implicitapp.ui


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.imastudio.implicitapp.R
import com.imastudio.implicitapp.helper.Helper
import kotlinx.android.synthetic.main.fragment_camera.*
import kotlinx.android.synthetic.main.fragment_video.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.toast
import java.io.File
import java.io.InputStream

/**
 * A simple [Fragment] subclass.
 */
class VideoFragment : Fragment() {
    var lokasiFile: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      val v =inflater.inflate(R.layout.fragment_video, container, false)
v.btnvideo.onClick {
    var builder = StrictMode.VmPolicy.Builder()
    StrictMode.setVmPolicy(builder.build())

    val namaFolder = "video_saya"
    val destPath = context?.getExternalFilesDir(null)?.getAbsolutePath()
    val file = File(destPath, namaFolder)
    if (!file.exists()) {
        file.mkdir()
    }
    val fileGambar = File(
        destPath + "/" + namaFolder + "/VID" + Helper.currentDate() + ".mp4"
    )
    val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
    lokasiFile = Uri.fromFile(fileGambar)
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    intent.putExtra(MediaStore.EXTRA_OUTPUT,lokasiFile)
    startActivityForResult(intent, Helper.CAMERA)
}
        return v
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== Helper.CAMERA &&resultCode== Activity.RESULT_OK){
            toast("lokasi file : ${lokasiFile.toString()}")

        }
    }


}
