package com.dd.githubapp.mvp.impl

import androidx.fragment.app.Fragment
import com.dd.githubapp.mvp.IMvpView
import com.dd.githubapp.mvp.IPresenter

/**
 * GithubApp
 *
 * @author    daidong
 *
 */
abstract class BaseFragment<out P : BasePresenter<BaseFragment<P>>> : IMvpView<P>, Fragment() {
    override val presenter: P

    init {
        presenter = createPresenter()
        presenter.view = this
    }

    fun createPresenter(): P = TODO()
}