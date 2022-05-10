package com.example.a160419034_todoapp.model

import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo: Todo)

    @Query("SELECT * FROM todo ORDER BY priority DESC")
    suspend fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid = :id")
    suspend fun selectTodo(id:Int): Todo

    @Query("UPDATE todo SET title = :title, notes = :notes, priority = :priority WHERE uuid = :id")
    suspend fun update(id: Int, title: String, notes: String, priority: Int)

    @Update
    suspend fun update(todo: Todo)

    @Query("UPDATE todo SET is_done = 1 WHERE uuid = :id")
    suspend fun updateTodo(id: Int)

    @Delete
    suspend fun deleteTodo(todo: Todo)
}