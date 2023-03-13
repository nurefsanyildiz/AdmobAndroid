package com.example.admobandroiddemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {

    lateinit var mAdView : AdView
    private var mInterstitialAd: InterstitialAd? = null
    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Insert your own AdMob app ID to your app's AndroidManifest.xml file
        //Banner Test ID : ca-app-pub-3940256099942544/6300978111
        //Interstitial Test ID : ca-app-pub-3940256099942544/1033173712

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        var adRequest2 = AdRequest.Builder().build()
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest2, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d(TAG, adError?.toString())
                mInterstitialAd = null
            }
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
            }
        })
    }

    fun goToSecond(view: View){
        startActivity(Intent(this, MainActivity2::class.java))
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        }
    }

}