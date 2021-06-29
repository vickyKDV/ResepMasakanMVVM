package com.vickykdv.resepmasakanmvvm.data.api

import com.vickykdv.resepmasakanmvvm.data.model.ResponseRecipes
import io.reactivex.Single

interface ApiService {
    fun getRecipes(pages:Int):Single<List<ResponseRecipes>>
}