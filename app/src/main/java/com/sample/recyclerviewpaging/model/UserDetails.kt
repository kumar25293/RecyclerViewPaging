package com.sample.recyclerviewpaging.model

import com.google.gson.annotations.SerializedName
import  com.sample.recyclerviewpaging.R
import android.widget.ImageView

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class UserDetails {
     @SerializedName("page")
     var page: Int? = null
     @SerializedName("per_page")
     var perPage: Int? = null
     @SerializedName("total")
     var total: Int? = null
     @SerializedName("total_pages")
     var totalPages: Int = 0
     @SerializedName("data")
     var data: ArrayList<Data> = arrayListOf()
     @SerializedName("support")
     var support: Support = Support()

     class Data {
         @SerializedName("id")
         var id: Int? = null
         @SerializedName("email")
         var email: String? = null
         @SerializedName("first_name")
         var firstName: String? = null
         @SerializedName("last_name")
         var lastName: String? = null
         @SerializedName("avatar")
         var avatar: String? = null
         companion object {
             @BindingAdapter("avatar")
             @JvmStatic /* denote static methoe */
             open fun loadImage(imageView: ImageView?, imageURL: String?) {
                 if (imageView != null) {
                     Glide.with(imageView.getContext())
                         .setDefaultRequestOptions(
                             RequestOptions()
                                 .circleCrop()
                         )
                         .load(imageURL)
                         .placeholder(R.drawable.loading)
                         .into(imageView)
                 }
             }
         }
     }

     class Support {

     @SerializedName("url")
     var url: String? = null
     @SerializedName("text")
     var text: String? = null
 }

}
