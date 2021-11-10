package studyingPagination3.tempproj.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import studyingPagination3.tempproj.model.retrofit.RetrofitService
import studyingPagination3.tempproj.model.room.AnimeQuote
import retrofit2.HttpException
import java.util.concurrent.PriorityBlockingQueue


const val PAGE_SIZE = 10

class AnimePageSource(
    private val service:RetrofitService,
    private val query: String
): PagingSource<Int, AnimeQuote>() {


    override fun getRefreshKey(state :PagingState<Int, AnimeQuote>) :Int? {
       val anchorPosition =  state.anchorPosition ?: return null
       val page =  state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params :LoadParams<Int>) :LoadResult<Int, AnimeQuote> {
        if(query.isEmpty()){
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }

        val page: Int = params.key ?: 0

        val responce = service.getPaginationQuotes(query, page)
        if(responce.isSuccessful){
           val quotes =  checkNotNull(responce.body())
            val nextKey =  if(quotes.size < PAGE_SIZE) null else page + 1
            val prefKey = if(page == 1) null else page - 1
            return LoadResult.Page(quotes, prefKey, nextKey )
        } else return LoadResult.Error(HttpException(responce))
    }
}