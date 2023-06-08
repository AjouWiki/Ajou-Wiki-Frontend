package com.ajoudb.ajouwiki.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ajoudb.ajouwiki.databinding.ActivityEditWikiBinding
import com.ajoudb.ajouwiki.network.retrofit.RetrofitWork
import com.ajoudb.ajouwiki.network.wiki.AddWikiDetailRequestBody
import com.yahiaangelo.markdownedittext.MarkdownEditText

class EditWikiActivity : AppCompatActivity() {

    private var mBinding: ActivityEditWikiBinding?= null

    private val binding get() = mBinding!!

    private fun getId(): Int {
        return intent.getIntExtra("id", 0)
    }

    private fun getTitleText(): String {
        return intent.getStringExtra("title")!!
    }

    private fun getDescriptionText(): String {
        return intent.getStringExtra("description")!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityEditWikiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val markdownEditText = binding.editText
        val stylesBar = binding.stylesBar

        stylesBar.stylesList = arrayOf(
            MarkdownEditText.TextStyle.UNORDERED_LIST,
            MarkdownEditText.TextStyle.LINK)

        markdownEditText.setStylesBar(stylesBar)

        binding.title.setText(getTitleText())
        binding.editText.renderMD(getDescriptionText())

        binding.complete.setOnClickListener {
            //마크다운 잘 넘어감 (bold, italic은 안됨;;)
            Log.d("check", binding.editText.getMD())

            editWikiDetail()
        }
    }

    private fun editWikiDetail(){
        val onSuccess: () -> Unit = {
            Toast.makeText(this@EditWikiActivity,
                "업로드 완료.", Toast.LENGTH_SHORT).show()
            finish()
        }
        val onFailure : () -> Unit = {
            Toast.makeText(this@EditWikiActivity,
                "업로드 실패...", Toast.LENGTH_SHORT).show()
        }

        val id=getId()
        val title=binding.title.text.toString()
        val description=binding.editText.getMD()
        val retrofitWork = RetrofitWork()
        val wikiDetail = AddWikiDetailRequestBody(title=title, description = description)
        retrofitWork.editWikiDetailWork(wikiDetail, id, onSuccess, onFailure)
    }
}