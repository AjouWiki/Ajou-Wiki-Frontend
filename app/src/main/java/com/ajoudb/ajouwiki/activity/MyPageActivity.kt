package com.ajoudb.ajouwiki.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ajoudb.ajouwiki.UserInfo
import com.ajoudb.ajouwiki.databinding.ActivityMyPageBinding

class MyPageActivity : AppCompatActivity() {
    private var mBinding: ActivityMyPageBinding?= null
    private val binding get() = mBinding!!
    private var userInfo: UserInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myNameInput.text = userInfo!!.name
        binding.myStudentNumberInput.text = userInfo!!.studentNumber
        binding.myDepartmertInput.text = userInfo!!.department
        binding.myEmailInput.text = userInfo!!.email
        if (userInfo!!.sex == "man")
            binding.myMaleButton.isEnabled = true
        else if (userInfo!!.sex == "woman")
            binding.myFemaleButton.isEnabled = true

        binding.myConfirmButton.setOnClickListener {
            finish()
        }
    }

}