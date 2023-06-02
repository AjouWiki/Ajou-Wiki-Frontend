package com.ajoudb.ajouwiki.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajoudb.ajouwiki.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private var mBinding: ActivityHomeBinding?= null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}