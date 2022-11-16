package com.skydio.networkconnector

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.skydio.networkconnector.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onResume() {
        super.onResume()
        ensurePermissions()
    }

    private fun ensurePermissions() {
        val locationStatus = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        if (locationStatus != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0)
        }
    }

}
