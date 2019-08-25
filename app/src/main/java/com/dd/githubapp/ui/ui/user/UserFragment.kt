package com.dd.githubapp.ui.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.dd.githubapp.R
import com.dd.githubapp.model.AccountManager
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userViewModel =
            ViewModelProviders.of(this).get(UserViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_user, container, false)
        userViewModel.text.observe(this, Observer {

        })

        AccountManager.currentUser?.let {

//            context?.let { ctx -> Glide.with(ctx).load(it.avatarUrl).into(iv_avatar) }
//            tv_user.text = it.login
//            tv_nickname.text = it.name ?: ""


        }


        return root
    }
}