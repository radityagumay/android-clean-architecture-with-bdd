package com.radityalabs.moviefinder.presentation.ui.feature.home

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import com.radityalabs.moviefinder.R
import com.radityalabs.moviefinder.data.model.response.Discover
import com.radityalabs.moviefinder.domain.HomeUseCase
import com.radityalabs.moviefinder.external.animator
import com.radityalabs.moviefinder.external.navigator.MovieData
import com.radityalabs.moviefinder.external.navigator.Navigator
import com.radityalabs.moviefinder.external.snackBar
import com.radityalabs.moviefinder.presentation.di.module.HomeScreenModule
import com.radityalabs.moviefinder.presentation.ui.base.presenter.BasePresenter
import com.radityalabs.moviefinder.presentation.ui.base.screen.BaseScreen
import com.radityalabs.moviefinder.presentation.ui.base.view.BaseView
import com.radityalabs.moviefinder.presentation.ui.feature.MovieDetailData
import com.radityalabs.moviefinder.presentation.ui.feature.MovieDetailScreen
import com.radityalabs.universaladapter.UniversalAdapter
import io.reactivex.Single
import kotlinx.android.synthetic.main.screen_home.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class HomeScreen(context: Context) : BaseScreen<HomeScreenPresenter.View, HomeScreenPresenter>(context),
        HomeScreenPresenter.View {

    companion object {
        val TAG = HomeScreen::class.java.simpleName

        private const val INITIAL_PAGE = 1
    }

    internal var navigator: Navigator? = null
        @Inject set

    private var isDataFromCacheAvailable = false
    private var isLoading = false

    private var nextPage: Int = INITIAL_PAGE
    private var firstVisibleItemPosition by Delegates.notNull<Int>()

    private var homeParcelData: HomeScreenParcel? = null
    private var movies = mutableListOf<Discover.Result>()

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: UniversalAdapter<Discover.Result, HomeViewHolder>

    init {
        LayoutInflater.from(context).inflate(R.layout.screen_home, this, true)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        navigator?.setParcelData(TAG, HomeScreenParcel(movies, nextPage, firstVisibleItemPosition))
    }

    override fun setupInjection() {
        screenComponent.plus(HomeScreenModule()).inject(this)
    }

    override fun setupEvent() {
        initCache()
        adapter = UniversalAdapter({ parent, _ ->
            HomeViewHolder.inflate(parent).also {
                it.container.setOnClickListener(setClickListener(adapter, it))
            }
        }, { vh, _, item ->
            vh.bind(item)
        })
    }

    override fun setupView() {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        service.apply {
            adapter = this@HomeScreen.adapter
            addOnScrollListener(recyclerViewOnScrollListener)
            layoutManager = this@HomeScreen.layoutManager
        }.animator()
    }

    override fun setupData() {
        if (isDataFromCacheAvailable) {
            homeParcelData?.movies?.let {
                movies.addAll(it)
                adapter.addAll(it)

                service.scrollToPosition(firstVisibleItemPosition)
            }
        } else {
            presenter.fetchMovies(INITIAL_PAGE)
        }
    }

    override fun errorMessage() = resources.getString(R.string.not_found)

    override fun onFetchMoviesSuccess(list: List<Discover.Result>) {
        isLoading = false
        movies.addAll(list)
        adapter.addAll(list)
    }

    override fun onFetchMoviesError(message: String) {
        isLoading = false
        snackBar(message)
    }

    override fun getClassName() = TAG

    fun setClickListener(adapter: UniversalAdapter<Discover.Result, HomeViewHolder>,
                         holder: HomeViewHolder) = View.OnClickListener {
        val position = holder.adapterPosition
        val item = adapter.items[position]

        navigator?.goTo(MovieDetailScreen(context), MovieDetailData(item.id))
    }

    private fun initCache() {
        try {
            homeParcelData = navigator?.parcelData?.get(TAG) as HomeScreenParcel
            homeParcelData?.let {
                isDataFromCacheAvailable = true
                nextPage = it.lastPage + 1
                firstVisibleItemPosition = it.firstVisibleItemPosition
            }
        } catch (ex: Exception) {
        }
    }

    private fun loadMoreItems() {
        isLoading = true
        presenter.fetchMovies(nextPage)
    }

    private val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            if (!isLoading) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
                    nextPage += 1
                    loadMoreItems()
                }
            }
        }
    }
}

class HomeScreenPresenter @Inject constructor(private val usecase: HomeUseCase) : BasePresenter<HomeScreenPresenter.View>() {
    fun fetchMovies(page: Int) {
        addDisposable(usecase.fetchMovies(page)
                .onErrorResumeNext(Single.error(Throwable(view.errorMessage())))
                .subscribe({ success ->
                    view.onFetchMoviesSuccess(success)
                }, { error ->
                    view.onFetchMoviesError(error.message ?: view.errorMessage())
                }))
    }

    interface View : BaseView {
        fun onFetchMoviesSuccess(list: List<Discover.Result>)

        fun onFetchMoviesError(message: String)

        fun errorMessage(): String
    }
}

data class HomeScreenParcel(val movies: List<Discover.Result>,
                            val lastPage: Int,
                            val firstVisibleItemPosition: Int) : MovieData