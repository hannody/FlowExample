package com.abunayla.flowexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abunayla.flowexample.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        val data = flow {
            for (i in 1..10) {
                emit(i)
                delay(1000L)
            }
        }


        GlobalScope.launch {
            data.buffer().filter {
                    it % 2 == 0
                }.map{
                    it * it
            }.collect {
                    println(it)
                    delay(2000L)
                }

        }

        setContentView(view)


    }
}