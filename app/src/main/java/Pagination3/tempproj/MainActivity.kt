package studyingPagination3.tempproj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ch.ni.an.tempproj.R
import studyingPagination3.tempproj.adapters.AnimeAdapter
import studyingPagination3.tempproj.viewModel.AnimeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), TextWatcher {

    private val myModel:AnimeViewModel by viewModels()
    private lateinit var adapter:AnimeAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var editText :EditText




    override fun onCreate(savedInstanceState :Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        editText =findViewById(R.id.personName)
        editText.addTextChangedListener(this)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = AnimeAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter








    }

    override fun beforeTextChanged(s :CharSequence?, start :Int, count :Int, after :Int) {

    }

    override fun onTextChanged(s :CharSequence?, start :Int, before :Int, count :Int) {
        val name: String = editText.text.toString()
        lifecycleScope.launch{

            myModel.getListData(name).collectLatest {
                adapter.submitData(it)
                recyclerView.adapter = adapter
            }
        }
    }

    override fun afterTextChanged(s :Editable?) {

    }


}