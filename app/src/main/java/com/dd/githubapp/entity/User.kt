package com.dd.githubapp.entity

import android.os.Parcelable
import com.dd.githubapp.common.anno.PoKo
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * GithubApp
 *
 * @author    daidong
 *
 */
@PoKo
@Parcelize
data class User(
    @SerializedName("avatar_url")
    var avatarUrl: String,
    @SerializedName("bio")
    var bio: String?,
    @SerializedName("blog")
    var blog: String?,
    @SerializedName("collaborators")
    var collaborators: Int,
    @SerializedName("company")
    var company: String?,
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("disk_usage")
    var diskUsage: Int,
    @SerializedName("email")
    var email: String?,
    @SerializedName("events_url")
    var eventsUrl: String,
    @SerializedName("followers")
    var followers: Int,
    @SerializedName("followers_url")
    var followersUrl: String,
    @SerializedName("following")
    var following: Int,
    @SerializedName("following_url")
    var followingUrl: String,
    @SerializedName("gists_url")
    var gistsUrl: String,
    @SerializedName("gravatar_id")
    var gravatarId: String,
    @SerializedName("hireable")
    var hireable: String?,
    @SerializedName("html_url")
    var htmlUrl: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("location")
    var location: String,
    @SerializedName("login")
    var login: String,
    @SerializedName("name")
    var name: String?,
    @SerializedName("node_id")
    var nodeId: String,
    @SerializedName("organizations_url")
    var organizationsUrl: String,
    @SerializedName("owned_private_repos")
    var ownedPrivateRepos: Int,
    @SerializedName("private_gists")
    var privateGists: Int,
    @SerializedName("public_gists")
    var publicGists: Int,
    @SerializedName("public_repos")
    var publicRepos: Int,
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
    @SerializedName("total_private_repos")
    var totalPrivateRepos: Int,
    @SerializedName("two_factor_authentication")
    var twoFactorAuthentication: Boolean,
    @SerializedName("type")
    var type: String,
    @SerializedName("updated_at")
    var updatedAt: String,
    @SerializedName("url")
    var url: String
) : Parcelable
