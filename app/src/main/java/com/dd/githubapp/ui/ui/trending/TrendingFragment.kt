package com.dd.githubapp.ui.ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dd.githubapp.R

class TrendingFragment : Fragment() {

    private lateinit var trendingViewModel: TrendingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        trendingViewModel =
            ViewModelProviders.of(this).get(TrendingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_trending, container, false)
        val textView: TextView = root.findViewById(R.id.text_trending)
        trendingViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}