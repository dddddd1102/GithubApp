package com.dd.githubapp.entity

import com.google.gson.annotations.SerializedName

/**
 * GithubApp
 *
 * @author    daidong
 *
 */

data class SearchRepositories(
    @SerializedName("total_count") var totalCount: Int,
    @SerializedName("incomplete_results") var incompleteResults: Boolean,
    var items: List<Repository>
) {


}