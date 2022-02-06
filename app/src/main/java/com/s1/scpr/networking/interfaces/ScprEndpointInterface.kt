package com.s1.scpr.networking.interfaces

import retrofit2.http.GET
import com.s1.scpr.networking.interfaces.ScprEndpointInterface
import com.s1.scpr.models.Clips
import com.s1.scpr.models.Programs
import retrofit2.Call
import retrofit2.http.Path

interface ScprEndpointInterface {
    @GET("orgs/$ORG_ID/programs/")
    fun listPrograms(): Call<Programs?>?

    @GET("orgs/$ORG_ID/programs/{programID}/clips?pageSize=50")
    fun listClips(@Path("programID") programID: String?): Call<Clips?>?

    companion object {
        const val ROUTE = "https://omny.fm/api/"
        const val ORG_ID = "acc8cc57-ff7c-44c5-9bd6-ab0900fbdc43"
    }
}