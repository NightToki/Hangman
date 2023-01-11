package com.example.lab05

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Communicator : ViewModel() {

    val message = MutableLiveData<Char>()
    val currentIncompleteWord = MutableLiveData<String>()
    val guessLeft = MutableLiveData<Int>()
    val result = MutableLiveData<Int>()
    val left = MutableLiveData<Int>()
    val play = MutableLiveData<Boolean>()

    fun sendMessage(text: Char) {
        message.value = text
    }

    fun sendGuessLeft(g: Int) {
        guessLeft.value = g
    }

    fun sendIncompleteWord(iCompleteWord: String) {
        currentIncompleteWord.value = iCompleteWord
    }

    fun sendResult(r: Int) {
        result.value = r
    }

    fun sendLeft(l: Int) {
        left.value = l
    }

    fun sendPlay(p : Boolean) {
        play.value = p
    }
}