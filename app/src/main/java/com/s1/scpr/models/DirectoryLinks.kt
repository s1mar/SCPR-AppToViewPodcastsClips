package com.s1.scpr.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.s1.scpr.models.Monetization
import com.s1.scpr.models.AdMarker
import com.s1.scpr.models.AudioOptions
import com.s1.scpr.models.DirectoryLinks
import com.s1.scpr.models.DefaultPlaylist
import com.s1.scpr.models.Program

class DirectoryLinks {
    @SerializedName("ApplePodcasts")
    @Expose
    var applePodcasts: Any? = null

    @SerializedName("GooglePodcasts")
    @Expose
    var googlePodcasts: Any? = null

    @SerializedName("Spotify")
    @Expose
    var spotify: String? = null

    @SerializedName("Stitcher")
    @Expose
    var stitcher: Any? = null

    @SerializedName("TuneIn")
    @Expose
    var tuneIn: Any? = null

    @SerializedName("GooglePlay")
    @Expose
    var googlePlay: Any? = null

    @SerializedName("RssFeed")
    @Expose
    var rssFeed: String? = null

    @SerializedName("IHeart")
    @Expose
    var iHeart: Any? = null

    @SerializedName("AmazonMusic")
    @Expose
    var amazonMusic: Any? = null
}