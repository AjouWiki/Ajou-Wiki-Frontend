package com.ajoudb.ajouwiki.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajoudb.ajouwiki.databinding.ActivityWikiDetailBinding

class WikiDetailActivity : AppCompatActivity() {
    private var mBinding: ActivityWikiDetailBinding?= null
    private val binding get() = mBinding!!

    /*Todo: Adpater 연결 및 정보 받아와서 보여주기, 수정 버튼*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityWikiDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.add.setOnClickListener {
            val intent = Intent(this, AddWikiActivity::class.java)
            startActivity(intent)
        }
    }
}