package com.imastudio.implicitapp.ui


import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat

import com.imastudio.implicitapp.helper.Helper.Companion.CAMERA
import com.imastudio.implicitapp.helper.Helper.Companion.currentDate
import kotlinx.android.synthetic.main.fragment_camera.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.toast
import java.io.File
import com.imastudio.implicitapp.helper.Helper.Companion.GALERY
import kotlinx.android.synthetic.main.fragment_camera.*
import java.io.InputStream

import android.R
import android.graphics.Matrix
import android.media.ExifInterface
import androidx.core.app.NotificationCompat.getExtras
import android.graphics.Bitmap
















/**
 * A simple [Fragment] subclass.
 */
class CameraFragment : Fragment() {
    var lokasiFile: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(com.imastudio.implicitapp.R.layout.fragment_camera, container, false)
        callPermission()
        actionClick(v)
        return v
    }
    private fun actionClick(v: View) {
        v.btncamera.onClick {
            var builder = StrictMode.VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())

            val namaFolder = "foto_saya"
            val destPath = context?.getExternalFilesDir(null)?.getAbsolutePath()
            val file = File(destPath, namaFolder)
            if (!file.exists()) {
                file.mkdir()
            }
            val fileGambar = File(
                destPath + "/" + namaFolder + "/VID" + currentDate() + ".jpg"
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            lokasiFile = Uri.fromFile(fileGambar)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.putExtra(MediaStore.EXTRA_OUTPUT,lokasiFile)
            startActivityForResult(intent,CAMERA)

        }
        v.btnshow.onClick {
            val galery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galery,GALERY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== CAMERA &&resultCode==RESULT_OK){
        toast("lokasi file : ${lokasiFile.toString()}")

            val photo = data?.getExtras()?.get(lokasiFile?.path) as Bitmap
            imgshow.setImageBitmap(photo)
        }else if (requestCode== GALERY &&resultCode==RESULT_OK){
            var lokasiGambar = data?.data
            var inputStream :InputStream?=null
            try {
                inputStream= lokasiGambar?.let { activity?.contentResolver?.openInputStream(it) }
            }catch (e:Exception){
                e.localizedMessage
            }
            var bitmap = BitmapFactory.decodeStream(inputStream)
            imgshow.setImageBitmap(bitmap)
        }
    }
    private fun exifToDegrees(exifOrientation: Int): Int {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270
        }
        return 0
    }


    private fun callPermission() {

        if (activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.CAMERA
                )
            } != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity?.checkSelfPermission(
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    10
                )
            }
            return
        }
    }


}
