package studyingPagination3.tempproj.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import studyingPagination3.tempproj.model.AnimePageSource
import studyingPagination3.tempproj.model.PAGE_SIZE
import studyingPagination3.tempproj.model.retrofit.Common
import studyingPagination3.tempproj.model.retrofit.RetrofitClient
import studyingPagination3.tempproj.model.retrofit.RetrofitService
import studyingPagination3.tempproj.model.room.AnimeDatabase
import studyingPagination3.tempproj.model.room.AnimeQuote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class AnimeViewModel(): ViewModel() {

   private lateinit var retrofit:RetrofitService

   init {
       retrofit = Common.retrofit
   }

    fun getListData(character: String):Flow<PagingData<AnimeQuote>>{
        return Pager (PagingConfig(10),
            pagingSourceFactory = {
                AnimePageSource(retrofit,character )
            }).flow
    }






}