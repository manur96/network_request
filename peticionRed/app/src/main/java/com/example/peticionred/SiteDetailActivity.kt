package com.example.peticionred

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_site_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SiteDetailActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_detail)

        getSiteById()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://t21services.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getSiteById() {
        var siteDetail: SiteDetail? = null
        val service = getRetrofit().create(SiteService::class.java)
        val id = intent.getStringExtra("id")
        if (id != null) {
            service.getPointById(id).enqueue(object : Callback<SiteDetail> {
                override fun onResponse(call: Call<SiteDetail>?, response: Response<SiteDetail>?) {
                    siteDetail = response?.body()
                    titleDetail.text = siteDetail?.title
                    addres.text = siteDetail?.address
                    transport.text = siteDetail?.transport
                    email.text = siteDetail?.email
                    geocoordinatesDetail.text = siteDetail?.geocoordinates
                    description.text = siteDetail?.description
                    phone.text = siteDetail?.phone
                }

                override fun onFailure(call: Call<SiteDetail>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }

}