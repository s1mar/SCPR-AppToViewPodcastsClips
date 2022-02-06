package com.s1.scpr.acts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import com.s1.scpr.R
import com.s1.scpr.adapters.ProgramsAdapter
import com.s1.scpr.databinding.HomeActBinding
import com.s1.scpr.interfaces.Function
import com.s1.scpr.models.Program
import com.s1.scpr.models.Programs
import com.s1.scpr.networking.interfaces.ScprEndpointInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeAct : Activity() {
    private var scprEndpointService: ScprEndpointInterface? = null
    private var mBinder: HomeActBinding? = null
    private var mAdapter: ProgramsAdapter? = null
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinder = DataBindingUtil.setContentView(this, R.layout.home_act)
        init_actionBar()
        init_Retrofit()
        fetchPrograms()
    }

    private fun init_actionBar() {
        mBinder!!.actionBar.btnBack.visibility = View.GONE
        mBinder!!.actionBar.labelTitle.setText(R.string.label_programs)
    }

    private fun init_Retrofit() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ScprEndpointInterface.ROUTE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        scprEndpointService =
            retrofit.create(ScprEndpointInterface::class.java)
    }

    private fun fetchPrograms() {
        val programs: Call<Programs?>? = scprEndpointService?.listPrograms()
        programs?.enqueue(object : Callback<Programs?> {
            override fun onResponse(
                @NonNull call: Call<Programs?>,
                @NonNull response: Response<Programs?>
            ) {
                if (response.isSuccessful && response.body() != null) populateAdapter(response.body()!!.programs) else dataFound(
                    false
                )
            }

            override fun onFailure(@NonNull call: Call<Programs?>, @NonNull t: Throwable) {
                Log.e(TAG, "Call Failed")
                dataFound(false)
            }
        })
    }

    private fun populateAdapter(programs: List<Program>?) {
        if (programs == null || programs.isEmpty()) {
            dataFound(false)
        } else {
            if (mAdapter == null) {
                mAdapter = ProgramsAdapter(object : Function<String> {
                    override fun call(ob: String) {
                        //Use this program id to get the clips
                        Log.v(TAG, String.format("ProgramID is %s", ob))
                        val launchIntent =  Intent(this@HomeAct, ClipsAct::class.java)
                        launchIntent.putExtra(
                            getString(R.string.intent_extra_program_id),
                            ob
                        )
                        startActivity(launchIntent)
                    }
                })
                mBinder!!.recyclerView.setAdapter(mAdapter)
                mBinder!!.recyclerView.setHasFixedSize(true)
            }
            mAdapter!!.updateDataSet(programs, true)
            dataFound(true)
        }
    }

    private fun dataFound(flag: Boolean) {
        mBinder!!.frameLayoutNoData.visibility = if (flag) View.GONE else View.VISIBLE
    }

    companion object {
        private val TAG = HomeAct::class.java.simpleName
    }
}