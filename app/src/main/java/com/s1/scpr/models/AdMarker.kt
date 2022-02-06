package com.s1.scpr.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.s1.scpr.models.Monetization
import com.s1.scpr.models.AdMarker
import com.s1.scpr.models.AudioOptions
import com.s1.scpr.models.DirectoryLinks
import com.s1.scpr.models.DefaultPlaylist
import com.s1.scpr.models.Program

class AdMarker {
    @SerializedName("Offset")
    @Expose
    var offset: String? = null

    @SerializedName("MaxNumberOfAds")
    @Expose
    var maxNumberOfAds: Int? = null

    @SerializedName("AdMarkerType")
    @Expose
    var adMarkerType: String? = null
}