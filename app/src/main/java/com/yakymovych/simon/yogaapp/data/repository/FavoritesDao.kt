package com.yakymovych.simon.yogaapp.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yakymovych.simon.yogaapp.data.api.responses.Result

@Dao
interface FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<Result>)

    @Query("SELECT * FROM favorites")
    fun favorites(): List<Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Result)
}