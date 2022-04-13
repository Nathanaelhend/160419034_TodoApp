package com.example.a160419034_todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.a160419034_todoapp.model.Todo
import com.example.a160419034_todoapp.model.TodoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TodoListViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<List<Todo>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh() {
        todoLoadErrorLD.value = false
        loadingLD.value = true
        launch {
            val db = Room.databaseBuilder(getApplication(), TodoDatabase::class.java,
                "newtododb").build()

            todoLD.value = db.todoDao().selectAllTodo()
        }
    }

    fun clearTask(todo: Todo)
    {
        launch {
            val db = Room.databaseBuilder(getApplication(), TodoDatabase::class.java,
                "newtododb").build()
            db.todoDao().deleteTodo(todo)
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }
}