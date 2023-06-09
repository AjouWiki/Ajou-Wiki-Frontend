package com.ajoudb.ajouwiki
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EditWikiAlertDialog(private val context: Context) {

    interface OnDialogClickListener {
        fun onPositiveClick(tags: String)
        fun onNegativeClick()
    }

    fun show(onDialogClickListener: OnDialogClickListener) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.activity_edit_wiki, null)

        val editText = dialogView.findViewById<EditText>(R.id.editText)

        val alertDialogBuilder = MaterialAlertDialogBuilder(context)
            .setView(dialogView)
            .setPositiveButton("확인") { _, _ ->
                val tags = editText.text.toString()
                onDialogClickListener.onPositiveClick(tags)
            }
            .setNegativeButton("취소") { _, _ ->
                onDialogClickListener.onNegativeClick()
            }
            .create()

        alertDialogBuilder.show()
    }
}