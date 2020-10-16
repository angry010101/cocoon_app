package com.yakymovych.simon.yogaapp

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson
import com.yakymovych.simon.yogaapp.data.api.responses.GithubUser
import com.yakymovych.simon.yogaapp.data.repository.GithubDb
import com.yakymovych.simon.yogaapp.data.repository.GithubUserDao
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

//6. Write Unit tests for data processing logic & models,
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
                .allowMainThreadQueries()
                .build()
        usersDao = db?.users()
    }

    //1. Model test
    //6. Creates a model form json string
    @Test
    @Throws(java.lang.Exception::class)
    fun testModelTest() {
        val json = "{\n" +
                "    \"login\": \"mojombo\",\n" +
                "    \"id\": 1,\n" +
                "    \"node_id\": \"MDQ6VXNlcjE=\",\n" +
                "    \"avatar_url\": \"https://avatars0.githubusercontent.com/u/1?v=4\",\n" +
                "    \"gravatar_id\": \"\",\n" +
                "    \"url\": \"https://api.github.com/users/mojombo\",\n" +
                "    \"html_url\": \"https://github.com/mojombo\",\n" +
                "    \"followers_url\": \"https://api.github.com/users/mojombo/followers\",\n" +
                "    \"following_url\": \"https://api.github.com/users/mojombo/following{/other_user}\",\n" +
                "    \"gists_url\": \"https://api.github.com/users/mojombo/gists{/gist_id}\",\n" +
                "    \"starred_url\": \"https://api.github.com/users/mojombo/starred{/owner}{/repo}\",\n" +
                "    \"subscriptions_url\": \"https://api.github.com/users/mojombo/subscriptions\",\n" +
                "    \"organizations_url\": \"https://api.github.com/users/mojombo/orgs\",\n" +
                "    \"repos_url\": \"https://api.github.com/users/mojombo/repos\",\n" +
                "    \"events_url\": \"https://api.github.com/users/mojombo/events{/privacy}\",\n" +
                "    \"received_events_url\": \"https://api.github.com/users/mojombo/received_events\",\n" +
                "    \"type\": \"User\",\n" +
                "    \"site_admin\": false\n" +
                "  }";

        val user = Gson().fromJson(json,GithubUser::class.java)

        assertEquals(user.login,"mojombo")
        assertEquals(user.id,1)
        assertEquals(user.nodeId,"MDQ6VXNlcjE=")
        assertEquals(user.avatarUrl,"https://avatars0.githubusercontent.com/u/1?v=4")
        assertEquals(user.gravatarId,"")
        assertEquals(user.url,"https://api.github.com/users/mojombo")
        assertEquals(user.htmlUrl,"https://github.com/mojombo")
        assertEquals(user.followersUrl,"https://api.github.com/users/mojombo/followers")
        assertEquals(user.followingUrl,"https://api.github.com/users/mojombo/following{/other_user}")
        assertEquals(user.gistsUrl,"https://api.github.com/users/mojombo/gists{/gist_id}")
        assertEquals(user.starredUrl,"https://api.github.com/users/mojombo/starred{/owner}{/repo}")
        assertEquals(user.subscriptionsUrl,"https://api.github.com/users/mojombo/subscriptions")
        assertEquals(user.organizationsUrl,"https://api.github.com/users/mojombo/orgs")
        assertEquals(user.reposUrl,"https://api.github.com/users/mojombo/repos")
        assertEquals(user.eventsUrl,"https://api.github.com/users/mojombo/events{/privacy}")
        assertEquals(user.receivedEventsUrl,"https://api.github.com/users/mojombo/received_events")
        assertEquals(user.type,"User")
        assertEquals(user.siteAdmin,false)
    }

    //6. Room models (validate creation)
    @Test
    @Throws(java.lang.Exception::class)
    fun addRecord() {
        val user = GithubUser(0, "", "", "", "", "",
                "", "", "", "user1", "", "",
                "", false, "", "", "", "")
        val employees: List<GithubUser> = listOf(user)
        val t = Thread {
            usersDao!!.insert(employees)
            val dbEmployees: List<GithubUser> = usersDao?.getAllUsers(0) ?: emptyList()
            assertEquals(1, dbEmployees.size)
        }
        t.start()
        t.join()
    }

    //6. Room models (validate update)
    @Test
    @Throws(java.lang.Exception::class)
    fun updateRecord() {
        val tValidateCreate = Thread {
            assertEquals(0, usersDao?.getAllUsers(0)!!.size)
            addRecord()
            val dbEmployees: List<GithubUser> = usersDao?.getAllUsers(0) ?: emptyList()
            assertEquals(1, dbEmployees.size)
            assertEquals(dbEmployees.get(0).followersUrl, "")
        }
        tValidateCreate.start()
        tValidateCreate.join()
        val user = GithubUser(0, "", "", "myfollowers",
                "", "",
                "", "", "", "user1", "", "",
                "", false, "", "", "", "")
        val employees: List<GithubUser> = listOf(user)
        val t = Thread {
            assertEquals(1, usersDao?.getAllUsers(0)!!.size)
            usersDao!!.insert(employees)
            val dbEmployees: List<GithubUser> = usersDao?.getAllUsers(0) ?: emptyList()
            assertEquals(1, dbEmployees.size)
            assertEquals(dbEmployees.get(0).followersUrl, "myfollowers")
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