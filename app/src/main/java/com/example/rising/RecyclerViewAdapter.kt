package com.example.rising

import android.support.v4.view.ViewCompat
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.support.v7.widget.RecyclerView
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView

class RecyclerViewAdapter(val itemList: List<Model>) : RecyclerView.Adapter<RecyclerViewAdapter.BuymaLikeRecyclerViewHolder>() {

    private val isAlreadyAnimated = Array(itemList.size, {false})

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuymaLikeRecyclerViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_view, null)
        return BuymaLikeRecyclerViewHolder(layoutView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    private fun startAnimation(view: View) {
        val animation = AnimationUtils.loadAnimation(view.context, R.anim.item_animation_from_bottom)
        animation.startTime = 500
        view.startAnimation(animation)
    }

    override fun onViewDetachedFromWindow(holder: BuymaLikeRecyclerViewHolder) {
        super.onViewDetachedFromWindow(holder)
        Log.d("RecyclerViewAdapter","Detached")
        holder.itemView.clearAnimation()
        ViewCompat.animate(holder.itemView).cancel()
    }

    override fun onBindViewHolder(holder: BuymaLikeRecyclerViewHolder, position: Int) {
        val model = itemList[position]

        holder.name.text = model.name
        holder.photo.setImageResource(model.photo)

        if (!isAlreadyAnimated[position]) {
            isAlreadyAnimated[position] = true
            startAnimation(holder.itemView)
        }
    }

    inner class BuymaLikeRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.country_name)
        val photo = itemView.findViewById<ImageView>(R.id.country_photo)
    }
}