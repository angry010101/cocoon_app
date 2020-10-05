package com.yakymovych.simon.yogaapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yakymovych.simon.yogaapp.data.api.responses.GithubUser
import com.yakymovych.simon.yogaapp.data.api.responses.UserNote

@Dao
interface GithubUserDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(posts : List<GithubUser>)

	@Query("SELECT * FROM users")
	fun users() : DataSource.Factory<Int, GithubUser>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertNote(note: UserNote)

	@Query("SELECT * FROM notes WHERE username=:name")
	fun getNote(name: String) : LiveData<UserNote?>

	@Query("SELECT * FROM notes WHERE username=:login")
	fun getNoteSync(login: String): UserNote?

	@Query("SELECT * FROM users WHERE id>=:key")
	fun getAllUsers(key: Int): List<GithubUser>
}