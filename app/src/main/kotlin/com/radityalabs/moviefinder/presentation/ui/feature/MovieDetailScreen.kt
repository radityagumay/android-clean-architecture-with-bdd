package com.radityalabs.moviefinder.presentation.ui.feature

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.graphics.Palette
import android.util.AttributeSet
import android.view.LayoutInflater
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener
import com.radityalabs.moviefinder.R
import com.radityalabs.moviefinder.data.model.entity.MovieDetailViewEntity
import com.radityalabs.moviefinder.data.model.response.MovieDetail
import com.radityalabs.moviefinder.domain.SecondUseCase
import com.radityalabs.moviefinder.external.loadAsBitmap
import com.radityalabs.moviefinder.external.navigator.MovieData
import com.radityalabs.moviefinder.external.navigator.Navigator
import com.radityalabs.moviefinder.external.snackBar
import com.radityalabs.moviefinder.presentation.di.module.SecondScreenModule
import com.radityalabs.moviefinder.presentation.ui.base.presenter.BasePresenter
import com.radityalabs.moviefinder.presentation.ui.base.screen.BaseScreen
import com.radityalabs.moviefinder.presentation.ui.base.view.BaseView
import kotlinx.android.synthetic.main.second_home.view.*
import javax.inject.Inject


@SuppressLint("ViewConstructor")
class MovieDetailScreen @JvmOverloads constructor(context: Context,
                                                  attributeSet: AttributeSet? = null,
                                                  defStyle: Int = 0) :
        BaseScreen<MovieDetailPresenter.View, MovieDetailPresenter>(context, attributeSet, defStyle),
        MovieDetailPresenter.View {
    companion object {
        val TAG = MovieDetailScreen::class.java.simpleName
    }

    lateinit var movieDetailData: MovieDetailData

    internal var navigator: Navigator? = null
        @Inject set

    init {
        LayoutInflater.from(context).inflate(R.layout.second_home, this, true)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        video.release()
    }

    override fun setupInjection() {
        screenComponent.plus(SecondScreenModule()).inject(this)
    }

    override fun setupEvent() {
        movieDetailData = navigator?.parcelData?.get(TAG) as MovieDetailData
    }

    override fun setupView() {}

    override fun setupData() {
        movieDetailData.movieId?.let {
            presenter.getMoviewById(it)
        }
    }

    override fun onGetMovieSuccess(response: MovieDetailViewEntity) {
        with(response) {
            posterImage?.loadAsBitmap(context, image, { palette ->
                applyPalette(palette)
            })

            youtubView(youtubeKey)

            collapsingToolbar.title = title
            collapsingToolbar.setExpandedTitleColor(resources.getColor(android.R.color.transparent))

            this@MovieDetailScreen.title.text = title
            description.text = overview

            this@MovieDetailScreen.genre.text = genre
        }
    }

    override fun onGetMoviewError(message: String) {
        snackBar(message)
    }

    override fun errorMessage() = resources.getString(R.string.not_found)

    override fun getClassName() = TAG

    private fun youtubView(videoId: String?) {
        videoId?.let {
            video.initialize({ initializedYouTubePlayer ->
                initializedYouTubePlayer.addListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady() {
                        initializedYouTubePlayer.loadVideo(videoId, 0f)
                    }
                })
            }, true)
        }
    }

    private fun applyPalette(palette: Palette) {
        val primaryDark = resources.getColor(R.color.colorPrimaryDark)
        val primary = resources.getColor(R.color.colorPrimary)
        collapsingToolbar.setContentScrimColor(palette.getMutedColor(primary))
        collapsingToolbar.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark))
    }
}

class MovieDetailPresenter @Inject constructor(private val usecase: SecondUseCase) : BasePresenter<MovieDetailPresenter.View>() {
    fun getMoviewById(movieId: Int) {
        addDisposable(usecase.getMovieById(movieId).subscribe({ success ->
            view.onGetMovieSuccess(success)
        }, { error ->
            view.onGetMoviewError(error.message ?: view.errorMessage())
        }))
    }

    interface View : BaseView {
        fun onGetMovieSuccess(response: MovieDetailViewEntity)

        fun onGetMoviewError(message: String)

        fun errorMessage(): String
    }
}

data class MovieDetailData(val movieId: Int? = null) : MovieData