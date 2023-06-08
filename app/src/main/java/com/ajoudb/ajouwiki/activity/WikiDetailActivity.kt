package com.ajoudb.ajouwiki.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajoudb.ajouwiki.Wiki
import com.ajoudb.ajouwiki.adapter.WikiDetailAdapter
import com.ajoudb.ajouwiki.databinding.ActivityWikiDetailBinding
import com.ajoudb.ajouwiki.network.retrofit.RetrofitWork
import com.ajoudb.ajouwiki.network.search.SearchResponseBody
import com.ajoudb.ajouwiki.network.wiki.WikiDetailResponseBody

class WikiDetailActivity : AppCompatActivity() {
    private var mBinding: ActivityWikiDetailBinding?= null
    private val binding get() = mBinding!!

    private fun getExtra(): Int {
        return intent.getIntExtra("id", 0)
    }

    /*Todo: Adpater 연결 및 정보 받아와서 보여주기, 수정 버튼*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityWikiDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.add.setOnClickListener {
            val intent = Intent(this, AddWikiActivity::class.java)
            startActivity(intent)
            onStop()
        }

        initView()
    }

    private fun initView(){
        val id=getExtra()

        val onSuccess: (wikiDetail: WikiDetailResponseBody) -> Unit = {
            binding.title.text = it.result.name
            binding.wikiDetailList.adapter = WikiDetailAdapter(it.result.wiki_details!!)
        }
        val onFailure : () -> Unit = {
        }
        val retrofitWork = RetrofitWork()
        retrofitWork.wikiDetailWork(id, onSuccess, onFailure)
    }

    override fun onResume() {
        super.onResume()
        initView()
    }
}