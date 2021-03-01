package com.example.nhf.Profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.example.nhf.R
import kotlinx.android.synthetic.main.profile_item.view.*

class ProfileAdapter(private val listener: ProfileItemClickListener) :
    RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    private val items = mutableListOf<ProfileItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val itemView: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.profile_item, parent, false)
        return ProfileViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val item = items[position]
        holder.nameTextView.text = item.name
        holder.numberofGames.text = item.number_of_games.toString()
        holder.numberOf180.text = item.number_of_180s.toString()
         if (item.gender){
            holder.iconImageView.setImageResource(R.drawable.income)
          }
          else{
                holder.iconImageView.setImageResource(R.drawable.open_book)
         }

        holder.item = item
    }


    override fun getItemCount(): Int {
        return items.size
    }

    interface ProfileItemClickListener {
        fun onItemChanged(item: ProfileItem)
        fun onItemDelete(item:ProfileItem)
    }

    fun addItem(item: ProfileItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }
    fun deleteItem(item:ProfileItem){
        items.remove(item)
        listener.onItemDelete(item)
        notifyDataSetChanged()
    }

    fun update(shoppingItems: List<ProfileItem>) {
        items.clear()
        items.addAll(shoppingItems)
        notifyDataSetChanged()
    }

    fun getAll():List<ProfileItem>{
        return items
    }

    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val iconImageView: ImageView
        val nameTextView: TextView
        val numberofGames: TextView
        val numberOf180: TextView
        val removeButton: ImageButton
        var item: ProfileItem? = null

        init {
            iconImageView = itemView.findViewById(R.id.ProfileItemIconImageView)
            nameTextView = itemView.findViewById(R.id.ProfileItemNameTextView)
            numberofGames = itemView.findViewById(R.id.ProfileItemNumberOfGamesTextView)
            numberOf180 = itemView.findViewById(R.id.ProfileItemNumumberOf180TextView)
            removeButton = itemView.findViewById(R.id.ShoppingItemRemoveButton)
            itemView.ProfileItemNameTextViewTAG.text=("Name")
            itemView.ProfileItemNumberOfGamesTextViewTAG.text=("Games played: ")
            itemView.ProfileItemNumumberOf180TextViewTAG.text=("Number of 180s :")
            removeButton.setOnClickListener{
               item?.let {
                   deleteItem(it)
               }
           }
        }

    }

}