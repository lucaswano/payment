package com.project.ui.screens.home.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.base.R
import com.base.fragments.BaseAppFragment
import com.blikoon.qrcodescanner.QrCodeActivity
import com.project.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_scan.*

class ScanFragment: BaseAppFragment() {
    override val layoutRes = R.layout.fragment_scan

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scan.setOnClickListener {
            scan()
        }
    }


    private fun scan(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(activity, Manifest.permission.VIBRATE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CAMERA, Manifest.permission.VIBRATE), 100)
            }else{
                openScanScreen()
            }
        }else{
            openScanScreen()
        }
    }

    private fun openScanScreen(){
        activity.startActivityForResult(Intent(activity, QrCodeActivity::class.java), MainActivity.REQUEST_SCAN)
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode != 100) return
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) openScanScreen()
    }
}