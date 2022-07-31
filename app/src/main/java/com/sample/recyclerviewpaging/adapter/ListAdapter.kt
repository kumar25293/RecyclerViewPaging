package com.sample.recyclerviewpaging.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.recyclerviewpaging.R
import com.sample.recyclerviewpaging.databinding.ListItemBinding
import com.sample.recyclerviewpaging.model.UserDetails
import android.view.animation.AlphaAnimation




class ListViewAdapter(val context:Context,var onclick:(UserDetails.Data)->Unit ) : RecyclerView.Adapter<ListViewAdapter.ViewHolder>() {

    private lateinit var binding: ListItemBinding
     var mList :ArrayList<UserDetails.Data> =ArrayList<UserDetails.Data>()


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        animateView( holder.itemView)

        mList?.let {   val data: UserDetails.Data? = mList?.get(position)
            binding.user = data
        }
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0

    }

    /********************************************************
     * Aniamte the view while scroll up/bottom in list
     ********************************************************/

    fun animateView(view:View){
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration=500
        view.animation =anim;
    }

    fun setEmployeeList(users: ArrayList<UserDetails.Data>) {
        var size = this.mList?.size
        this.mList?.addAll(users)
        var sizeNew = this.mList?.size
        println("List size $sizeNew")
        if (size != null) {
            if (sizeNew != null) {
                notifyItemRangeInserted(size,mList?.size - 1)
//                notifyItemInserted(mList?.size - 1)
//                notifyDataSetChanged()
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         binding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_item, parent, false);

        val view =binding.root
        return ViewHolder(view)
    }


   inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) ,View.OnClickListener{

           init {
               view.setOnClickListener(this)
           }

        override fun onClick(v: View?) {
            val pos= adapterPosition
                onclick( mList?.get(pos))
        }


    }


}