package com.ajoudb.ajouwiki
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EditWikiAlertDialog(private val context: Context, private val tags: String) {

    interface OnDialogClickListener {
        fun onPositiveClick(tags: String)
        fun onNegativeClick()
    }

    fun show(onDialogClickListener: OnDialogClickListener) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.modify_wiki_dialog, null)

        val editText1 = dialogView.findViewById<EditText>(R.id.editText1)
        editText1.setText(tags)
        val alertDialogBuilder = MaterialAlertDialogBuilder(context)
            .setView(dialogView)
            .setPositiveButton("확인") { _, _ ->
                val tags = editText1.text.toString()
                onDialogClickListener.onPositiveClick(tags)
            }
            .setNegativeButton("취소") { _, _ ->
                onDialogClickListener.onNegativeClick()
            }
            .setCancelable(false)
            .create()

        alertDialogBuilder.show()
    }
}