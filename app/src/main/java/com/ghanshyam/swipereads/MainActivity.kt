package com.ghanshyam.swipereads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var newsList: RecyclerView
    lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsList = findViewById(R.id.newsList)

        getNews()
    }

    private fun getNews() {
        val news = newService.newInstance.getHeadLines("in", 1)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    Log.d("Ghanshyam", news.toString())
                    adapter = NewsAdapter(this@MainActivity, news.articles)
                    newsList.adapter = adapter
                    newsList.layoutManager = LinearLayoutManager(this@MainActivity)

                } else {
                    Log.d("Ghanshyam", "Response body is null")
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.e("Mishra1", "Error Fetching", t)

            }
        })
    }
}