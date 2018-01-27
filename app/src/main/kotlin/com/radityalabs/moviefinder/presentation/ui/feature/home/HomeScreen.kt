package com.radityalabs.moviefinder.presentation.ui.feature.home

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.radityalabs.moviefinder.R
import com.radityalabs.moviefinder.data.model.response.Discover
import com.radityalabs.moviefinder.domain.HomeUseCase
import com.radityalabs.moviefinder.external.animator
import com.radityalabs.moviefinder.external.navigator.Navigator
import com.radityalabs.moviefinder.presentation.di.module.HomeScreenModule
import com.radityalabs.moviefinder.presentation.ui.base.presenter.BasePresenter
import com.radityalabs.moviefinder.presentation.ui.base.screen.BaseScreen
import com.radityalabs.moviefinder.presentation.ui.base.view.BaseView
import com.radityalabs.moviefinder.presentation.ui.feature.SecondBundle
import com.radityalabs.moviefinder.presentation.ui.feature.SecondScreen
import com.radityalabs.universaladapter.UniversalAdapter
import kotlinx.android.synthetic.main.screen_home.view.*
import javax.inject.Inject

class HomeScreen(context: Context) : BaseScreen<HomeScreenPresenter.View, HomeScreenPresenter>(context),
        HomeScreenPresenter.View {

    companion object {
        private const val INITIAL_PAGE = 1
    }

    private var isLoading = false

    private var nextPage: Int = INITIAL_PAGE

    internal var navigator: Navigator? = null
        @Inject set

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: UniversalAdapter<Discover.Result, HomeViewHolder>

    init {
        LayoutInflater.from(context).inflate(R.layout.screen_home, this, true)
    }

    override fun setupInjection() {
        screenComponent.plus(HomeScreenModule()).inject(this)
    }

    override fun setupEvent() {
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
        presenter.fetchMovies(INITIAL_PAGE)
    }

    override fun onFetchMoviesSuccess(list: List<Discover.Result>) {
        isLoading = false
        adapter.addAll(list)
    }

    fun setClickListener(adapter: UniversalAdapter<Discover.Result, HomeViewHolder>,
                         holder: HomeViewHolder) = View.OnClickListener {
        val position = holder.adapterPosition
        val item = adapter.items[position]

        navigator?.goTo(SecondScreen(context, movieId = item.id))
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
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

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
    companion object {
        private val TAG = HomeScreenPresenter::class.java.simpleName
    }

    fun fetchMovies(page: Int) {
        addDisposable(usecase.fetchMovies(page).subscribe({ success ->
            view.onFetchMoviesSuccess(success)
        }, { error ->
            Log.e(TAG, error.message)
        }))
    }

    interface View : BaseView {
        fun onFetchMoviesSuccess(list: List<Discover.Result>)
    }
}