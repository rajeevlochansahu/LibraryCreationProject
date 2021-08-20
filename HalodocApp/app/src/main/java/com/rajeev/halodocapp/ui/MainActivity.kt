package com.rajeev.halodocapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rajeev.halodocapp.R
import com.rajeev.halodocapp.data.network.MyAPI
import com.rajeev.halodocapp.data.repository.MyRepository
import com.rajeev.halodocapp.databinding.ActivityMainBinding
import com.rajeev.halodocapp.model.MyData
import com.rajeev.halodocapp.viewmodel.MyViewModel
import com.rajeev.halodocapp.viewmodel.MyViewModelFactory


class MainActivity : AppCompatActivity(), MyRecyclerViewAdapter.ItemClickListener {

    private var binding: ActivityMainBinding? = null
    private lateinit var viewModel: MyViewModel
    private var adapter: MyRecyclerViewAdapter? = null
    private var page = 0
    private var limit: Int = 1
        private val retrofitService = MyAPI.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MyRepository(retrofitService))).get(MyViewModel::class.java)
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = this

        adapter = MyRecyclerViewAdapter(ArrayList<MyData>(), this)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding?.recyclerView?.adapter = adapter

        viewModel.myDataList.observe(this, Observer {
            if (it != null) {
                limit = it.page
                adapter?.setData(it.myDataList)
            }
            binding?.idPBLoading?.visibility = View.GONE
        })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        fetchData()

        // adding on scroll change listener method for our nested scroll view.
        binding?.idNestedSV?.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            // on scroll change we are checking when users scroll as bottom.
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                // in this method we are incrementing page number,
                // making progress bar visible and calling get data method.
                page++
                binding?.idPBLoading?.visibility = View.VISIBLE
                fetchData()
            }
        })
    }

    private fun fetchData() {
        if (page > limit) {
            // checking if the page number is greater than limit.
            // displaying toast message in this case when page>limit.
            Toast.makeText(this, "That's all the data..", Toast.LENGTH_SHORT).show()

            // hiding our progress bar.
            binding?.idPBLoading?.visibility = View.GONE;
            return
        } else {
            viewModel.fetchPaginationData(page)
        }
    }

    private fun openInWebView(url: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("KEY", url)
        startActivity(intent)
    }

    override fun onItemClicked(data: MyData) {
        openInWebView(data.url)
    }
}