package com.yakymovych.simon.yogaapp

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yakymovych.simon.yogaapp.data.api.responses.GithubUser
import com.yakymovych.simon.yogaapp.data.repository.GithubDb
import com.yakymovych.simon.yogaapp.data.repository.GithubUserDao
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DbTest {
    private var db: GithubDb? = null
    private var usersDao: GithubUserDao? = null

    @Before
    @Throws(Exception::class)
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder<GithubDb>(
                InstrumentationRegistry.getContext(),
                GithubDb::class.java)
                .build()
        usersDao = db?.users()
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun addRecord() {
        val user = GithubUser(0,"","","","","",
                        "","","","user1","","",
                        "",false,"","","","")
        val employees: List<GithubUser> = listOf(user)
        val t = Thread {
            usersDao!!.insert(employees)
            val dbEmployees: List<GithubUser> = usersDao?.getAllUsers(0) ?: emptyList()
            assertEquals(1, dbEmployees.size)
        }
        t.start()
        t.join()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        db?.close()
    }
}