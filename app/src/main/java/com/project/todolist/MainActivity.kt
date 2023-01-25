package com.project.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        val rv = findViewById<RecyclerView>(R.id.rvTodoItems)
        val btn = findViewById<Button>(R.id.btnAddTodo)
        val btn2 = findViewById<Button>(R.id.btnDeleteDoneTodos)
        val et = findViewById<EditText>(R.id.etTodoTitle)

        rv.adapter = todoAdapter
        rv.layoutManager = LinearLayoutManager(this)

        btn.setOnClickListener {
            val todoTitle = et.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                et.text.clear()
            }
        }
        btn2.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }

    }
}