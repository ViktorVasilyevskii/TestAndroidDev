package com.vasilevskii.testandroiddev.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.vasilevskii.testandroiddev.R
import com.vasilevskii.testandroiddev.databinding.ActivityMainBinding
import com.vasilevskii.testandroiddev.ui.fragment.OneScreenFragment

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.container)
    }
}