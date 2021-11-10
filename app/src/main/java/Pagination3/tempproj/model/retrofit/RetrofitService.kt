package studyingPagination3.tempproj.model.retrofit

import androidx.room.PrimaryKey
import studyingPagination3.tempproj.model.room.AnimeQuote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("quotes/anime?")
    suspend fun getPaginationQuotes(
        @Query("title") title :String,
        @Query("page") page :Int,
    ) :Response<List<AnimeQuote>>




}

object Common{
    const val BASE_URL = "https://animechan.vercel.app/api/"

    val retrofit = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)

}

data class AnimeChan(
    val anime: String? = null,
    val character: String? = null,
    val quote: String
    )