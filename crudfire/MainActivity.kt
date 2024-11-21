package com.example.crudfire

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import androidx.lifecycle.ViewModelProvider
import com.example.crudfire.databinding.ActivityMainBinding
import com.example.crudfire.ui.theme.CRUDFIRETheme

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TareaAdapter
    private lateinit var viewModel: TareaViewModel

    var tareaEdit = Tarea()


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[TareaViewModel::class.java]

        viewModel.listaTareas.observe(this){ tareas ->
            setupRecyclerView(tareas)
        }

        binding.btnAgregarTarea.setOnClickListener{
            val tarea = Tarea(
                titulo = binding.etTitulo.text.toString(),
                descripcion = binding.etDescripcion.text.toString()
            )

            viewModel.agregarTarea(tarea)

            binding.etTitulo.setText("")
            binding.etDescripcion.setText("")
        }
        binding.btnActualizarTarea.setOnClickListener{
            tareaEdit.titulo = ""
            tareaEdit.descripcion=""

            tareaEdit.titulo=binding.etTitulo.text.toString()
            tareaEdit.descripcion=binding.etDescripcion.toString()

            viewModel.actualizarTarea(tareaEdit)

            adapter.notifyDataSetChanged()

            binding.etTitulo.setText("")
            binding.etDescripcion.setText("")
        }

        }
    fun setupRecyclerView(listaTareas: List<Tarea>){
        adapter = TareaAdapter(listaTareas, ::borrarTarea, ::actualizarTarea)
        binding.rvTareas.adapter = adapter
    }
    fun borrarTarea(id: String){
        viewModel.borrarTarea(id)
    }

    fun actualizarTarea(tarea:Tarea){
        tareaEdit = tarea
        binding.etTitulo.setText(tareaEdit.titulo)
        binding.etDescripcion.setText(tareaEdit.descripcion)
    }
    }


