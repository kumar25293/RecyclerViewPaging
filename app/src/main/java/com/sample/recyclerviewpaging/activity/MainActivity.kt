package com.sample.recyclerviewpaging.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.recyclerviewpaging.BuildConfig
import com.sample.recyclerviewpaging.R
import com.sample.recyclerviewpaging.adapter.ListViewAdapter
import com.sample.recyclerviewpaging.model.UserDetails
import com.sample.recyclerviewpaging.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel:MainViewModel

    lateinit var context: Context

    lateinit var userlist:List<UserDetails.Data>

    lateinit var recyview:RecyclerView
    lateinit var progressBar:ProgressBar

    lateinit var adapter:ListViewAdapter
    lateinit var layoutManager : LinearLayoutManager

    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private var isLoading: Boolean = false
    var pagecount:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
          println("Api url == "+BuildConfig.API_URL)

        recyview = findViewById(R.id.viewusers)
        progressBar = findViewById(R.id.progressBar);

        context = this@MainActivity
        progressBar.visibility = View.GONE


        layoutManager = LinearLayoutManager(this)
        //attaches LinearLayoutManager with RecyclerView
        recyview?.layoutManager = layoutManager

        /* callback function in constructor as lambda expression */
        adapter = ListViewAdapter(context) {
            Toast.makeText(context, "${it.firstName}", Toast.LENGTH_SHORT).show()

        }
        /* callback function in constructor as lambda expression and invoke another fucntion*/

//        adapter = ListViewAdapter(context) { data -> OnItemClickListener(data) }
        recyview?.adapter = adapter
        recyview?.setHasFixedSize(true);

        pagecount++

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        loadMore(pagecount)
//            mainViewModel.getUserDetails(context,1)?.observe(this){
//            userlist =it
//
//                adapter?.setEmployeeList(userlist)
//
//                println("UserList ${userlist.size}")
//        }

        addScrollerListener()

    }

    fun OnItemClickListener( data:UserDetails.Data){
        Toast.makeText(applicationContext, "${data.firstName}", Toast.LENGTH_SHORT).show()

    }

    private fun addScrollerListener()
    {
        //attaches scrollListener with RecyclerView
        recyview.addOnScrollListener(object : RecyclerView.OnScrollListener()
        {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int)
            {
                super.onScrolled(recyclerView, dx, dy)
                // we check "dy" value greater than zero. its for allow only scroll up to fetch nextpage data
                if(dy>=0){
                    println("Y axis position $dy")
                if (!isLoading) {
                    //findLastCompletelyVisibleItemPostition() returns position of last fully visible view.
                    ////It checks, fully visible view is the last one.
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    Toast.makeText(applicationContext, "$visibleItemCount-$totalItemCount - $firstVisibleItemPosition", Toast.LENGTH_SHORT).show()
                    if (pagecount <= mainViewModel.getTotelPage()) {
                        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                            Log.d("MyTAG", "Load new list")
                            pagecount++
                            loadMore(pagecount)
                            progressBar.visibility = View.VISIBLE
                        } else {
                            progressBar.visibility = View.INVISIBLE
                        }
                    }
                }
                }
            }
        })
    }

    fun loadMore(pagecount:Int){
        mainViewModel.getUserDetails(context,pagecount)?.observe(this){
            userlist =it

            adapter?.setEmployeeList(userlist as ArrayList<UserDetails.Data>)

            println("UserList ${userlist.size}")
            progressBar.visibility = View.GONE
        }
    }
}