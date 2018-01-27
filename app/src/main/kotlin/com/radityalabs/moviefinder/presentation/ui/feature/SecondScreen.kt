package com.radityalabs.moviefinder.presentation.ui.feature

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import com.radityalabs.moviefinder.R
import com.radityalabs.moviefinder.domain.SecondUseCase
import com.radityalabs.moviefinder.external.navigator.Navigator
import com.radityalabs.moviefinder.presentation.di.module.SecondScreenModule
import com.radityalabs.moviefinder.presentation.ui.base.presenter.BasePresenter
import com.radityalabs.moviefinder.presentation.ui.base.screen.BaseScreen
import com.radityalabs.moviefinder.presentation.ui.base.view.BaseView
import javax.inject.Inject

@SuppressLint("ViewConstructor")
class SecondScreen @JvmOverloads constructor(context: Context,
                                             attributeSet: AttributeSet? = null,
                                             defStyle: Int = 0,
                                             movieId: Int? = null) :
        BaseScreen<SecondScreenPresenter.View, SecondScreenPresenter>(context, attributeSet, defStyle),
        SecondScreenPresenter.View {

    private val movieId = movieId

    internal var navigator: Navigator? = null
        @Inject set

    init {
        LayoutInflater.from(context).inflate(R.layout.second_home, this, true)
    }

    override fun setupInjection() {
        screenComponent.plus(SecondScreenModule()).inject(this)
    }

    override fun setupEvent() {
    }

    override fun setupView() {}

    override fun setupData() {
        movieId?.let {
            presenter.getMoviewById(movieId)
        }
    }
}

class SecondScreenPresenter @Inject constructor(private val usecase: SecondUseCase) : BasePresenter<SecondScreenPresenter.View>() {
    fun getMoviewById(movieId: Int) {
        addDisposable(usecase.getMovieById(movieId).subscribe({ success ->
            Log.d("RADITYAAAA", success.toString())
        }, { error ->
        }))
    }

    interface View : BaseView
}

data class SecondBundle(val movieId: Int? = null)