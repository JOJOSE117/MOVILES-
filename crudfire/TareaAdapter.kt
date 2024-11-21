package com.example.crudfire

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.compose.ui.layout.Layout
import androidx.recyclerview.widget.RecyclerView

class TareaAdapter(
    var listaTareas: List<Tarea>,
    val onBorrarClick:(String)-> Unit,
    val onActualizarClick: (Tarea) -> Unit
):RecyclerView.Adapter<TareaAdapter.viewHolder>() {
    class viewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val cvTarea: CardView = itemView.findViewById(R.id.cvTarea)
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
        val tvDrescripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
        val ibtnBorrar: ImageButton = itemView.findViewById(R.id.ibtnBorrar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaAdapter.viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_tarea, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: TareaAdapter.viewHolder, position: Int) {
       val tarea = listaTareas[position]

        holder.tvTitulo.text = tarea.titulo
        holder.tvDrescripcion.text = tarea.descripcion

        holder.ibtnBorrar.setOnClickListener{
            onBorrarClick(tarea.id)
        }
        holder.cvTarea.setOnClickListener{
            onActualizarClick(tarea)
        }
    }

    override fun getItemCount(): Int {
        return listaTareas.size
    }
}