package com.example.tabexamplekotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*

class FoundFragment : Fragment() {

    //Здесь привязывать переменные к экрану
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d(TAG,"Added devices is opened")
        return inflater.inflate(R.layout.fragment_found, container, false)
    }

    //заявляем, что будем создавать контекстное меню
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    //создаем меню
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_found, menu);

        super.onCreateOptionsMenu(menu, inflater)
        Log.d(TAG, "Menu is created")
    }

    //Обрабатываем нажатие на пункт меню
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // Handle item selection
        return when (item?.itemId) {
            R.id.update -> {
                Log.d(TAG, "Update button us clicked")
                true
            }
            R.id.settings -> {
                Log.d(TAG, "Settings button is clicked")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}