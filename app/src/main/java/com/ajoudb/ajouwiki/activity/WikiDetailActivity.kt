package com.ajoudb.ajouwiki.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ajoudb.ajouwiki.AddWikiAlertDialog
import com.ajoudb.ajouwiki.EditWikiAlertDialog
import com.ajoudb.ajouwiki.Wiki
import com.ajoudb.ajouwiki.adapter.WikiDetailAdapter
import com.ajoudb.ajouwiki.adapter.WikiListAdapter
import com.ajoudb.ajouwiki.databinding.ActivityWikiDetailBinding
import com.ajoudb.ajouwiki.network.retrofit.RetrofitWork
import com.ajoudb.ajouwiki.network.wiki.AddWikiRequestBody
import com.ajoudb.ajouwiki.network.wiki.EditWikiRequestBody
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
            val intent = Intent(this, AddWikiDetailActivity::class.java)
            intent.apply {
                this.putExtra("id", getExtra()) // 데이터 넣기
            }
            startActivity(intent)
            onStop()
        }

        binding.editTag.setOnClickListener{
            binding.editTag.isEnabled = false
            val editWikiDialog = EditWikiAlertDialog(this)
            editWikiDialog.show(object : EditWikiAlertDialog.OnDialogClickListener {
                override fun onPositiveClick(tags: String) {
                    val onSuccess: () -> Unit = {

                    }
                    val onFailure: () -> Unit = {

                    }

                    val retrofitWork = RetrofitWork()
                    val wikiinfo = EditWikiRequestBody(tags)
                    retrofitWork.editWikiWork(wikiinfo, onSuccess, onFailure)
                }

                override fun onNegativeClick() {
                }
            })
        }

        initView()
    }

    private fun initView(){
        val id=getExtra()

        val onSuccess: (wikiDetail: WikiDetailResponseBody) -> Unit = {
            binding.title.text = it.result.name

            var tags=""

            for(tag in it.result.tags){
                tags += "#"
                tags += tag.name
                tags += " "
            }

            binding.tag.text = tags

            if(it.result.wiki_details.isEmpty()){
                binding.empty.visibility = View.VISIBLE
            } else{
                binding.empty.visibility = View.INVISIBLE
            }

            binding.wikiDetailList.adapter = WikiDetailAdapter(it.result.wiki_details!!).apply{
                setItemClickListener(
                    object : WikiDetailAdapter.ItemClickListener {
                        override fun onClick(view: View, position: Int) {
                            val id = wikiDetail[position].id
                            val title = wikiDetail[position].title
                            val description = wikiDetail[position].description
                            val wikiId = wikiDetail[position].wiki_id

                            val intent = Intent(this@WikiDetailActivity, EditWikiActivity::class.java)

                            intent.apply {
                                this.putExtra("id", id) // 데이터 넣기
                                this.putExtra("title", title)
                                this.putExtra("description", description)
                                this.putExtra("wikiId", wikiId)
                            }
                            startActivity(intent)
                        }
                    })
            }
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