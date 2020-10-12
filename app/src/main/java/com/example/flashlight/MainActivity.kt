package com.example.flashlight

import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.sql.Time
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    private lateinit var cameraManager: CameraManager
    private lateinit var cameraId: String
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val OnFlash: Button=findViewById(R.id.button)
        val OffFlash: Button=findViewById(R.id.button2)
        val SOS: Button=findViewById(R.id.button3)
        val Data: TextView=findViewById(R.id.textView2)

        val isFlashAvailable=applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
        if (!isFlashAvailable){
            NoFlash()
        }
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            cameraId=cameraManager.cameraIdList[0]
        }
        catch (e:CameraAccessException){
            e.printStackTrace()
        }

        OnFlash.setOnClickListener {
            toast("Flash is On")
            Data.text="Flash On"
            light(true)
        }
        OffFlash.setOnClickListener {
            toast("Flash is Off")
            Data.text="Flash Off"
            light(false)
        }
        SOS.setOnClickListener {
            toast("..._ _ _...")
            Data.text="SOS"
            sos()
        }
    }
    private fun toast(DataIn:String){
        val Data=Toast.makeText(this,DataIn,Toast.LENGTH_SHORT).show()
    }
    private fun NoFlash(){
        val alert=AlertDialog.Builder(this).create()
        alert.setTitle("Sorry!")
        alert.setMessage("Sorry because this device don't have dedicated flash light")
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"Ok"){_,_->finish()}
        alert.show()
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun light(OnOff:Boolean) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                cameraManager.setTorchMode(cameraId,OnOff)
            }
        }
        catch (e:CameraAccessException){
            e.printStackTrace()
        }
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun sos()
    {
        Thread{
            Thread.sleep(1000)
            light(true)
            Thread.sleep(500)
            light(false)
            Thread.sleep(500)
            light(true)
            Thread.sleep(500)
            light(false)
            Thread.sleep(500)
            light(true)
            Thread.sleep(500)
            light(false)
            Thread.sleep(500)

            light(true)
            Thread.sleep(1000)
            light(false)
            Thread.sleep(1000)
            light(true)
            Thread.sleep(1000)
            light(false)
            Thread.sleep(1000)
            light(true)
            Thread.sleep(1000)
            light(false)
            Thread.sleep(1000)

            light(true)
            Thread.sleep(500)
            light(false)
            Thread.sleep(500)
            light(true)
            Thread.sleep(500)
            light(false)
            Thread.sleep(500)
            light(true)
            Thread.sleep(500)
            light(false)
            Thread.sleep(500)

        }.start()
    }
}