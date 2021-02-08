package com.example.ktAndroidSample.implicit_intent

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.afollestad.materialdialogs.MaterialDialog
import com.example.ktAndroidSample.R
import com.example.ktAndroidSample.databinding.ActivityMapBinding
import java.net.URLEncoder

class MapActivity : AppCompatActivity() {

    lateinit var binding: ActivityMapBinding
    var latitude = ""
    var longitude = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()

    }

    private fun initialize(){
        initBinding()
        initClick()
    }

    private fun initBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map)
    }

    private fun initClick(){
        binding.apply {
            currentLocation.setOnClickListener {
                this.editLocation.setText(R.string.current_location)
                searchLocation()
            }

            btSearch.also {
                it.setOnClickListener {
                    if (this.editLocation.text.isEmpty()) {
                        MaterialDialog(this@MapActivity).show {
                            title(R.string.null_location)
                            negativeButton(R.string.warn_back)
                        }
                    } else {
                        launcherMapApp()
                    }
                }
            }
        }
    }

    private fun launcherMapApp(){
        val searchWord = binding.editLocation.text.toString()

        if (searchWord == "Current location") {
            val uri = Uri.parse("geo:${latitude},${longitude}")
            this.startActivity(Intent(Intent.ACTION_VIEW, uri))

        }else{
            val uri = Uri.parse("geo:0,0?q=${URLEncoder.encode(searchWord, "UTF-8")}")
            this.startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    private fun searchLocation(){
        val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locationListener = GPSListener()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            ActivityCompat.requestPermissions(this,permissions,1000)
            return
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val locationListener = GPSListener()
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
                return
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)
        }
    }

    private inner class GPSListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            latitude = location.latitude.toString()
            longitude = location.longitude.toString()
        }
    }

    companion object {
        fun start(activity: Activity) = activity.startActivity(Intent(activity, MapActivity::class.java))
    }
}