package com.example.myapplication.view.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.adapter.JadwalAdapter
import com.example.myapplication.model.DataModel

import com.example.myapplication.R
import com.example.myapplication.model.ItemModel
import com.example.myapplication.rest.ApiConfig
import com.example.myapplication.rest.ApiService
import kotlinx.android.synthetic.main.fragment_jadwal.view.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class JadwalFragment : Fragment() {
    private var items: ArrayList<ItemModel> = arrayListOf()
    private lateinit var rv: RecyclerView
    private lateinit var mAdapter: JadwalAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_jadwal, container, false)
        rv= view.rv
        val apiService: ApiService = ApiConfig.provideApi()
        apiService.getJadwalKuliah()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                items.clear()
                items = it.data as ArrayList<ItemModel>
                mAdapter = JadwalAdapter(items, activity)
                rv.visibility = View.VISIBLE
                rv.layoutManager = LinearLayoutManager(activity)
                rv.adapter = mAdapter

            }, {
                error("Error" + it.message)
            })

        return view
    }

    private fun getDatas() {

    }


}
