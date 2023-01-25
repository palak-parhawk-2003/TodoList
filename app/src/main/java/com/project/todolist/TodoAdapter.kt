package com.project.todolist

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.CheckBox
import org.w3c.dom.Text

//for recycler view adapter
class TodoAdapter (
    private val todos : MutableList<Todo>
        ) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_todo,
            parent,
            false
        )
        return ViewHolder(view)
    }

    fun addTodo(todo: Todo){
        todos.add(todo )
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos(){
        todos.removeAll { todo -> todo.isChecked }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tv: TextView, isChecked: Boolean){
        if(isChecked){
            tv.paintFlags = tv.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            tv.paintFlags = tv.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currTodo = todos[position]
        holder.tv.text = currTodo.Title
        holder.cb.isChecked = currTodo.isChecked
        toggleStrikeThrough(holder.tv, currTodo.isChecked)
        holder.cb.setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThrough(holder.tv, isChecked)
            currTodo.isChecked = !currTodo.isChecked
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    class ViewHolder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        val tv: TextView = itemView.findViewById(R.id.tvTodoTitle)
        val cb: CheckBox = itemView.findViewById(R.id.cbDone)
    }
}
