package com.androidapp.containerprogect.fragmentRetr

import android.util.Log
import androidx.lifecycle.ViewModel
import com.androidapp.containerprogect.data.remote.quest.QuestApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ViewModelFragmentRetrofit : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun fetchQuestList(questApi: QuestApi) {
        compositeDisposable.add(
            questApi.getQuestList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.i("TAG", "Id: ${it.items[0].questId}, name: ${it.items[0].name}")
                }, {

                })
        )
    }

    fun fetchQuest(questApi: QuestApi) {
        compositeDisposable.add(
            questApi.getQuest("1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.i("TAG", it)
                }, {

                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}