package com.example.imgpurr.screen.imgur

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.imgpurr.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prepareObservables()
        prepareComponents()
    }

    private fun prepareObservables() {

    }

    private fun prepareComponents() {

    }
}
