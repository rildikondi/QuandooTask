package com.akondi.quandootask.features.merchants_feature

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akondi.quandootask.core.platform.BaseActivity
import com.akondi.quandootask.core.platform.BaseFragment

class MerchantsActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, MerchantsActivity::class.java)
    }

    override fun fragment(): BaseFragment = MerchantsFragment()
}
