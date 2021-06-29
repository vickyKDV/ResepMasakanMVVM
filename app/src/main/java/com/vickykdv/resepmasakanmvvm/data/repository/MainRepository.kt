package com.vickykdv.resepmasakanmvvm.data.repository

import com.vickykdv.resepmasakanmvvm.data.api.ApiHelper
import com.vickykdv.resepmasakanmvvm.data.model.ResponseRecipes
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {

    fun getRecipes(pages:Int): Single<List<ResponseRecipes>> {
        return apiHelper.getRecipes(pages)
    }

}