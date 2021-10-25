package com.ch.ni.an.retrofitstudy


import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ch.ni.an.retrofitstudy.databinding.ActivityMainBinding
import com.ch.ni.an.retrofitstudy.retrofit.RetrofitServices



class MainActivity : AppCompatActivity() {

    private val myModel: AnimeViewModel by viewModels()
    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        bind = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        myModel.let {
            it.state.observe(this, { state ->
                when(state){
                    STATE.PENDING -> {
                        bind.progressBar.visibility = View.VISIBLE
                        bind.animeQuoteTextView.visibility = View.GONE
                        bind.animeCharacterTextView.visibility = View.GONE
                        bind.animeNameTextView.visibility = View.GONE
                        bind.favoriteImageButton.visibility = View.GONE
                    }
                    STATE.DONE -> {
                        updateUI()

                    }
                    STATE.FAIL -> {
                        updateUI()
                        val toast = Toast.makeText(
                            this,
                            getString(R.string.noInternetConnection),
                            Toast.LENGTH_LONG)
                        toast.setGravity(Gravity.CENTER,0 ,0)
                        toast.show()
                        bind.animeQuoteTextView.visibility = View.GONE
                        bind.animeCharacterTextView.visibility = View.GONE
                        bind.animeNameTextView.visibility = View.GONE
                        bind.favoriteImageButton.visibility = View.GONE

                    }
                }
            })
            it.animeChan.observe(this, {
                bind.animeNameTextView.text = it.anime
                bind.animeCharacterTextView.text = it.character
                bind.animeQuoteTextView.text = it.quote
            })
        }
        bind.nextWordButton.setOnClickListener {
            myModel.getAnimeChan()
        }
    }

    private fun updateUI(){
        bind.progressBar.visibility = View.GONE
        bind.animeQuoteTextView.visibility = View.VISIBLE
        bind.animeCharacterTextView.visibility = View.VISIBLE
        bind.animeNameTextView.visibility = View.VISIBLE
        bind.favoriteImageButton.visibility = View.VISIBLE
    }

}