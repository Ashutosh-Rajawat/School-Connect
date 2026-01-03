package com.atr.schoolconnect.presentation.fragment

import android.app.Dialog
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import com.atr.schoolconnect.R
import com.atr.schoolconnect.databinding.DialogLanguageBinding
import com.atr.schoolconnect.databinding.FragmentProfileBinding
import com.atr.schoolconnect.presentation.activity.FeePaymentActivity
import com.atr.schoolconnect.presentation.activity.LoginActivity
import com.atr.schoolconnect.presentation.activity.StudentProfileActivity
import com.atr.schoolconnect.presentation.activity.SupportActivity
import com.bumptech.glide.Glide


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        // Configure the profile menu rows (Language, Support, Student Profile, Fee Payment)
        val labels = listOf("Language", "Support", "Student Profile", "Fee Payment")
        val icons =
            listOf(R.drawable.language, R.drawable.support, R.drawable.profile_icon, R.drawable.fee)

        var rowIndex = 0

        for (i in 0 until binding.menuContainer.childCount) {
            val child = binding.menuContainer.getChildAt(i)

            val tvLabel = child.findViewById<TextView?>(R.id.tvLabel)
            val ivIcon = child.findViewById<ImageView?>(R.id.ivIcon)

            if (tvLabel != null && ivIcon != null && rowIndex < labels.size) {

                tvLabel.text = labels[rowIndex]

                Glide.with(this)
                    .load(icons[rowIndex])
                    .into(ivIcon)

                val position = rowIndex   // IMPORTANT

                child.setOnClickListener {
                    handleMenuClick(position)
                }

                rowIndex++
            }
        }
        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }

        return binding.root
    }

    private fun handleMenuClick(position: Int) {
        when (position) {
            0 -> showLanguageDialog()
            1 -> openSupport()
            2 -> openStudentProfile()
            3 -> openFeePayment()
        }
    }

    private fun openFeePayment() {
        startActivity(Intent(requireContext(), FeePaymentActivity::class.java))
    }

    private fun openStudentProfile() {
        startActivity(Intent(requireContext(), StudentProfileActivity::class.java))
    }

    private fun openSupport() {
        startActivity(Intent(requireContext(), SupportActivity::class.java))
    }

    private fun showLanguageDialog() {

        val dialog = Dialog(requireContext())
        val binding = DialogLanguageBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.setCancelable(true)

        binding.btnDone.setOnClickListener {
            when (binding.radioGroup.checkedRadioButtonId) {
                R.id.rbEnglish -> {
                    // English selected
                }

                R.id.rbHindi -> {
                    // Hindi selected
                }
            }
            dialog.dismiss()
        }

        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun showLogoutDialog() {
        val dialog = Dialog(requireContext()) // use requireContext() in Fragment
        dialog.setContentView(R.layout.dialog_logout)
        dialog.setCancelable(true)

        val btnCancel = dialog.findViewById<Button>(R.id.btnCancel)
        val btnLogout = dialog.findViewById<Button>(R.id.btnLogout)

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        btnLogout.setOnClickListener {
            dialog.dismiss()
            logoutUser()
        }

        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun logoutUser() {

        startActivity(Intent(requireActivity(), LoginActivity::class.java))
        requireActivity().finishAffinity()

    }

}

