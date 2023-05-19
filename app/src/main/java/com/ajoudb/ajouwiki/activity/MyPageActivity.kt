package com.ajoudb.ajouwiki.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ajoudb.ajouwiki.UserInfo
import com.ajoudb.ajouwiki.databinding.ActivityMyPageBinding
import java.io.Serializable

class MyPageActivity : AppCompatActivity() {
    private var mBinding: ActivityMyPageBinding?= null
    private val binding get() = mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userInfo = intent.intentSerializable("user_info", UserInfo::class.java) as UserInfo

        binding.myNameInput.text = userInfo.name
        binding.myStudentNumberInput.text = userInfo.student_id
        binding.myDepartmertInput.text = userInfo.department
        binding.myEmailInput.text = userInfo.email
        if (userInfo.sex == "man")
            binding.myMaleButton.isChecked = true
        else if (userInfo.sex == "woman")
            binding.myFemaleButton.isChecked = true

        binding.myConfirmButton.setOnClickListener {
            finish()
        }
    }

    private fun <T: Serializable> Intent.intentSerializable(key: String, clazz: Class<T>): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.getSerializableExtra(key, clazz)
        } else {
            this.getSerializableExtra(key) as T?
        }
    }
}