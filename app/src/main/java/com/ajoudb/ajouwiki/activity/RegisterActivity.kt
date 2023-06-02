package com.ajoudb.ajouwiki.activity

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ajoudb.ajouwiki.R
import com.ajoudb.ajouwiki.databinding.ActivityRegisterBinding
import com.ajoudb.ajouwiki.network.checkemail.CheckEmailRequestBody
import com.ajoudb.ajouwiki.network.checkid.CheckIdRequestBody
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
    private var isIdChecked = false
    private var isEmailChecked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerEmailCheck.setOnClickListener {
            // 이메일 중복 확인
            val onSuccess: () -> Unit = {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.text_success))
                    .setMessage(getString(R.string.text_available_email))
                    .setPositiveButton(getString(R.string.text_confirm)) { dialog, _ ->
                        dialog.dismiss()
                        binding.registerEmailField.isEnabled = false
                    }
                    .setCancelable(false)
                builder.show()
                if (isIdChecked)
                    binding.registerButton.isEnabled = true
            }
            val onFailure : (Int) -> Unit = {
                if (it == 1) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(getString(R.string.text_failure))
                        .setMessage(getString(R.string.text_dup_email))
                        .setPositiveButton(getString(R.string.text_confirm)) { dialog, _ ->
                            dialog.dismiss()
                            binding.registerEmailCheck.isEnabled = true
                        }
                        .setCancelable(false)
                    builder.show()
                } else if (it == 2) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(getString(R.string.text_failure))
                        .setMessage(getString(R.string.text_network_check_and_again))
                        .setPositiveButton(getString(R.string.text_confirm)) { dialog, _ ->
                            dialog.dismiss()
                            binding.registerEmailCheck.isEnabled = true
                        }
                        .setCancelable(false)
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
                binding.registerEmailCheck.isEnabled = false
                val retrofitWork = RetrofitWork()
                retrofitWork.checkEmailWork(userEmail, onSuccess, onFailure)
            }

        }
        binding.registerIdCheck.setOnClickListener {
            // 이메일 중복 확인
            val onSuccess: () -> Unit = {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.text_success))
                    .setMessage(getString(R.string.text_available_id))
                    .setPositiveButton(getString(R.string.text_confirm)) { dialog, _ ->
                        dialog.dismiss()
                        binding.registerIdField.isEnabled = false
                    }
                    .setCancelable(false)
                builder.show()
                isIdChecked = true
                if (isEmailChecked)
                    binding.registerButton.isEnabled = true
            }
            val onFailure : (Int) -> Unit = {
                if (it == 1) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(getString(R.string.text_failure))
                        .setMessage(getString(R.string.text_dup_id))
                        .setPositiveButton(getString(R.string.text_confirm)) { dialog, _ ->
                            dialog.dismiss()
                            binding.registerIdCheck.isEnabled = true
                        }
                        .setCancelable(false)
                    builder.show()
                } else if (it == 2) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(getString(R.string.text_failure))
                        .setMessage(getString(R.string.text_network_check_and_again))
                        .setPositiveButton(getString(R.string.text_confirm)) { dialog, _ ->
                            dialog.dismiss()
                            binding.registerIdCheck.isEnabled = true
                        }
                        .setCancelable(false)
                    builder.show()
                }
            }
            val userId = CheckIdRequestBody(
                binding.registerIdField.text.toString()
            )
            binding.registerIdCheck.isEnabled = false
            val retrofitWork = RetrofitWork()
            retrofitWork.checkIdWork(userId, onSuccess, onFailure)

        }
        binding.registerButton.setOnClickListener {
            val onSuccess: () -> Unit = {
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
                        .setMessage(getString(R.string.text_try_again))
                        .setPositiveButton(getString(R.string.text_confirm)) { dialog, _ ->
                            dialog.dismiss()
                            binding.registerButton.isEnabled = true
                        }
                    builder.show()
                } else if (it == 2) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(getString(R.string.text_failure))
                        .setMessage(getString(R.string.text_network_check_and_again))
                        .setPositiveButton(getString(R.string.text_confirm)) { dialog, _ ->
                            dialog.dismiss()
                            binding.registerButton.isEnabled = true
                        }
                    builder.show()
                }
            }
            if (TextUtils.isEmpty(binding.registerName.text.toString()) ||
                TextUtils.isEmpty(binding.registerStudentNumber.text.toString()) ||
                TextUtils.isEmpty(binding.registerEmailField.text.toString()) ||
                TextUtils.isEmpty(binding.registerPassword.text.toString()) ||
                TextUtils.isEmpty(binding.registerPasswordCheck.text.toString()) ||
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
            else if (binding.registerPassword.text.toString() != binding.registerPasswordCheck.text.toString()) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.text_check_password))
                    .setMessage(getString(R.string.text_not_equal_password))
                    .setPositiveButton(getString(R.string.text_confirm)) {
                            dialog, _ -> dialog.dismiss()
                    }
                builder.show()
            }
            else {
                val sex: String = if (binding.registerMaleButton.isChecked)
                    "man"
                else "woman"

                val userData = SignUpRequestBody(
                    binding.registerIdField.text.toString(),
                    binding.registerPassword.text.toString(),
                    binding.registerName.text.toString(),
                    binding.registerStudentNumber.text.toString(),
                    binding.registerEmailField.text.toString(),
                    binding.registerDepartmentSpinner.selectedItem.toString(),
                    sex
                )
                binding.registerButton.isEnabled = false
                val retrofitWork = RetrofitWork()
                retrofitWork.signUpUserWork(userData, onSuccess, onFailure)

            }
        }
    }
    private fun checkEmail(email:String): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

}