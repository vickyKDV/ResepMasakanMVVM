package com.vickykdv.resepmasakanmvvm.data.api

import com.vickykdv.resepmasakanmvvm.data.model.ResponseRecipes
import io.reactivex.Single

class ApiHelper(private val apiService: ApiService) {

    fun getRecipes(pages:Int) = apiService.getRecipes(pages)
}