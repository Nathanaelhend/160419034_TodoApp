package com.example.a160419034_todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.a160419034_todoapp.R
import com.example.a160419034_todoapp.databinding.FragmentCreateTodoBinding
import com.example.a160419034_todoapp.model.Todo
import com.example.a160419034_todoapp.util.NotificationHelper
import com.example.a160419034_todoapp.util.TodoWorker
import com.example.a160419034_todoapp.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*
import kotlinx.android.synthetic.main.layout_todo_item.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 * Use the [CreateTodoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateTodoFragment : Fragment(), TodoAddClickListener, RadioButtonListener, TodoDateListener  {
    private lateinit var viewModel : DetailTodoViewModel
    private lateinit var dataBinding : FragmentCreateTodoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_create_todo, container, false)
        dataBinding = FragmentCreateTodoBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        //Instantiate the layout variable
        dataBinding.todo = Todo("","")
        dataBinding.dateListener = this

//        buttonAdd.setOnClickListener {
//            var radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
//            var todo = Todo(editTitle.text.toString(), editNotes.text.toString(), 0, radio.tag
//                .toString().toInt())
//            var list = listOf(todo)
//            viewModel.addTodo(list)
//            Toast.makeText(view.context,"Data added", Toast.LENGTH_SHORT).show()
//            Navigation.findNavController(it).popBackStack()
//
//
//            //membuat work request
//            val myWorkRequest = OneTimeWorkRequestBuilder<TodoWorker>()
//                .setInitialDelay(30, TimeUnit.SECONDS)
//                .setInputData(workDataOf(
//                                "title" to "Todo Created",
//                                "message" to "A new todo has been created! Stay focus!"))
//                .build()
//            WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
//        }
    }

    override fun onRadioClick(view: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onButtonAddTodo(v: View) {
            dataBinding.todo?.let {
                val list = listOf(it)
                viewModel.addTodo(list)
                Toast.makeText(v.context, "Data added", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(v).popBackStack()


                //membuat work request
                val myWorkRequest = OneTimeWorkRequestBuilder<TodoWorker>()
                    .setInitialDelay(30, TimeUnit.SECONDS)
                    .setInputData(
                        workDataOf(
                            "title" to "Todo Created",
                            "message" to "A new todo has been created! Stay focus!"
                        )
                    )
                    .build()
                WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
            }
    }

    override fun onDateClick(view: View) {
        TODO("Not yet implemented")
    }

    override fun onTimeClick(view: View) {
        TODO("Not yet implemented")
    }
}