package com.ch.ni.an.retrofitstudy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch.ni.an.retrofitstudy.model.AnimeChan
import com.ch.ni.an.retrofitstudy.retrofit.Common
import com.ch.ni.an.retrofitstudy.retrofit.RetrofitServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class AnimeViewModel: ViewModel() {


    private val _animeChan = MutableLiveData<AnimeChan>()
    val animeChan: LiveData<AnimeChan> = _animeChan

    private val _state = MutableLiveData<STATE>()
    val state: LiveData<STATE> = _state

    init {
        getAnimeChan()
    }



    fun getAnimeChan(){
        viewModelScope.launch(Dispatchers.IO){
            _state.postValue(STATE.PENDING)
            try {
                updateState()
            } catch (e:Exception) {
                _state.postValue(STATE.FAIL)
            }

            }
        }


    private suspend fun updateState(){
        val mService = Common.retrofitServices
        val animeChan = mService.getAnimeChan()
        _animeChan.postValue(animeChan)
        _state.postValue(STATE.DONE)
    }


}

enum class STATE {
    PENDING, DONE, FAIL
}