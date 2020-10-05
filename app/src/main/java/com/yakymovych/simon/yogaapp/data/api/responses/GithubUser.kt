package com.yakymovych.simon.yogaapp.data.api.responses

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users",
        indices = [Index(value = ["id"], unique = true)])
data class GithubUser(
        @PrimaryKey
        @SerializedName("id")
        var id: Int,
        @SerializedName("avatar_url")
        var avatarUrl: String,
        @SerializedName("events_url")
        var eventsUrl: String,
        @SerializedName("followers_url")
        var followersUrl: String,
        @SerializedName("following_url")
        var followingUrl: String,
        @SerializedName("gists_url")
        var gistsUrl: String,
        @SerializedName("gravatar_id")
        var gravatarId: String,
        @SerializedName("html_url")
        var htmlUrl: String,
        @SerializedName("login")
        var login: String,
        @SerializedName("node_id")
        var nodeId: String,
        @SerializedName("organizations_url")
        var organizationsUrl: String,
        @SerializedName("received_events_url")
        var receivedEventsUrl: String,
        @SerializedName("repos_url")
        var reposUrl: String,
        @SerializedName("site_admin")
        var siteAdmin: Boolean,
        @SerializedName("starred_url")
        var starredUrl: String,
        @SerializedName("subscriptions_url")
        var subscriptionsUrl: String,
        @SerializedName("type")
        var type: String,
        @SerializedName("url")
        var url: String
) {
        object DiffCallback : DiffUtil.ItemCallback<GithubUser>() {
                override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser)
                        = oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser)
                        = oldItem.id == newItem.id

        }
}

@Entity(tableName = "notes",
        indices = [Index(value = ["id"], unique = true)])
data class UserNote(@PrimaryKey var id: Int,var username: String, var note: String)