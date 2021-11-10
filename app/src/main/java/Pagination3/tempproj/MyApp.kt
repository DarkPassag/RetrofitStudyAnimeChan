package studyingPagination3.tempproj

import android.app.Application
import android.app.ApplicationErrorReport
import studyingPagination3.tempproj.model.room.AnimeDatabase

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        AnimeDatabase.getDatabase(applicationContext)
    }
}