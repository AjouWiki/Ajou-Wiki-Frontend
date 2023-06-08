package com.ajoudb.ajouwiki.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajoudb.ajouwiki.UserInfo
import com.ajoudb.ajouwiki.adapter.WikiListAdapter
import com.ajoudb.ajouwiki.databinding.ActivityHomeBinding
import com.ajoudb.ajouwiki.network.retrofit.RetrofitWork
import com.ajoudb.ajouwiki.network.wiki.WikiListResponseBody
import java.io.Serializable

class HomeActivity : AppCompatActivity() {

    private var mBinding: ActivityHomeBinding?= null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userInfo = intent.intentSerializable("user_info", UserInfo::class.java) as UserInfo



        binding.addWikiButton.setOnClickListener {
            val intent = Intent(this, AddWikiActivity::class.java)
            startActivity(intent)
        }

        binding.myPageButton.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            intent.putExtra("user_info", userInfo)
            startActivity(intent)
        }

        binding.searchButton.setOnClickListener {
            /* Todo: 검색 기능 구현*/
        }

        /* Todo: initRecycler 구현 ex) binding.wikiList.adapter=WikiListAdapter(response data) */
//        initRecycler()
        val onSuccess: () -> Unit = {
        }
        val onFailure : () -> Unit = {
        }
        val retrofitWork = RetrofitWork()
        retrofitWork.wikiWork(onSuccess, onFailure)

    }
    private fun <T: Serializable> Intent.intentSerializable(key: String, clazz: Class<T>): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.getSerializableExtra(key, clazz)
        } else {
            this.getSerializableExtra(key) as T?
        }
    }
    fun initRecycler(responseData: List<WikiListResponseBody>) {
        val adapter = WikiListAdapter(responseData)
        binding.wikiList.adapter = adapter
    }
}