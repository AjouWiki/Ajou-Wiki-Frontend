package com.ajoudb.ajouwiki.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.ajoudb.ajouwiki.EditWikiAlertDialog
import com.ajoudb.ajouwiki.R
import com.ajoudb.ajouwiki.adapter.WikiDetailAdapter
import com.ajoudb.ajouwiki.databinding.ActivityWikiDetailBinding
import com.ajoudb.ajouwiki.network.retrofit.RetrofitWork
import com.ajoudb.ajouwiki.network.wiki.EditWikiRequestBody
import com.ajoudb.ajouwiki.network.wiki.WikiDetailResponseBody
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class WikiDetailActivity : AppCompatActivity() {
    private var mBinding: ActivityWikiDetailBinding?= null
    private val binding get() = mBinding!!

    private var originTags = ""
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
            var tags = originTags
            val tagList = tags.split("#").map { it.trim() }.filter { it.isNotBlank() }
            val updatedTags = tagList.joinToString(", ")
            val id = getExtra()
            val editWikiDialog = EditWikiAlertDialog(this, updatedTags)
            editWikiDialog.show(object : EditWikiAlertDialog.OnDialogClickListener {
                override fun onPositiveClick(tags: String) {
                    val onSuccess: () -> Unit = {
                        Toast.makeText(this@WikiDetailActivity,
                            "수정 완료.", Toast.LENGTH_SHORT).show()
                        binding.editTag.isEnabled = true
                    }
                    val onFailure: () -> Unit = {
                        Toast.makeText(this@WikiDetailActivity,
                            "수정 실패.", Toast.LENGTH_SHORT).show()
                        binding.editTag.isEnabled = true
                    }


                    val retrofitWork = RetrofitWork()
                    val tags = EditWikiRequestBody(tags)
                    retrofitWork.editWikiWork(tags, id, onSuccess, onFailure)
                }

                override fun onNegativeClick() {
                    binding.editTag.isEnabled = true
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
            originTags = tags
            if(tags.length>26) {
                tags = tags.substring(0..26) + "..."
            }

            binding.tag.text = tags

            if(it.result.wiki_details.isEmpty()){
                binding.empty.visibility = View.VISIBLE
            } else{
                binding.empty.visibility = View.INVISIBLE
            }

            binding.wikiDetailList.adapter = WikiDetailAdapter(it.result.wiki_details).apply{
                setItemClickListener(
                    object : WikiDetailAdapter.ItemClickListener {
                        @RequiresApi(Build.VERSION_CODES.O)
                        override fun onClick(view: View, position: Int) {
                            val id = wikiDetail[position].id // detail_pk
                            val title = wikiDetail[position].title
                            val description = wikiDetail[position].description
                            val wikiId = wikiDetail[position].wiki_id // wiki_id

                            val intent = Intent(this@WikiDetailActivity, EditWikiDetailActivity::class.java)

                            val onSuccess: () -> Unit = {
                                val onSuccessLock: () -> Unit = {
                                    intent.apply {
                                        this.putExtra("id", id) // 데이터 넣기
                                        this.putExtra("title", title)
                                        this.putExtra("description", description)
                                        this.putExtra("wikiId", wikiId)
                                    }
                                    startActivity(intent)
                                }
                                val onFailureLock: () -> Unit = {
                                    // lock 실패
                                    Toast.makeText(this@WikiDetailActivity,
                                        "다시 시도해주세요", Toast.LENGTH_SHORT).show()
                                }
                                val retrofitWork = RetrofitWork()
                                retrofitWork.lockWork(wikiId!!, id!!, onSuccessLock, onFailureLock)

                            }

                            val onFailure: (err: String) -> Unit = { err ->
                                if (err == "fail") {
                                    // 네트워크 에러
                                    Toast.makeText(this@WikiDetailActivity,
                                        "다시 시도해주세요", Toast.LENGTH_SHORT).show()
                                }
                                else {
                                    // 다른 사람이 수정중
                                    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
                                    val offsetDateTime = OffsetDateTime.parse(err, formatter)
                                    val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                    val output = outputFormatter.format(offsetDateTime)

                                    val builder = MaterialAlertDialogBuilder(this@WikiDetailActivity)
                                    builder.setTitle("다른 사람이 수정중입니다")
                                        .setMessage("$output 이후에 다시 시도해주세요.")
                                        .setPositiveButton(getString(R.string.text_confirm)) {
                                                dialog, _ -> dialog.dismiss()
                                        }
                                    builder.show()

                                }

                            }
                            // TODO: LOCK 확인
                            val retrofitWork = RetrofitWork()
                            retrofitWork.getLockWork(wikiId!!, id!!, onSuccess, onFailure)

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