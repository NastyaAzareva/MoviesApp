package com.example.movies_v8_1.presentation.ui.home.components

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.movies_v8_1.R

class LeaveDialog: DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.leave_dialog_title)
            .setMessage(R.string.leave_dialog_message)
            .setNegativeButton("No") {
                    dialog, which -> requireActivity().finish()
            }
            .setNeutralButton("Maybe later"){
                    dialog, which -> requireActivity().finish()
            }
            .setPositiveButton("Ok"){
                    dialog, which -> Toast.makeText(requireContext(), "OK", Toast.LENGTH_LONG).show()
            }
            .setCancelable(false)
            .create()
    }
}