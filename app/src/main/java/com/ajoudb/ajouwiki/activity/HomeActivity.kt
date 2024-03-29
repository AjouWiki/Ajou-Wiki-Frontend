package com.ajoudb.ajouwiki.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ajoudb.ajouwiki.AddWikiAlertDialog
import com.ajoudb.ajouwiki.UserInfo
import com.ajoudb.ajouwiki.Wiki
import com.ajoudb.ajouwiki.adapter.WikiListAdapter
import com.ajoudb.ajouwiki.databinding.ActivityHomeBinding
import com.ajoudb.ajouwiki.network.wiki.AddWikiRequestBody
import com.ajoudb.ajouwiki.network.retrofit.RetrofitWork
import com.ajoudb.ajouwiki.network.search.SearchResponseBody
import java.io.Serializable


class HomeActivity : AppCompatActivity() {

    private var mBinding: ActivityHomeBinding?= null
    private val binding get() = mBinding!!

    private var data: ArrayList<Wiki> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userInfo = intent.intentSerializable("user_info", UserInfo::class.java) as UserInfo



        binding.addWikiButton.setOnClickListener {
            binding.addWikiButton.isEnabled = false
            val addWikiDialog = AddWikiAlertDialog(this)
            addWikiDialog.show(object : AddWikiAlertDialog.OnDialogClickListener {
                override fun onPositiveClick(name: String, tags: String) {
                    val onSuccess: () -> Unit = {
                        val onSuccess_empty: (wikiList: List<Wiki>) -> Unit = {
                            data.clear()
                            data.addAll(it)
                            binding.wikiList.adapter?.notifyDataSetChanged()
                            Toast.makeText(
                                this@HomeActivity,
                                "업로드 완료", Toast.LENGTH_SHORT
                            ).show()
                            binding.addWikiButton.isEnabled = true
                        }
                        val onFailure : () -> Unit = {
                            binding.addWikiButton.isEnabled = true
                        }
                        val retrofitWork = RetrofitWork()
                        retrofitWork.wikiWork(onSuccess_empty, onFailure)
                    }
                    val onFailure : () -> Unit = {
                        Toast.makeText(this@HomeActivity,
                            "업로드 실패", Toast.LENGTH_SHORT).show()
                        binding.addWikiButton.isEnabled = true
                    }
                    val retrofitWork = RetrofitWork()
                    val wikiinfo = AddWikiRequestBody(name, tags)
                    retrofitWork.addWikiWork(wikiinfo, onSuccess, onFailure)
                }

                override fun onNegativeClick() {
                }
            })
        }

        binding.myPageButton.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            intent.putExtra("user_info", userInfo)
            startActivity(intent)
        }

        binding.searchButton.setOnClickListener {
            val onSuccess: (wikiList: SearchResponseBody) -> Unit = {
                data.clear()
                data.addAll(it.result)
                binding.wikiList.adapter?.notifyDataSetChanged()
                binding.searchButton.isEnabled = true
            }
            val onFailure : () -> Unit = {
                data.clear()
                binding.wikiList.adapter?.notifyDataSetChanged()
                binding.searchButton.isEnabled = true
            }
            val onSuccess_empty: (wikiList: List<Wiki>) -> Unit = {
                data.clear()
                data.addAll(it)
                binding.wikiList.adapter?.notifyDataSetChanged()
                binding.searchButton.isEnabled = true
            }
            binding.searchButton.isEnabled = false
            val searchKW = binding.searchInput.text.toString()
            val retrofitWork = RetrofitWork()
            if (searchKW.isEmpty()) {
                retrofitWork.wikiWork(onSuccess_empty, onFailure)
            }
            else {
                retrofitWork.searchWork(searchKW, onSuccess, onFailure)
            }
        }

        /* Todo: initRecycler 구현 ex) binding.wikiList.adapter=WikiListAdapter(response data) */
        val onSuccess: (wikiList: List<Wiki>) -> Unit = {
            data.clear()
            data.addAll(it)

            binding.wikiList.adapter = WikiListAdapter(data).apply {
                setItemClickListener(
                    object : WikiListAdapter.ItemClickListener {
                        override fun onClick(view: View, position: Int) {
                            val id = wikiList[position].id
                            val intent = Intent(this@HomeActivity, WikiDetailActivity::class.java)

                            intent.apply {
                                this.putExtra("id", id) // 데이터 넣기
                            }
                            startActivity(intent)
                        }
                    })
            }
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

}