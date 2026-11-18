package com.carplaydrive.app

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : Activity() {
    private fun launch(intent: Intent, fallback: String) {
        try { startActivity(intent) }
        catch (_: Exception) { Toast.makeText(this, fallback, Toast.LENGTH_LONG).show() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.mapsButton).setOnClickListener {
            val nav = Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="))
            nav.setPackage("com.google.android.apps.maps")
            launch(nav, "Google Maps yüklü değil. Lütfen Play Store'dan yükleyin.")
        }
        findViewById<Button>(R.id.youtubeButton).setOnClickListener {
            launch(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com")),
                "YouTube açılamadı.")
        }
        findViewById<Button>(R.id.appsButton).setOnClickListener {
            launch(Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME),
                "Ana ekran açılamadı.")
        }
        findViewById<Button>(R.id.iptvButton).setOnClickListener {
            val url = findViewById<EditText>(R.id.streamUrl).text.toString().trim()
            if (url.isBlank() || !(url.startsWith("https://") || url.startsWith("http://"))) {
                Toast.makeText(this, "Lütfen geçerli bir M3U8 yayın bağlantısı girin.", Toast.LENGTH_LONG).show()
            } else {
                val view = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                    setDataAndType(Uri.parse(url), "video/*")
                }
                launch(view, "Bu yayını açabilecek bir medya oynatıcı bulunamadı. VLC gibi bir oynatıcı yükleyin.")
            }
        }
    }
}
