package com.example.lab05

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.cecs453assignment4hangman.R
import com.example.cecs453assignment4hangman.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fragmentManager = supportFragmentManager

    companion object{
        var game = Hangman(6)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.game_state, GameStateFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.add(R.id.game_result, GameResultFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


}