package com.vickykdv.resepmasakanmvvm.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.vickykdv.resepmasakanmvvm.data.model.ResponseRecipes
import com.vickykdv.resepmasakanmvvm.data.model.ResultsItem
import com.vickykdv.resepmasakanmvvm.data.repository.MainRepository
import com.vickykdv.resepmasakanmvvm.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel (private val mainRepository: MainRepository): ViewModel() {
    private val users = MutableLiveData<Resource<List<ResponseRecipes>>>()
    private val compositeDisposable = CompositeDisposable()

//    val state : MutableLiveData<RecipesState> by lazy {
//        MutableLiveData<RecipesState>()
//    }


    val data : MutableLiveData<PagedList<ResultsItem>> by lazy {
        MutableLiveData<PagedList<ResultsItem>>()
    }

    init {
        fetchRecipes()
    }

    fun getRecipes() {
        mainRepository.getRecipes()
    }

    private fun fetchRecipes() {
        users.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userList ->
                    users.postValue(Resource.success(userList))
                }, { throwable ->
                    users.postValue(Resource.error("Something Went Wrong", null))
                })
        )
    }
}