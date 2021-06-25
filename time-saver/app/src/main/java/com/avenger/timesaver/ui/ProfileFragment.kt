package com.avenger.timesaver.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import com.avenger.timesaver.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class ProfileFragment : Fragment() {
    private var darkModeSwitch: Switch? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDarkModeSwitch(view);
    }

    private fun setDarkModeSwitch(view: View) {
        darkModeSwitch=view.findViewById(R.id.darkModeSwitch)

    }

}