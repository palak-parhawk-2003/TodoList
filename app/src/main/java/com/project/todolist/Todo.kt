package com.project.todolist
//This separate file has been made for a single todo item.
data class Todo(
    val Title: String,
    var isChecked: Boolean = false
)