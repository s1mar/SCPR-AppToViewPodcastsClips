package com.s1.scpr.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.s1.scpr.models.Monetization
import com.s1.scpr.models.AdMarker
import com.s1.scpr.models.AudioOptions
import com.s1.scpr.models.DirectoryLinks
import com.s1.scpr.models.DefaultPlaylist
import com.s1.scpr.models.Program

class DefaultPlaylist {
    @SerializedName("Id")
    @Expose
    var id: String? = null

    @SerializedName("Title")
    @Expose
    var title: String? = null

    @SerializedName("Slug")
    @Expose
    var slug: String? = null

    @SerializedName("Description")
    @Expose
    var description: String? = null

    @SerializedName("DescriptionHtml")
    @Expose
    var descriptionHtml: String? = null

    @SerializedName("RssFeedUrl")
    @Expose
    var rssFeedUrl: String? = null

    @SerializedName("EmbedUrl")
    @Expose
    var embedUrl: String? = null

    @SerializedName("ArtworkUrl")
    @Expose
    var artworkUrl: String? = null

    @SerializedName("NumberOfClips")
    @Expose
    var numberOfClips: Int? = null

    @SerializedName("Visibility")
    @Expose
    var visibility: String? = null

    @SerializedName("Categories")
    @Expose
    var categories: List<String>? = null

    @SerializedName("DirectoryLinks")
    @Expose
    var directoryLinks: DirectoryLinks? = null

    @SerializedName("CustomFieldData")
    @Expose
    var customFieldData: Any? = null

    @SerializedName("ProgramId")
    @Expose
    var programId: String? = null

    @SerializedName("OrganizationId")
    @Expose
    var organizationId: String? = null
}