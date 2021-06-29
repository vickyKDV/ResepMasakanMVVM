package com.vickykdv.resepmasakanmvvm.data.api

import com.rx2androidnetworking.Rx2AndroidNetworking
import com.vickykdv.resepmasakanmvvm.data.model.ResponseRecipes
import io.reactivex.Single

class ApiServiceImpl :ApiService {


    override fun getRecipes(pages: Int): Single<List<ResponseRecipes>> {
        return Rx2AndroidNetworking.get("https://masak-apa.tomorisakura.vercel.app/api/recipes/$pages")
            .build()
            .getObjectListSingle(ResponseRecipes::class.java)
    }
}