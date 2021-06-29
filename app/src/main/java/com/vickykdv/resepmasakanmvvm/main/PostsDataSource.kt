package com.vickykdv.resepmasakanmvvm.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.vickykdv.resepmasakanmvvm.data.model.ResponseRecipes
import com.vickykdv.resepmasakanmvvm.data.repository.MainRepository
import com.vickykdv.resepmasakanmvvm.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class PostsDataSource(
    private val compositeDisposable: CompositeDisposable,
    private val mainRepository: MainRepository
): PageKeyedDataSource<Int, ResponseRecipes>()  {

    private val users = MutableLiveData<Resource<List<ResponseRecipes>>>()



    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ResponseRecipes>
    ) {
        compositeDisposable.add(
            mainRepository.getRecipes(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userList ->
                    users.postValue(Resource.success(userList))
                }, { throwable ->
                    users.postValue(Resource.error("Something Went Wrong", null))
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ResponseRecipes>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ResponseRecipes>) {
        compositeDisposable.add(
            mainRepository.getRecipes(params.key)
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