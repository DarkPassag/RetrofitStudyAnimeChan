package studyingPagination3.tempproj.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "quotes")
data class AnimeQuote(
    @PrimaryKey val anime: String,
    @ColumnInfo val character: String,
    @ColumnInfo val quote: String
)

