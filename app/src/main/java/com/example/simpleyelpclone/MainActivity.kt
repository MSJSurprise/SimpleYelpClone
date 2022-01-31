package com.example.simpleyelpclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
private const val TAG = "MainActivity"
private const val BASE_URL = "https://api.yelp.com/v3/"
private const val API_KEY = "ZY9Utd_FxfPt0QFfNqQC9liVIXQHMKRV6YpvhpJgk0hfdftZtZWSzbRbXlQ7Vf3rX1swL0SxMFXqYhaXlBcqqOZ6n9ODHDyRk3SebZUqyIvkoZbKYeM1NHGxBXz3YXYx"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchTerm = intent.getStringExtra("searchTerm")
        val location = intent.getStringExtra("location")
        Log.i(TAG, "searchTerm $searchTerm")
        Log.i(TAG, "location $location")

        supportActionBar?.setTitle("$searchTerm in $location")

        val restaurants = mutableListOf<YelpRestaurant>()
        val adapter = RestaurantAdapter(this, restaurants)
        val rvRestaurant: RecyclerView = findViewById(R.id.rvRestaurants)
        rvRestaurant.adapter = adapter
        rvRestaurant.layoutManager = LinearLayoutManager(this)


        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val yelpService = retrofit.create(YelpService::class.java)
        yelpService.searchRestaurants("Bearer $API_KEY","$searchTerm", "$location").enqueue(object: Callback<YelpSearchResult>{
            override fun onResponse(call: Call<YelpSearchResult>, response: Response<YelpSearchResult>) {
                Log.i(TAG, "onResponse $response")
                val body = response.body()
                if (body == null) {
                    Log.w(TAG, "Did not receive valid response body from Yelp API... exiting")
                    Toast.makeText(this@MainActivity, "Enter a valid Search Term and Location",
                        Toast.LENGTH_LONG).show()
                    return
                }
                restaurants.addAll(body.restaurants)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                Log.i(TAG, "onFailure $t")

            }
        })

    }
}