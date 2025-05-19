package com.example.todoapp.Data.repository
import android.net.Uri
import com.example.todoapp.Data.dao.Todo.TodoDAO
import com.example.todoapp.Data.model.Todoitem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.channels.awaitClose

interface TodoRepository{
    fun getAllTodos(): Flow<List<Todoitem>>
     fun fenchtodosfromFirebase(): Flow<List<Todoitem>>
    suspend fun getTodoById(id: Int) : Todoitem?
    suspend fun insertTodo(todo: Todoitem)
    suspend fun updateTodo(todo: Todoitem)
    suspend fun deleteTodo(todo: Todoitem)
    suspend fun  uploadToFirebase(todo: Todoitem)
    suspend fun uploadImageToFirebase(imageUri: Uri): String
    suspend fun deleteTodoFirebase(todo: Todoitem)
    suspend fun updateTodoFirebase(todo: Todoitem)

}
class TodoRepositoryImpl(private val todoDao: TodoDAO):
        TodoRepository{
    override fun getAllTodos(): Flow<List<Todoitem>> {
        return todoDao.getAllTodos()
    }

    override fun fenchtodosfromFirebase(): Flow<List<Todoitem>> = callbackFlow{
        val dbref= FirebaseDatabase.getInstance().reference
            .child("todos")
        // create a listener to change any changes on child ref
        val listener= object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val todos= mutableListOf<Todoitem>()
                for (child in snapshot.children){
                    val todo = child.getValue(Todoitem::class.java)
                    todo?.let { todos.add(it) }
                }
                // corountines flow , trysend handles any expectations
                // encounterd in procces
                trySend(todos).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                  close(error.toException())
            }

        }
        // add the listener to the db reference
        dbref.addValueEventListener(listener)
        // if app is not launced close the connection
        awaitClose{dbref.removeEventListener(listener)}

    }

    //override fun fenchtodosfromFirebase(): Flow<List<Todoitem>> =flow{, static   implementation for fentching data
        //val dbref= FirebaseDatabase.getInstance().reference.child("todos")
       // val snapshot= dbref.get().await()
        val todos= mutableListOf<Todoitem>()
        // now we can popupopulate the list reference with the snapshot details
       // for(child in snapshot.children){
         //   val todo= child.getValue(Todoitem::class.java)
         //   todo?.let { todos.add(it) }
       // }
            //expose items to the view model
      //  emit(todos)
    //}

    override suspend fun getTodoById(id: Int): Todoitem?{
        return todoDao.getTodoById(id)
    }

    override suspend fun insertTodo(todo: Todoitem) {
        return todoDao.insertTodo(todo)
    }

    override suspend fun updateTodo(todo: Todoitem) {
        return todoDao.updateTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todoitem){
        return todoDao.deleteTodo(todo)
    }

    override suspend fun uploadToFirebase(todo: Todoitem) {
        //firebase db initialization
        val database= FirebaseDatabase.getInstance().reference
        // target db by name
        val  newTodoRef= database.child("todos").push()
        //Generate and capture an the unique id from firebase
        val firebaseId= newTodoRef.key?: return
        val todoWithId = todo.copy(firebase_id = "FirebaseId")
        // insert  the data to realtime database
        newTodoRef.setValue(todo)
    }
  // suspended  function
    override suspend fun uploadImageToFirebase(imageUri: Uri): String{
      val storageRef= FirebaseStorage.getInstance().reference
      val imageRef=storageRef.child("todo_images/${UUID.randomUUID()}.jpg")
      // push image to the folder above
      if (imageUri != null){
          imageRef.putFile(imageUri).await()
      }
      return imageRef.downloadUrl.await().toString()
  }

    override suspend fun deleteTodoFirebase(todo: Todoitem) {
       val fierbaseId= todo.firebase_id
        if (fierbaseId.isNotEmpty()){
            val dbref= FirebaseDatabase.getInstance().reference
                .child("todos").child(fierbaseId)
            dbref.removeValue().await()
        }else{
            throw IllegalArgumentException("firebase id is empty")
        }
    }

    override suspend fun updateTodoFirebase(todo: Todoitem) {
        val firebaseId= todo.firebase_id
        if (firebaseId.isNotEmpty()){
            val dbref= FirebaseDatabase.getInstance().reference
                .child("todos").child(firebaseId)
            dbref.setValue(todo).await()
        }else{
            throw IllegalArgumentException("firebase id is empty")
        }
    }
}
