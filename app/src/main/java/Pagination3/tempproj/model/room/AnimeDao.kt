package studyingPagination3.tempproj.model.room

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface AnimeDao {

    @Query("SELECT * FROM quotes")
    fun getQuotes(): LiveData<List<AnimeQuote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addQuote(animeQuote :AnimeQuote)

    @Delete
    fun deleteQuote(animeQuote :AnimeQuote)

}