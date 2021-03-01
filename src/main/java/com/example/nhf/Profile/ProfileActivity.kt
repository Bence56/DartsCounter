package com.example.nhf.Profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.nhf.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.profile_item.*
import kotlinx.android.synthetic.main.profile_item.view.*
import kotlin.concurrent.thread
import android.content.Intent as Intent1

class ProfileActivity : AppCompatActivity(),ProfileAdapter.ProfileItemClickListener,
    NewProfileItemDialogFragment.NewProfileItemDialogListener{

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProfileAdapter
    private lateinit var database: ProfileListDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        //setSupportActionBar(toolbar)
        fab.setOnClickListener{
            NewProfileItemDialogFragment().show(
                supportFragmentManager,
                NewProfileItemDialogFragment.TAG
            )
        }
        database = ProfileListDatabase.getInstance(this)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView = MainRecyclerView
        adapter = ProfileAdapter(this)
        loadItemsInBackground()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
    private fun loadItemsInBackground() {
        thread {
            val items = database.profileItemDAO().getAll()
            runOnUiThread {
                adapter.update(items)
            }
        }
    }

    override fun onItemChanged(item: ProfileItem) {
        thread {
            database.profileItemDAO().update(item)
            Log.d("MainActivity", "Profile item update was successful")
        }
    }

    override fun onItemDelete(item: ProfileItem) {
        thread {
            database.profileItemDAO().deleteItem(item)
        }
    }

    override fun onProfileItemCreated(newItem: ProfileItem) {
        thread {
            val newId = database.profileItemDAO().insert(newItem)
            val newProfileItem = newItem.copy(
                id = newId
            )
            runOnUiThread {
                adapter.addItem(newProfileItem)
            }
        }
    }


}