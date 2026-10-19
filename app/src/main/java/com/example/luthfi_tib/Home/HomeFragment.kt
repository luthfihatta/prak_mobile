package com.example.luthfi_tib.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.luthfi_tib.DetailActivity
import com.example.luthfi_tib.Home.pertemuan6.SixthActivity
import com.example.luthfi_tib.Home.pertemuan_5.LimaActivity
import com.example.luthfi_tib.LoginActivity
import com.example.luthfi_tib.R
import com.example.luthfi_tib.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for requireContext() fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
        val user = requireActivity().intent.getStringExtra("username")
        val pass = requireActivity().intent.getStringExtra("password")
        val umur = requireActivity().intent.getIntExtra("umur", 0)

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
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss() // Tetap berada di halaman MainActivity
                }
                .setPositiveButton("Ya") { dialog, _ ->
                    // Pindah ke Login
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                .setCancelable(false)
                .show()
        }

        //Intent dari MainActivity ke Detail Activity
        binding.btnKembali.text = "Lihat Detail"
        binding.btnKembali.setOnClickListener {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            startActivity(intent)
        }

        binding.btnToLima.setOnClickListener {
            startActivity(Intent(requireContext(), LimaActivity::class.java))
        }
        binding.btnToEnam.setOnClickListener {
            startActivity(Intent(requireContext(), SixthActivity::class.java))
        }
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                    requireActivity().finish()
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }
}