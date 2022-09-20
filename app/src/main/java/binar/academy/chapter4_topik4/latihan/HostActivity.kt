package binar.academy.chapter4_topik4.latihan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import binar.academy.chapter4_topik4.databinding.ActivityHostBinding

class HostActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}