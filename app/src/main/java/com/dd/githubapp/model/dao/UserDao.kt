package com.dd.githubapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dd.githubapp.model.User
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * GithubApp
 *
 * @author    daidong
 *
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM users LIMIT 0, 1")
    fun findLoginUser(): Flowable<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLoginUser(user: User): Completable

    @Query("DELETE FROM users")
    fun deleteLoginUser()

}