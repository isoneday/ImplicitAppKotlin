package com.imastudio.implicitapp.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.imastudio.implicitapp.R
import kotlinx.android.synthetic.main.fragment_email.*
import kotlinx.android.synthetic.main.fragment_email.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.email


class EmailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v =inflater.inflate(R.layout.fragment_email, container, false)
        // Inflate the layout for this fragment
        v.btnsend.onClick {
            //cara lama
//            val intent = Intent(Intent.ACTION_SEND)
//            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(edtto.text.toString()))
//            intent.putExtra(Intent.EXTRA_TEXT,edtmessage.text.toString())
//            intent.putExtra(Intent.EXTRA_SUBJECT,edtsubject.text.toString())
//            intent.type="message/rfc822"
//            startActivity(Intent.createChooser(intent,"pilih email client"))
       //anko email
            email(edtto.text.toString(),edtsubject.text.toString()
                ,edtmessage.text.toString())
        }
        return v
    }


}
