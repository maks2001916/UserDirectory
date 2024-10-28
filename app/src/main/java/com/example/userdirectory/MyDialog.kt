package com.example.userdirectory

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MyDialog: DialogFragment() {
    private var removable: Removable ?= null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        removable = context as Removable?
    }
    override fun onCreateDialog( savedInstanceState: Bundle?): Dialog {
            val name = requireArguments().getString("name")
            val builder = AlertDialog.Builder(requireActivity())
            return builder
                .setTitle(getString(R.string.attention))
                .setMessage("${getString(R.string.delete)} ${getString(R.string.user_1)} $name")
                .setPositiveButton(getString(R.string.yes)) { dialog, which ->
                    removable?.remove(name)
                }
                .setNegativeButton(getString(R.string.cancellation), null)
                .create()
        }

}