package com.ajoudb.ajouwiki

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ajoudb.ajouwiki.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private var mBinding: ActivityRegisterBinding?= null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var isStudentNumberDupChecked = false;
        var isEmailDupChecked = false;

        binding.registerStudentNumberCheck.setOnClickListener {
            // TODO: 학번 중복체크
            if (isStudentNumberDupChecked && isEmailDupChecked)
                binding.registerButton.isEnabled = true
        }

        binding.registerEmailCheck.setOnClickListener {
            // TODO: 이메일 중복체크
            if (isStudentNumberDupChecked && isEmailDupChecked)
                binding.registerButton.isEnabled = true
        }

        binding.registerButton.setOnClickListener {
            // TODO: 회원가입 처리
        }
    }
}