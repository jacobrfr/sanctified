package com.google.apps.sanctified

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.prayer_create_dialog.*
import java.lang.ClassCastException

class FragmentPrayerCreateDialog : DialogFragment() {
    private lateinit var listener: PrayerCreateDialogListener

    interface PrayerCreateDialogListener {
        fun onDialogPositiveClick(subject: String, description: String)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity?.layoutInflater
        val builder = AlertDialog.Builder(activity)
            .setView(inflater?.inflate(R.layout.prayer_create_dialog, null))
            .setPositiveButton("CREATE", null)
            .setNegativeButton("CANCEL", null)
            .create()
        return builder
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = this.parentFragment as PrayerCreateDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must implement PrayerCreateDialogListener"))
        }
    }

    override fun onStart() {
        super.onStart()
        val button: Button = (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
        button.setOnClickListener {
            listener.onDialogPositiveClick(
                    (dialog as AlertDialog).prayer_create_subject.text.toString(),
                    (dialog as AlertDialog).prayer_create_description.text.toString()
            )
            this.dismiss()
        }
    }
}