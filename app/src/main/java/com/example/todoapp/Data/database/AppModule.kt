package com.example.todoapp.Data.database

import com.example.todoapp.Data.dao.Todo.TodoDAO
import com.example.todoapp.Data.repository.TodoRepository
import com.example.todoapp.Data.repository.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import android.content.Context
import androidx.room.Room
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    //migration functionality from 1 to 2
    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL(
                "ALTER TABLE todos ADD COLUMN firebase_id "+
                "INTEGER NOT NULL DEFAULT 0"
            )
        }
    }
    @Provides
    @Singleton
    fun provideTodoRepository(dao: TodoDAO) : TodoRepository{
        return TodoRepositoryImpl(dao)
    }
    @Provides
    @Singleton
    fun  providesTodoDatabase(@ApplicationContext context:Context):
            AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "todo_db"
        ).addMigrations(MIGRATION_1_2).build()
    }
    @Provides
    fun provideTodoDAO(database: AppDatabase): TodoDAO{
        return database.todoDAO()
    }
}