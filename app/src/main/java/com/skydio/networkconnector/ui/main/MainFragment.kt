package com.skydio.networkconnector.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skydio.networkconnector.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.requestCallbackStepsLiveData.observe(viewLifecycleOwner, callback_steps::setText)
        connect.setOnClickListener {
            viewModel.ssid = ssid.text.toString().trim()
            viewModel.password = password.text.toString()
            viewModel.connect()
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}
