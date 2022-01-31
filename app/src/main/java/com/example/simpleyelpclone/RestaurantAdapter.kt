package com.example.simpleyelpclone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import org.w3c.dom.Text
import java.util.*

class RestaurantAdapter(val context: Context, val restaurants: List<YelpRestaurant>):RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(restaurant: YelpRestaurant) {
            val tvName: TextView = itemView.findViewById(R.id.tvName)
            val ivImage: ImageView = itemView.findViewById(R.id.imageView)
            val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
            val tvAddress: TextView = itemView.findViewById(R.id.tvAddress)
            val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
            val tvNumReviews: TextView = itemView.findViewById(R.id.tvNumReviews)
            val tvDistance: TextView = itemView.findViewById(R.id.tvDistance)
            val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)

            tvName.text = restaurant.name
           // ivImage.setImageResource(restaurant.imageUrl)
            Glide.with(context).load(restaurant.imageUrl).apply(RequestOptions().transforms(
                CenterCrop(), RoundedCorners(20)
            )).into(ivImage)
            ratingBar.rating = restaurant.rating.toFloat()
            tvAddress.text = restaurant.location.address
            tvCategory.text = restaurant.categories[0].title
            tvNumReviews.text = "${restaurant.numReviews} Reviews"
            tvDistance.text = restaurant.displayDistance()
            tvPrice.text = restaurant.price

        }
    }
}
