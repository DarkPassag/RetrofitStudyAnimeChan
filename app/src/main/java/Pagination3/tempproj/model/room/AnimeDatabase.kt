package studyingPagination3.tempproj.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.lang.IllegalStateException



@Database(entities = [AnimeQuote::class], version = 1, exportSchema = false)
abstract class AnimeDatabase: RoomDatabase() {
    abstract fun animeDao():AnimeDao

    companion object{

        private var INSTANCE: AnimeDatabase? = null

        fun getDatabase(context :Context):AnimeDatabase {

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                AnimeDatabase::class.java,
                "AnimeDatabase")
                    .build()
                INSTANCE = instance
                return instance
            }

        }

        fun get():AnimeDatabase {
            return INSTANCE ?:
            throw IllegalStateException("database must be initialize")
        }
    }
}