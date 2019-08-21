package com.dd.githubapp.mvp

/**
 * GithubApp
 *
 * @author    daidong
 *
 */

interface IPresenter<out View : IMvpView<IPresenter<View>>> : ILifeCycle {
    val view: View
}

interface IMvpView<out Presenter : IPresenter<IMvpView<Presenter>>> : ILifeCycle {
    val presenter: Presenter
}