package com.task.wisdomleaf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.task.wisdomleaf_task.Api
import com.task.wisdomleaf_task.MyAdapter
import com.task.wisdomleaf_task.Property

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>
    lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        manager = LinearLayoutManager(this)
        getAllData()

        swipeRefreshLayout.setOnRefreshListener {

            getAllData()


        }


    }

    fun getAllData() {
        Api.retrofitService.getAllData().enqueue(object : Callback<List<Property>> {
            override fun onResponse(
                call: Call<List<Property>>,
                response: Response<List<Property>>
            ) {
                if (response.isSuccessful) {
                    recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
                        myAdapter = MyAdapter(response.body()!!)
                        layoutManager = manager
                        adapter = myAdapter

                        swipeRefreshLayout.isRefreshing = false
                    }
                }
            }

            override fun onFailure(call: Call<List<Property>>, t: Throwable) {
                t.printStackTrace()
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }
}