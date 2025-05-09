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


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
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
        ).build()
    }
    @Provides
    fun provideTodoDAO(database: AppDatabase): TodoDAO{
        return database.todoDAO()
    }
}