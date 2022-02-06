package com.s1.scpr.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.s1.scpr.models.Monetization
import com.s1.scpr.models.AdMarker
import com.s1.scpr.models.AudioOptions
import com.s1.scpr.models.DirectoryLinks
import com.s1.scpr.models.DefaultPlaylist
import com.s1.scpr.models.Program

class Program {
    @SerializedName("Id")
    @Expose
    var id: String? = null

    @SerializedName("Name")
    @Expose
    var name: String? = null

    @SerializedName("Slug")
    @Expose
    var slug: String? = null

    @SerializedName("Description")
    @Expose
    var description: String? = null

    @SerializedName("DescriptionHtml")
    @Expose
    var descriptionHtml: String? = null

    @SerializedName("Author")
    @Expose
    private var author: String? = null

    @SerializedName("Publisher")
    @Expose
    var publisher: Any? = null

    @SerializedName("Copyright")
    @Expose
    var copyright: Any? = null

    @SerializedName("Language")
    @Expose
    var language: String? = null

    @SerializedName("ArtworkUrl")
    @Expose
    var artworkUrl: String? = null

    @SerializedName("Category")
    @Expose
    var category: String? = null

    @SerializedName("Categories")
    @Expose
    var categories: List<String>? = null

    @SerializedName("SocialWeb")
    @Expose
    var socialWeb: String? = null

    @SerializedName("SocialTwitter")
    @Expose
    var socialTwitter: Any? = null

    @SerializedName("SocialFacebook")
    @Expose
    var socialFacebook: Any? = null

    @SerializedName("SocialShowSupportUrl")
    @Expose
    var socialShowSupportUrl: Any? = null

    @SerializedName("SocialShowSupportLabel")
    @Expose
    var socialShowSupportLabel: Any? = null

    @SerializedName("Hidden")
    @Expose
    var hidden: Boolean? = null

    @SerializedName("Archived")
    @Expose
    var archived: Boolean? = null

    @SerializedName("Network")
    @Expose
    var network: String? = null

    @SerializedName("NetworkId")
    @Expose
    var networkId: String? = null

    @SerializedName("ExternalId")
    @Expose
    var externalId: Any? = null

    @SerializedName("ContactName")
    @Expose
    var contactName: String? = null

    @SerializedName("ContactEmail")
    @Expose
    var contactEmail: String? = null

    @SerializedName("CustomFieldData")
    @Expose
    var customFieldData: Any? = null

    @SerializedName("DefaultPlaylistId")
    @Expose
    var defaultPlaylistId: String? = null

    @SerializedName("DefaultPlaylist")
    @Expose
    var defaultPlaylist: DefaultPlaylist? = null

    @SerializedName("AdConfig")
    @Expose
    var adConfig: Any? = null

    @SerializedName("OrganizationId")
    @Expose
    var organizationId: String? = null
    fun getAuthor(): String {
        author = if (author == null) "" else author!!.trim { it <= ' ' }
        return author!!
    }

    fun setAuthor(author: String?) {
        this.author = author
    }
}