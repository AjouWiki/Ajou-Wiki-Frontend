package com.ajoudb.ajouwiki

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ajoudb.ajouwiki.databinding.ActivityLoginBinding
import com.ajoudb.ajouwiki.network.retrofit.RetrofitWork
import com.ajoudb.ajouwiki.network.signin.SignInRequestBody

class LoginActivity : AppCompatActivity() {
    private var mBinding: ActivityLoginBinding ?= null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            if (TextUtils.isEmpty(binding.idInput.text.toString()) ||
                    TextUtils.isEmpty(binding.passwordInput.text.toString())) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("로그인")
                    .setMessage("아이디와 비밀번호를 입력해주세요")
                    .setPositiveButton("확인") {
                        dialog, Int -> dialog.dismiss()
                    }
                builder.show()
            }
            else {
                // TODO: 로그인 작업
                val userData = SignInRequestBody(
                    binding.idInput.text.toString(),
                    binding.passwordInput.text.toString()
                )
                val retrofitWork = RetrofitWork()
                retrofitWork.signInWork(userData)
                binding.loginButton.isEnabled = false;

            }
        }
        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

}
