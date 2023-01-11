package com.example.lab05

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cecs453assignment4hangman.databinding.FragmentGameStateBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GameStateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameStateFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var _binding : FragmentGameStateBinding? = null

    private val binding get() = _binding!!
    val game: Hangman= MainActivity.game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameStateBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)    {
        super.onViewCreated(view, savedInstanceState)
        val model = ViewModelProvider(requireActivity()).get(Communicator::class.java)
        model.currentIncompleteWord.observe(viewLifecycleOwner, Observer {
            binding.stateOfGame.text = model.currentIncompleteWord.value.toString()

            model.sendGuessLeft(game.guessesLeft)

            binding.letter.setOnClickListener   {
                binding.letter.getText().clear()
            }

            if(game.guessesLeft == 1)   {
                model.sendLeft(1)
            }

            val result: Int = game.gameOver()

            if(result != 0) {
                model.sendResult(0)
            }
        })



        binding.letter.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

                var str:String=binding.letter.text.toString()
                if (str != null && str!!.length > 0) {
                    val letter: Char = str!!.get(0)
                    game.guess(letter)
                    model.sendGuessLeft(game.guessesLeft)
                    model.sendPlay(true)
                }

            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })


    }

    override fun onStart() {
        super.onStart()
        binding.stateOfGame.setText(game.currentIncompleteWord())
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GameStateFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) = GameStateFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
        }
    }
}