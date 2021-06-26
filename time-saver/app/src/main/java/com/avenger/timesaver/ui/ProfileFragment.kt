package com.avenger.timesaver.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import android.widget.Button
import com.avenger.timesaver.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*


@AndroidEntryPoint

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        view.findViewById<Button>(R.id.addNewShop).setOnClickListener {
//            startActivity(Intent(view.context, AddStore::class.java))
//        }
        tvRecentAppointments.setOnClickListener {
            //launch a fragment which has details from firebase with an option to leave feedback and on click of that launch feedback form
        }

//        setDarkModeSwitch(view)
    }

//    private fun setDarkModeSwitch(view: View) {
//        darkModeSwitch=view.findViewById(R.id.darkModeSwitch)
//
//    }

}