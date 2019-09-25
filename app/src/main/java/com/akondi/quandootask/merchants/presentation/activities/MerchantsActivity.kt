package com.akondi.quandootask.merchants.presentation.activities

import android.content.Context
import android.content.Intent
import com.akondi.quandootask.core.platform.BaseActivity
import com.akondi.quandootask.core.platform.BaseFragment
import com.akondi.quandootask.merchants.presentation.fragments.MerchantsFragment

class MerchantsActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, MerchantsActivity::class.java)
    }

    override fun fragment(): BaseFragment =
        MerchantsFragment()
}
