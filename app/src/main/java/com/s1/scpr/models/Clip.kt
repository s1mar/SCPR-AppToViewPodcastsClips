package com.s1.scpr.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.s1.scpr.models.Monetization
import com.s1.scpr.models.AdMarker
import com.s1.scpr.models.AudioOptions
import com.s1.scpr.models.DirectoryLinks
import com.s1.scpr.models.DefaultPlaylist
import com.s1.scpr.models.Program

class Clip {
    @SerializedName("Id")
    @Expose
    var id: String? = null

    @SerializedName("Title")
    @Expose
    private var title: String? = null

    @SerializedName("Slug")
    @Expose
    var slug: String? = null

    @SerializedName("Description")
    @Expose
    var description: String? = null

    @SerializedName("DescriptionHtml")
    @Expose
    var descriptionHtml: String? = null

    @SerializedName("Tags")
    @Expose
    var tags: List<Any>? = null

    @SerializedName("TranscriptUrl")
    @Expose
    var transcriptUrl: Any? = null

    @SerializedName("Season")
    @Expose
    var season: Any? = null

    @SerializedName("Episode")
    @Expose
    var episode: Any? = null

    @SerializedName("EpisodeType")
    @Expose
    var episodeType: String? = null

    @SerializedName("ImageUrl")
    @Expose
    var imageUrl: String? = null

    @SerializedName("AudioUrl")
    @Expose
    var audioUrl: String? = null

    @SerializedName("WaveformUrl")
    @Expose
    var waveformUrl: String? = null

    @SerializedName("VideoUrl")
    @Expose
    var videoUrl: Any? = null

    @SerializedName("EmbedUrl")
    @Expose
    var embedUrl: String? = null

    @SerializedName("DurationSeconds")
    @Expose
    var durationSeconds: Double? = null

    @SerializedName("PublishState")
    @Expose
    var publishState: String? = null

    @SerializedName("PublishedUrl")
    @Expose
    var publishedUrl: String? = null

    @SerializedName("Visibility")
    @Expose
    var visibility: String? = null

    @SerializedName("PublishedUtc")
    @Expose
    var publishedUtc: String? = null

    @SerializedName("PlaylistIds")
    @Expose
    var playlistIds: List<String>? = null

    @SerializedName("Chapters")
    @Expose
    var chapters: List<Any>? = null

    @SerializedName("State")
    @Expose
    var state: String? = null

    @SerializedName("ShareUrl")
    @Expose
    var shareUrl: Any? = null

    @SerializedName("RssLinkOverride")
    @Expose
    var rssLinkOverride: Any? = null

    @SerializedName("ImportedId")
    @Expose
    var importedId: Any? = null

    @SerializedName("Monetization")
    @Expose
    var monetization: Monetization? = null

    @SerializedName("AdMarkers")
    @Expose
    var adMarkers: List<AdMarker>? = null

    @SerializedName("HasPreRollVideoAd")
    @Expose
    var hasPreRollVideoAd: Boolean? = null

    @SerializedName("RecordingMetadata")
    @Expose
    var recordingMetadata: Any? = null

    @SerializedName("PublishedAudioSizeInBytes")
    @Expose
    var publishedAudioSizeInBytes: Int? = null

    @SerializedName("ContentRating")
    @Expose
    private var contentRating: String? = null

    @SerializedName("AudioOptions")
    @Expose
    var audioOptions: AudioOptions? = null

    @SerializedName("ExternalId")
    @Expose
    var externalId: Any? = null

    @SerializedName("CustomFieldData")
    @Expose
    var customFieldData: Any? = null

    @SerializedName("ProgramId")
    @Expose
    var programId: String? = null

    @SerializedName("OrganizationId")
    @Expose
    var organizationId: String? = null
    fun getTitle(): String {
        title = if (title == null) "" else title!!.trim { it <= ' ' }
        return title!!
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getContentRating(): String {
        contentRating = if (contentRating == null) "" else contentRating!!.trim { it <= ' ' }
        return contentRating!!
    }

    fun setContentRating(contentRating: String?) {
        this.contentRating = contentRating
    }
}