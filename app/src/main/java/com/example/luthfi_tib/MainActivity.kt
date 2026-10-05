package com.example.luthfi_tib

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.luthfi_tib.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val user = intent.getStringExtra("username")
        val pass = intent.getStringExtra("password")
        val umur = intent.getIntExtra("umur", 0)

        Log.e("Hasil", "umur $umur")

        binding.txtUsername.text = user
        binding.txtPassword.text = pass

        //SnackBar
        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(binding.root, "Tindakan dilakukan", Snackbar.LENGTH_LONG)
                .setAction("BATAL") {
                    // Aksi batal
                }.show()
        }

        //AlertDialog untuk LogOut
        binding.btnAlertDialog.text = "Log Out"
        binding.btnAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss() // Tetap berada di halaman MainActivity
                }
                .setPositiveButton("Ya") { dialog, _ ->
                    // Pindah ke Login
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setCancelable(false)
                .show()
        }

        //Intent dari MainActivity ke Detail Activity
        binding.btnKembali.text = "Lihat Detail"
        binding.btnKembali.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

    }
}