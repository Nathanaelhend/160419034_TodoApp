package com.example.a160419034_todoapp.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.a160419034_todoapp.model.TodoDatabase

val DB_NAME = "newtododb"

fun buildDb(context: Context) = Room.databaseBuilder(context, TodoDatabase::class.java, "newtododb")
    .addMigrations(MIGRATION_1_2, MIGRATION_1_3)
    .build()

val MIGRATION_1_2 = object : Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL")
    }
}

val MIGRATION_1_3 = object : Migration(2,3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 NOT NULL")
    }
    //Mengapa pada is_done menggunakan integer? Karena pada database sql, database membaca menggunakan
    // integer untuk menggantikan boolean.
}

val MIGRATION_3_4 = object : Migration(3,4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN todo_date INTEGER DEFAULT 0 NOT NULL")
    }
}