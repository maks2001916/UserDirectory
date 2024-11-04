package com.example.userdirectory

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), Removable {

    //val users: MutableList<User> = mutableListOf()
    private var adapter: ArrayAdapter<User>? = null
    lateinit var userViewModel: UserViewModel

    private lateinit var toolbarTB: Toolbar
    private lateinit var nameET: EditText
    private lateinit var ageET: EditText
    private lateinit var saveBTN: Button
    private lateinit var directoryLV: ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        toolbarTB = findViewById(R.id.mainToolbarTB)
        nameET = findViewById(R.id.nameET)
        ageET = findViewById(R.id.ageET)
        saveBTN = findViewById(R.id.saveBTN)
        directoryLV = findViewById(R.id.directoryLV)


        setSupportActionBar(toolbarTB)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userViewModel.users)
        directoryLV.adapter = adapter

        userViewModel.currentUser.observe(this, {
            userViewModel.users?.add(it)
            adapter!!.notifyDataSetChanged()
        })

        saveBTN.setOnClickListener {
            if (nameET.text.toString().isEmpty() ||
                ageET.text.toString().isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.not_all_data_is_entered),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                if (ageET.text.toString().all {it.isDigit()}) {
                    userViewModel.users?.add(User(nameET.text.toString(), ageET.text.toString().toInt()))
                    adapter!!.notifyDataSetChanged()
                    nameET.text.clear()
                    ageET.text.clear()
                } else {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.only_numbers),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        directoryLV.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                val user = adapter!!.getItem(position)
                val dialog = MyDialog()
                val args = Bundle()
                args.putString("name", user?.name)
                dialog.arguments = args
                dialog.show(supportFragmentManager, "custom")
            }

    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun remove(name: String?) {
        val userToRemove = userViewModel.users?.find { it.name == name }
        if (userToRemove != null) {
            userViewModel.users?.remove(userToRemove)
            adapter?.notifyDataSetChanged()
        }
    }
}