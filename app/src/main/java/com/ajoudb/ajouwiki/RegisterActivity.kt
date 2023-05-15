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


    }
}