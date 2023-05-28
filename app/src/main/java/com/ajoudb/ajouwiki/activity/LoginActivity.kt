package com.ajoudb.ajouwiki.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ajoudb.ajouwiki.R
import com.ajoudb.ajouwiki.UserInfo
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
            // 아이디 비번 입력 확인
            if (TextUtils.isEmpty(binding.idInput.text.toString()) ||
                    TextUtils.isEmpty(binding.passwordInput.text.toString())) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.text_login))
                    .setMessage(getString(R.string.text_require_idpw))
                    .setPositiveButton(getString(R.string.text_confirm)) {
                        dialog, _ -> dialog.dismiss()
                    }
                builder.show()

            }
            else {
                val onSuccess: (UserInfo) -> Unit = {
                    /*val intent = Intent(this, MyPageActivity::class.java)
                    intent.putExtra("user_info", it)
                    startActivity(intent)
                    finish()*/

                    val intent = Intent(this, AddWikiActivity::class.java)
                    startActivity(intent)
                }
                val onFailure : (Int) -> Unit = {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(getString(R.string.text_login_failure))
                    when (it) {
                        1 -> {
                            builder.setMessage(getString(R.string.text_check_id_password))
                                .setPositiveButton(getString(R.string.text_confirm)) { dialog, _ ->
                                    dialog.dismiss()
                                    binding.loginButton.isEnabled = true
                                }
                            builder.show()
                        }
                        2 -> {
                            builder.setMessage(getString(R.string.text_network_check))
                                .setPositiveButton(getString(R.string.text_confirm)) { dialog, _ ->
                                    dialog.dismiss()
                                    binding.loginButton.isEnabled = true
                                }
                            builder.show()
                        }
                        3 -> {
                            builder.setMessage(getString(R.string.text_still_inactivate))
                                .setPositiveButton(getString(R.string.text_confirm)) { dialog, _ ->
                                    dialog.dismiss()
                                    binding.loginButton.isEnabled = true
                                }
                            builder.show()
                        }
                    }
                }
                val userData = SignInRequestBody(
                    binding.idInput.text.toString(),
                    binding.passwordInput.text.toString()
                )
                val retrofitWork = RetrofitWork()
                retrofitWork.signInWork(userData, onSuccess, onFailure)
                binding.loginButton.isEnabled = false

            }
        }
        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

}
