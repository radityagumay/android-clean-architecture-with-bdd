package com.radityalabs.moviefinder.external.navigator

import android.annotation.SuppressLint
import android.view.View
import com.radityalabs.moviefinder.presentation.ui.base.screen.Screen
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.*

@SuppressLint("StaticFieldLeak")
open class Navigator {
    companion object {
        private var sInstance: Navigator? = null
        private var LOCK = Any()
        fun getInstance(): Navigator? {
            synchronized(LOCK) {
                if (sInstance == null) {
                    sInstance = Navigator()
                }
            }
            return sInstance
        }
    }

    private val backStack = ArrayDeque<Screen>()
    private var subject: PublishSubject<Screen> = PublishSubject.create()

    var root: Screen? = null
        private set
    var container: View? = null
        private set
    var stack = ArrayDeque<Screen>()
        private set

    fun toObservable(): Observable<Screen> {
        return subject
    }

    fun goTo(screen: Screen) {
        stack.add(screen)
        subject.onNext(screen)
    }

    fun goBack(): Screen {
        val view = stack.remove()
        subject.onNext(view)
        return view
    }

    fun setRootNavigator(root: Screen) {
        this.root = root
        backStack.add(root)
    }

    fun setContainerNavigator(view: View) {
        this.container = view
    }
}