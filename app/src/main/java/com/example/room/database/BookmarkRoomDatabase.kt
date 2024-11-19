package com.example.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

//Digunakan untuk mengatur database lokal menggunakan Room.
//Class ini mendefinisikan database yang berisi entitas `Bookmark` dan
//menyediakan akses ke `BookmarkDao` untuk operasi CRUD nya


@Database(entities = [Bookmark::class], version = 1, exportSchema = false)
abstract class BookmarkRoomDatabase: RoomDatabase() {
    abstract fun bookmarkDao() : BookmarkDao?
    companion object {
        //Menggunakan pola Singleton untuk memastikan hanya ada satu instance
        //database yang aktif selama aplikasi berjalan
        @Volatile
        private var INSTANCE: BookmarkRoomDatabase? = null

        fun getDatabase(context: Context) : BookmarkRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(BookmarkRoomDatabase::class.java) {
                    INSTANCE = databaseBuilder(context.applicationContext,
                        BookmarkRoomDatabase::class.java, "bookmark_database").build()
                }
            }
            return INSTANCE
        }
    }
}