package com.dd.githubapp.ui.ui.star

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dd.githubapp.R

class StarFragment : Fragment() {

    private lateinit var starViewModel: StarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        starViewModel =
            ViewModelProviders.of(this).get(StarViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_star, container, false)
        val textView: TextView = root.findViewById(R.id.text_star)
        starViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}