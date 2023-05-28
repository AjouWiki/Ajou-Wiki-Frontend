package com.ajoudb.ajouwiki.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ajoudb.ajouwiki.databinding.ActivityAddWikiBinding

class AddWikiActivity : AppCompatActivity() {

    private var mBinding: ActivityAddWikiBinding ?= null

    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAddWikiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val markdownEditText = binding.editText
        val stylesBar = binding.stylesBar

        markdownEditText.setStylesBar(stylesBar)

        binding.complete.setOnClickListener {
            Log.d("check", binding.editText.getMD())
        }

    }
}