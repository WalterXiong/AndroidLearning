package com.androidlearning.jetpacktest.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.androidlearning.jetpacktest.viewmodeltest.User

@Dao
interface UserDao {

    /**
     * 插入完成后还会将自动生成的主键id值返回
     */
    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun queryAllUser(): List<User>

    @Query("select * from User where age > :age")
    fun queryUserOlderThan(age: Int): List<User>

    @Delete
    fun deleteUser(user: User)

    @Query("delete from User where lastName = :lastName")
    fun deleteUserByLastName(lastName: String): Int

}