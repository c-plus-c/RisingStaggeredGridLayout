package com.example.rising

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)

        staggeredGridLayoutManager = StaggeredGridLayoutManager(2, 1)
        recyclerView.layoutManager = staggeredGridLayoutManager

        val listViewItems = ArrayList<Model>()

        val r = Random()
        (1..50).forEach {
            val lists = listOf(R.drawable.halloween1, R.drawable.halloween2, R.drawable.lenna, R.drawable.bug1, R.drawable.bug2)
            val index = r.nextInt(lists.count())
            listViewItems.add(Model(it.toString(), lists[index]))
        }

        val adapter = RecyclerViewAdapter(listViewItems)
        recyclerView.adapter = adapter
    }
}
