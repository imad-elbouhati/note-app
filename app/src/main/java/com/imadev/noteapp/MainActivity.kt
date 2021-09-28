package com.imadev.noteapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.imadev.noteapp.databinding.ActivityMainBinding
import com.imadev.noteapp.other.ConnectionLiveData
import com.imadev.noteapp.other.hide
import com.imadev.noteapp.other.show
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var connectionLiveData: ConnectionLiveData

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        connectionLiveData.observe(this, {
            binding.apply {
                if (it) holder.hide() else holder.show()
            }
        })
    }

}