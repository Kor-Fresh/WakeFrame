package com.wakeframe.app

import android.content.Intent
import android.content.ActivityNotFoundException
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.app.Dialog
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var wakeSwitch: SwitchCompat
    private lateinit var openGalleryButton: MaterialButton
    private lateinit var raidButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wakeSwitch = findViewById(R.id.switchWakeLock)
        openGalleryButton = findViewById(R.id.buttonOpenGallery)
        raidButton = findViewById(R.id.buttonRaidDialog)

        wakeSwitch.setOnCheckedChangeListener { _, isChecked ->
            applyKeepScreenOn(isChecked)
            showWakeLockStateToast(isChecked)
        }

        openGalleryButton.setOnClickListener {
            openPhotoGallery()
        }

        raidButton.setOnClickListener {
            showRaidDialog()
        }

        applyKeepScreenOn(wakeSwitch.isChecked)
    }

    private fun applyKeepScreenOn(keepOn: Boolean) {
        if (keepOn) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    private fun showWakeLockStateToast(isOn: Boolean) {
        val messageRes = if (isOn) R.string.wake_lock_on else R.string.wake_lock_off
        Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show()
    }

    private fun openPhotoGallery() {
        val intents = listOf(
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI),
            Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
            },
            Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "image/*"
                addCategory(Intent.CATEGORY_OPENABLE)
            }
        )

        for (intent in intents) {
            try {
                startActivity(Intent.createChooser(intent, getString(R.string.open_gallery)))
                return
            } catch (_: ActivityNotFoundException) {
                // Try next available approach.
            }
        }

        Toast.makeText(this, R.string.gallery_not_found, Toast.LENGTH_SHORT).show()
    }

    private fun showRaidDialog() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE

        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_raid_image, null)
        val imageView = dialogView.findViewById<ImageView>(R.id.imageRaid)
        val closeButton = dialogView.findViewById<TextView>(R.id.buttonCloseDialog)
        imageView.setImageResource(R.drawable.aion2_chimisk_3boss)

        val dialog = Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        dialog.setContentView(dialogView)
        dialog.setCancelable(true)

        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.setOnDismissListener {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        dialog.show()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }
}
