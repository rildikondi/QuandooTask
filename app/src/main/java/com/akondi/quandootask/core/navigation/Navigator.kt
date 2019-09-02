package com.akondi.quandootask.core.navigation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.FragmentActivity
import com.akondi.cleancoremvvm.core.extension.empty
import com.akondi.quandootask.core.extensions.startActivity
import com.akondi.quandootask.features.login_feature.LoginActivity
import com.akondi.quandootask.features.login_feature.Authenticator
import com.akondi.quandootask.features.merchants_feature.MerchantDetailsActivity
import com.akondi.quandootask.features.merchants_feature.MerchantView
import com.akondi.quandootask.features.merchants_feature.MerchantsActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator
@Inject constructor(private val authenticator: Authenticator) {

    private fun showLogin(context: Context) = context.startActivity<LoginActivity>()

    fun showMain(context: Context) {
        when (authenticator.userLoggedIn()) {
            true -> showMerchants(context)
            false -> showLogin(context)
        }
    }

    private fun showMerchants(context: Context) = context.startActivity(MerchantsActivity.callingIntent(context))

    fun showMerchantDetails(activity: FragmentActivity, merchant: MerchantView, navigationExtras: Extras) {
        val intent = MerchantDetailsActivity.callingIntent(activity, merchant)
        val sharedView = navigationExtras.transitionSharedElement as ImageView
        val activityOptions = ActivityOptionsCompat
            .makeSceneTransitionAnimation(activity, sharedView, sharedView.transitionName)
        activity.startActivity(intent, activityOptions.toBundle())
    }

    private val VIDEO_URL_HTTP = "http://www.youtube.com/watch?v="
    private val VIDEO_URL_HTTPS = "https://www.youtube.com/watch?v="

    fun openVideo(context: Context, videoUrl: String) {
        try {
            context.startActivity(createYoutubeIntent(videoUrl))
        } catch (ex: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl)))
        }
    }

    private fun createYoutubeIntent(videoUrl: String): Intent {
        val videoId = when {
            videoUrl.startsWith(VIDEO_URL_HTTP) -> videoUrl.replace(VIDEO_URL_HTTP, String.empty())
            videoUrl.startsWith(VIDEO_URL_HTTPS) -> videoUrl.replace(VIDEO_URL_HTTPS, String.empty())
            else -> videoUrl
        }

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
        intent.putExtra("force_fullscreen", true)

        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.M)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        return intent
    }

    class Extras(val transitionSharedElement: View)
}