package com.dd.githubapp.ui.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dd.githubapp.R
import com.dd.githubapp.ui.adapter.RepoAdapter
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_repo.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var repoAdapter: RepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("HomeFragment", "HomeFragment#onCreateView")
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        init()
        return root
    }

    private fun init() {
        repoAdapter = RepoAdapter()

        homeViewModel.text.observe(this, Observer {
            tv_repo_stars.setOnClickListener(View.OnClickListener {

            })
            rv_repo.setAdapter(repoAdapter)
            rv_repo.setOnPullLoadMoreListener(object :
                PullLoadMoreRecyclerView.PullLoadMoreListener {
                override fun onRefresh() {
                    Log.d("HomeFragment", "onRefresh")
                }

                override fun onLoadMore() {
                    Log.d("HomeFragment", "onLoadMore")
                }
            })

        })
    }

}