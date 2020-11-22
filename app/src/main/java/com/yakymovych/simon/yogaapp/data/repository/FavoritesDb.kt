package com.yakymovych.simon.yogaapp.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yakymovych.simon.yogaapp.data.api.responses.Multimedia
import com.yakymovych.simon.yogaapp.data.api.responses.Result

@Database(
        entities = [Result::class, Multimedia::class],
        version = 1,
        exportSchema = false
)
abstract class FavoritesDb : RoomDatabase() {
    abstract fun favorites(): FavoritesDao
}