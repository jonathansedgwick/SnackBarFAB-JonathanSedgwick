package com.example.jonathan.snackbarnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var myListView: ListView
    lateinit var mainFAB: FloatingActionButton
    lateinit var addPersonFAB: FloatingActionButton
    lateinit var addAlertFAB: FloatingActionButton
    lateinit var undoClickListener: View.OnClickListener
    lateinit var redoClickListener: View.OnClickListener
    var adapter: ArrayAdapter<String>? = null
    var items = ArrayList<String>()
    var fabsVisiblity = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myListView = findViewById(R.id.my_list_view)
        mainFAB = findViewById(R.id.main_fab)
        addPersonFAB = findViewById(R.id.add_person_fab)
        addAlertFAB = findViewById(R.id.add_alert_fab)

        mainFAB.setOnClickListener {
            toggleFabsVisibility()
        }

        addPersonFAB.setOnClickListener {
            items.add("Person Added")
            adapter?.notifyDataSetChanged()
            Snackbar.make(it, "You Added a Person", Snackbar.LENGTH_LONG)
                .setAction("Undo", undoClickListener)
                .show()
        }

        undoClickListener = View.OnClickListener {
            items.removeAt(items.size-1)
            adapter?.notifyDataSetChanged()
            Snackbar.make(it, "You Added a Person", Snackbar.LENGTH_LONG)
                .setAction("Redo", redoClickListener)
                .show()
        }

        redoClickListener = View.OnClickListener {
            items.add("Another Person")
            adapter?.notifyDataSetChanged()
        }



        addAlertFAB.setOnClickListener {
            items.add("Alarm Added")
            adapter?.notifyDataSetChanged()
            Snackbar.make(it, "You Added an Alert", Snackbar.LENGTH_LONG)
                .setAction("Undo", undoClickListener)
                .show()
        }




    }

    private fun toggleFabsVisibility() {
        if (fabsVisiblity) {
            addPersonFAB.hide()
            addAlertFAB.hide()
            fabsVisiblity = false
        } else {
            addPersonFAB.show()
            addAlertFAB.show()
            fabsVisiblity = true
        }
    }
}