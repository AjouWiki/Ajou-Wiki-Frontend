package com.ajoudb.ajouwiki.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ajoudb.ajouwiki.databinding.ActivityAddWikiDetailBinding
import com.ajoudb.ajouwiki.network.retrofit.RetrofitWork
import com.ajoudb.ajouwiki.network.wiki.AddWikiDetailRequestBody

class AddWikiDetailActivity : AppCompatActivity() {

    private var mBinding: ActivityAddWikiDetailBinding ?= null

    private val binding get() = mBinding!!

    private fun getExtra(): Int {
        return intent.getIntExtra("id", 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAddWikiDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val markdownEditText = binding.editText
        val stylesBar = binding.stylesBar

        markdownEditText.setStylesBar(stylesBar)

        binding.complete.setOnClickListener {
            //마크다운 잘 넘어감 (bold, italic은 안됨;;)
            Log.d("check", binding.editText.getMD())

            addWikiDetail()
        }

    }

    private fun addWikiDetail(){
        val onSuccess: () -> Unit = {
            Toast.makeText(this@AddWikiDetailActivity,
                "업로드 완료.", Toast.LENGTH_SHORT).show()
            finish()
        }
        val onFailure : () -> Unit = {
            Toast.makeText(this@AddWikiDetailActivity,
                "업로드 실패...", Toast.LENGTH_SHORT).show()
        }

        val id=getExtra()
        val title=binding.title.text.toString()
        val description=binding.editText.getMD()
        val retrofitWork = RetrofitWork()
        val wikiDetail = AddWikiDetailRequestBody(title=title, description = description)
        retrofitWork.addWikiDetailWork(wikiDetail, id, onSuccess, onFailure)
    }
}