package com.ajoudb.ajouwiki.activity

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ajoudb.ajouwiki.R
import com.ajoudb.ajouwiki.UserInfo
import com.ajoudb.ajouwiki.databinding.ActivityRegisterBinding
import com.ajoudb.ajouwiki.network.checkemail.CheckEmailRequestBody
import com.ajoudb.ajouwiki.network.retrofit.RetrofitWork
import com.ajoudb.ajouwiki.network.signup.SignUpRequestBody
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private var mBinding: ActivityRegisterBinding?= null
    private val binding get() = mBinding!!
    @Suppress("PrivatePropertyName")
    private val EMAIL_ADDRESS_PATTERN:Pattern = Pattern.compile(
        "^[a-zA-Z0-9._%+-]+@ajou.ac.kr$"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerEmailCheck.setOnClickListener {
            // 이메일 중복 확인
            val onSuccessful: () -> Unit = {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.text_success))
                    .setMessage(getString(R.string.text_available_email))
                    .setPositiveButton(getString(R.string.text_confirm)) { dialog, _ ->
                        dialog.dismiss()
                        binding.registerEmailField.isEnabled = false
                    }
                    .setCancelable(false)
                builder.show()
                binding.registerButton.isEnabled = true
            }
            val onFailure : (Int) -> Unit = {
                if (it == 1) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(getString(R.string.text_failure))
                        .setMessage(getString(R.string.text_dup_email))
                        .setPositiveButton(getString(R.string.text_confirm)) { dialog, _ ->
                            dialog.dismiss()
                        }
                    builder.show()
                } else if (it == 2) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(getString(R.string.text_failure))
                        .setMessage(getString(R.string.text_network_check_and_again))
                        .setPositiveButton(getString(R.string.text_confirm)) { dialog, _ ->
                            dialog.dismiss()
                        }
                    builder.show()
                }
            }
            val userEmail = CheckEmailRequestBody(
                binding.registerEmailField.text.toString()
            )
            // 이메일 유효성 검사
            if (!checkEmail(userEmail.email!!)) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.text_register))
                    .setMessage(getString(R.string.text_should_ajou))
                    .setPositiveButton(getString(R.string.text_confirm)) {
                            dialog, _ -> dialog.dismiss()
                    }
                builder.show()
            }
            else {
                val retrofitWork = RetrofitWork()
                retrofitWork.checkEmailWork(userEmail, onSuccessful, onFailure)
            }

        }
        binding.registerButton.setOnClickListener {
            // 모든 필드가 입력되었는지
            //TODO: api 명세 필요
            val onSuccessful: () -> Unit = {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.text_success))
                    .setMessage(getString(R.string.text_need_activate))
                    .setPositiveButton(getString(R.string.text_confirm)) { dialog, _ ->
                        dialog.dismiss()
                        finish()
                    }
                    .setCancelable(false)
                builder.show()
            }
            val onFailure : (Int) -> Unit = {
                if (it == 1) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(getString(R.string.text_failure))
                        .setMessage(getString(R.string.text_dup_email))
                        .setPositiveButton(getString(R.string.text_confirm)) { dialog, _ ->
                            dialog.dismiss()
                        }
                    builder.show()
                } else if (it == 2) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(getString(R.string.text_failure))
                        .setMessage(getString(R.string.text_network_check_and_again))
                        .setPositiveButton(getString(R.string.text_confirm)) { dialog, _ ->
                            dialog.dismiss()
                        }
                    builder.show()
                }
            }
            if (TextUtils.isEmpty(binding.registerName.toString()) ||
                TextUtils.isEmpty(binding.registerStudentNumber.toString()) ||
                TextUtils.isEmpty(binding.registerEmailField.toString()) ||
                !(binding.registerMaleButton.isChecked ||
                        binding.registerFemaleButton.isChecked)) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.text_register))
                    .setMessage(getString(R.string.text_all_field_required))
                    .setPositiveButton(getString(R.string.text_confirm)) {
                            dialog, _ -> dialog.dismiss()
                    }
                builder.show()
            }
            else {
                val sex: String = if (binding.registerMaleButton.isChecked)
                    "man"
                else "woman"

                val userInfo = UserInfo(
                    binding.registerId.text.toString(),
                    binding.registerName.text.toString(),
                    binding.registerStudentNumber.text.toString(),
                    binding.registerEmailField.text.toString(),
                    binding.registerDepartmentSpinner.selectedItem.toString(),
                    sex
                )
                val userData = SignUpRequestBody(
                    userInfo
                )
                val retrofitWork = RetrofitWork()
                retrofitWork.signUpUserWork(userData, onSuccessful, onFailure)

            }
        }
    }
    private fun checkEmail(email:String): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

}