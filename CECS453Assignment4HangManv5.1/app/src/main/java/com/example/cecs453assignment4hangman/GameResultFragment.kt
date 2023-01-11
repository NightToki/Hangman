package com.example.lab05

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cecs453assignment4hangman.databinding.FragmentGameResultBinding
import com.example.lab05.MainActivity.Companion.game

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class GameResultFragment : Fragment() {
    private var gameResultTV: TextView? = null
    private val ourFontsize = 50f
    private var _binding : FragmentGameResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGameResultBinding.inflate(inflater, container, false)
        setUpFragmentGui(container)

        val model = ViewModelProvider(requireActivity()).get(Communicator::class.java)

        model.left.observe(viewLifecycleOwner, Observer {
            gameResultTV!!.text = "ONLY 1 LEFT"
        })

        model.result.observe(viewLifecycleOwner, Observer {
            if(game.gameOver() == 1) {
                gameResultTV!!.text = "YOU WON!"
            }
            else if(game.gameOver() == -1)  {
                gameResultTV!!.text = "YOU LOST!"
            }
        })
        return binding.root
    }

    fun setUpFragmentGui(container: ViewGroup?) {
        if (gameResultTV == null) {
            gameResultTV = TextView(getActivity())
            gameResultTV!!.gravity = Gravity.CENTER
            gameResultTV!!.setTextSize(TypedValue.COMPLEX_UNIT_SP, ourFontsize)
        }
        container!!.addView(gameResultTV)
    }
    override fun onStart() { super.onStart()
        gameResultTV!!.text = "GOOD LUCK"
    }

    fun setResult(result: String?) {
        gameResultTV!!.text = result
    }
}