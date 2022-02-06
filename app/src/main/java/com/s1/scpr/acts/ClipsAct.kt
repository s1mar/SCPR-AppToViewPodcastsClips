package com.s1.scpr.acts

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.NonNull
import com.s1.scpr.R
import com.s1.scpr.adapters.ClipsAdapter
import com.s1.scpr.databinding.ClipsActBinding
import com.s1.scpr.interfaces.Function
import com.s1.scpr.models.Clip
import com.s1.scpr.modules.MediaController
import com.s1.scpr.networking.interfaces.ScprEndpointInterface
import com.s1.scpr.utils.Utils
import com.squareup.picasso.Picasso
import org.jetbrains.annotations.Nullable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.databinding.DataBindingUtil
import com.s1.scpr.models.Clips
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClipsAct : Activity() {
    private var scprEndpointService: ScprEndpointInterface? = null
    private var mBinder: ClipsActBinding? = null
    private var mAdapter: ClipsAdapter? = null
    private var programID: String? = null
    private var mediaController: MediaController? = null
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        programID = getIntent().getStringExtra("programID")
        mBinder = DataBindingUtil.setContentView(this, R.layout.clips_act)
        mediaController = MediaController(object : Function<Boolean> {
            override fun call(ob: Boolean) {
                interfaceWithMediaController(ob)
            }
        })
        init_actionBar()
        init_mediaControls()
        init_Retrofit()
        fetchClips()
    }

    private fun init_actionBar() {
        mBinder!!.actionBar.labelTitle.setText(R.string.label_clips)
        mBinder!!.actionBar.btnBack.setOnClickListener { v: View? -> onBackPressed() }
    }

    private fun interfaceWithMediaController(preparingMusic: Boolean) {
        if (preparingMusic) {
            mBinder!!.nowPlaying.btnPlay.visibility = View.GONE
            mBinder!!.nowPlaying.tvNp.setText(R.string.label_preparing_music)
        } else {
            mBinder!!.nowPlaying.btnPlay.performClick()
            mBinder!!.nowPlaying.btnPlay.visibility = View.VISIBLE
        }
    }

    private fun init_mediaControls() {
        mBinder!!.nowPlaying.btnPlay.setOnClickListener { v: View? ->
            if (mediaController!!.isPlaying) {
                //Pause the controls
                mediaController!!.pause()
                mBinder!!.nowPlaying.btnPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                mBinder!!.nowPlaying.tvNp.setText(R.string.label_clip_paused)
            } else {
                mediaController!!.play()
                mBinder!!.nowPlaying.btnPlay.setImageResource(R.drawable.ic_baseline_pause_24)
                mBinder!!.nowPlaying.tvNp.setText(R.string.label_now_playing)
            }
        }
    }

    private fun init_Retrofit() {

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ScprEndpointInterface.ROUTE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        scprEndpointService =
            retrofit.create<ScprEndpointInterface>(ScprEndpointInterface::class.java)
    }

    private fun fetchClips() {
        val clips: Call<Clips?>? = scprEndpointService?.listClips(programID)
        clips?.enqueue(object : Callback<Clips?> {
            override fun onResponse(
                @NonNull call: Call<Clips?>,
                @NonNull response: Response<Clips?>
            ) {
                if (response.isSuccessful() && response.body() != null) populateAdapter(response.body()!!.clips) else dataFound(
                    false
                )
            }

            override fun onFailure(@NonNull call: Call<Clips?>, @NonNull t: Throwable) {
                Log.e(TAG, "Call Failed")
                dataFound(false)
            }
        })
    }

    private fun populateAdapter(clips: List<Clip>?) {
        if (clips == null || clips.isEmpty()) {
            dataFound(false)
        } else {
            if (mAdapter == null) {
                mAdapter = ClipsAdapter(object : Function<Clip> {
                    override fun call(ob: Clip) {
                        updateMediaPlayerUI(ob)
                    }
                })
                mBinder!!.recyclerView.setAdapter(mAdapter)
                mBinder!!.recyclerView.setHasFixedSize(true)
            }
            mAdapter!!.updateDataSet(clips, true)
            dataFound(true)
        }
    }

    private fun updateMediaPlayerUI(clip: Clip) {
        val title = if (clip.getTitle().isEmpty()) "Title not provided" else clip.getTitle()
        val contentRating = String.format(
            "Content-Rating: %s",
            if (clip.getContentRating().isEmpty()) "Unrated" else clip.getContentRating()
        )
        val duration = String.format(
            "Duration: %s", Utils.secondsToHourMinsAndSecondsStr(
                clip.durationSeconds!!
            )
        )
        val desc = clip.description
        if (desc == null || desc.trim { it <= ' ' }.isEmpty()) {
            mBinder!!.nowPlaying.tv4.visibility = View.GONE
        } else {
            mBinder!!.nowPlaying.tv4.text = desc
            mBinder!!.nowPlaying.tv4.isSelected = true
        }
        mBinder!!.nowPlaying.tv1.text = title
        mBinder!!.nowPlaying.tv2.text = contentRating
        mBinder!!.nowPlaying.tv3.text = duration
        Picasso.get().load(clip.imageUrl).resize(70, 70).placeholder(R.drawable.loading)
            .error(R.drawable.error_loading_image)
            .into(mBinder!!.nowPlaying.imgView)
        mBinder!!.nowPlaying.getRoot().setVisibility(View.VISIBLE)
        mediaController!!.start(clip.audioUrl)
    }

    private fun dataFound(flag: Boolean) {
        mBinder!!.frameLayoutNoData.visibility =
            if (flag) View.GONE else View.VISIBLE
    }

    protected override fun onStop() {
        mediaController!!.onStop()
        super.onStop()
    }

    companion object {
        private val TAG = ClipsAct::class.java.simpleName
    }
}