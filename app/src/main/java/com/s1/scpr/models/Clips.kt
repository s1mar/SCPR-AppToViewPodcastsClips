package com.s1.scpr.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.s1.scpr.models.Monetization
import com.s1.scpr.models.AdMarker
import com.s1.scpr.models.AudioOptions
import com.s1.scpr.models.DirectoryLinks
import com.s1.scpr.models.DefaultPlaylist
import com.s1.scpr.models.Program

class Clips {
    @SerializedName("Clips")
    @Expose
    var clips: List<Clip>? = null

    @SerializedName("Cursor")
    @Expose
    var cursor: String? = null

    @SerializedName("TotalCount")
    @Expose
    var totalCount: Int? = null
}