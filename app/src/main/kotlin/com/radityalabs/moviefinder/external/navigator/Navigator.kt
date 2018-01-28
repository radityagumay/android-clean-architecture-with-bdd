package com.radityalabs.moviefinder.external.navigator

import android.annotation.SuppressLint
import android.os.Parcelable
import android.view.View
import com.radityalabs.moviefinder.presentation.ui.base.screen.Screen
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.io.Serializable
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.HashMap

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


    private var subject: PublishSubject<Screen> = PublishSubject.create()

    var root: Screen? = null
        private set
    var container: View? = null
        private set
    var stack = Stack<Screen>()
        private set
    var parcelData: ConcurrentHashMap<String, MovieData> = ConcurrentHashMap()
        private set

    fun toObservable(): Observable<Screen> {
        return subject
    }

    fun goTo(screen: Screen, parcel: MovieData) {
        stack.add(screen)
        parcelData.put(screen.getClassName(), parcel)
        subject.onNext(screen)
    }

    fun goBack() {
        val pop = stack.pop()
        parcelData.remove(pop.getClassName())
        subject.onNext(stack.peek())
    }

    fun setRootNavigator(root: Screen) {
        stack.add(root)
    }

    fun setContainerNavigator(view: View) {
        this.container = view
    }
}

interface MovieData