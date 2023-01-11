package com.example.lab05

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cecs453assignment4hangman.databinding.FragmentGameControlBinding
import com.example.lab05.MainActivity.Companion.game

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class GameControlFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var _binding : FragmentGameControlBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGameControlBinding.inflate(inflater, container, false)
        val model = ViewModelProvider(requireActivity()).get(Communicator::class.java)
        model.sendGuessLeft(game.guessesLeft)
        binding.status.text = model.guessLeft.value.toString()


        model.play.observe(viewLifecycleOwner, Observer{ model.sendIncompleteWord(game.currentIncompleteWord())
            model.sendGuessLeft(game.guessesLeft)
            if(game.guessesLeft < 0) { model.sendGuessLeft(0)
            }
            binding.status.text = model.guessLeft.value.toString()

            val inputMethodManager =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)

        })



        return binding.root
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            GameControlFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}