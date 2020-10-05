package com.yakymovych.simon.yogaapp.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yakymovych.simon.yogaapp.data.api.responses.GithubUser
import com.yakymovych.simon.yogaapp.data.api.responses.UserNote

@Database(
	entities = [GithubUser::class, UserNote::class],
	version = 1,
	exportSchema = false
)
abstract class GithubDb : RoomDatabase(){
	abstract fun users(): GithubUserDao
}