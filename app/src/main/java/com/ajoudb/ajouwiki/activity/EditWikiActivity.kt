package com.ajoudb.ajouwiki.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ajoudb.ajouwiki.databinding.ActivityEditWikiBinding

class EditWikiActivity : AppCompatActivity() {

    private var mBinding: ActivityEditWikiBinding?= null

    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityEditWikiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val markdownEditText = binding.editText
        val stylesBar = binding.stylesBar

        markdownEditText.setStylesBar(stylesBar)

        binding.complete.setOnClickListener {
            //마크다운 잘 넘어감 (bold, italic은 안됨;;)
            Log.d("check", binding.editText.getMD())
        }

    }
}