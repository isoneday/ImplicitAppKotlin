package com.imastudio.implicitapp.ui


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat

import com.imastudio.implicitapp.R
import com.imastudio.implicitapp.helper.Helper.Companion.PHONE
import kotlinx.android.synthetic.main.fragment_phone1.*
import kotlinx.android.synthetic.main.fragment_phone1.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.makeCall

/**
 * A simple [Fragment] subclass.
 */
class PhoneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v=inflater.inflate(R.layout.fragment_phone1, container, false)
        callPermission()
        actionClick(v)
        return v
    }

    private fun actionClick(v: View) {
        v.btncall.onClick {
            makeCall(edtnumber.text.toString())
        }
        v.btntampilcall.onClick { startActivity(Intent(Intent.ACTION_DIAL,
            Uri.parse("tel:${edtnumber.text.toString()}"))) }

        v.btnlistcontact.onClick {
            val i = Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI)
            i.type=ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(i,PHONE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== PHONE&&resultCode==Activity.RESULT_OK){
            var cursor: Cursor? = null
            try {
                val uri = data?.data
                cursor = uri?.let {
                    activity?.contentResolver?.query(
                        it,
                        arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER), null, null, null
                    )
                }
                if (cursor != null && cursor.moveToNext()) {
                    val phone = cursor.getString(0)
                    edtnumber.setText(phone)
                }
            } catch (e: Exception) {
                print(e.localizedMessage)
            }
        }
    }

    private fun callPermission() {
        if (activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.CALL_PHONE
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity?.checkSelfPermission(
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.CALL_PHONE),
                    10
                )
            }
            return
        }
    }


}
