package com.ajoudb.ajouwiki
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AddWikiAlertDialog(private val context: Context) {

    interface OnDialogClickListener {
        fun onPositiveClick(title: String, tags: String)
        fun onNegativeClick()
    }

    fun show(onDialogClickListener: OnDialogClickListener) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.add_wiki_dialog, null)

        val editText1 = dialogView.findViewById<EditText>(R.id.editText1)
        val editText2 = dialogView.findViewById<EditText>(R.id.editText2)

        val alertDialogBuilder = MaterialAlertDialogBuilder(context)
            .setView(dialogView)
            .setPositiveButton("확인") { _, _ ->
                val title = editText1.text.toString()
                val tags = editText2.text.toString()
                onDialogClickListener.onPositiveClick(title, tags)
            }
            .setNegativeButton("취소") { _, _ ->
                onDialogClickListener.onNegativeClick()
            }
            .create()

        alertDialogBuilder.show()
    }
}