package com.yakymovych.simon.yogaapp.data.repository

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yakymovych.simon.yogaapp.data.api.responses.GithubUser

@Dao
interface GithubUserDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(posts : List<GithubUser>)

	@Query("SELECT * FROM users")
	fun users() : DataSource.Factory<Int, GithubUser>

	@Query("SELECT * FROM users WHERE id>:key")
	fun getAllUsers(key: Int): List<GithubUser>
}