package com.akondi.quandootask.features.merchants_feature

import android.content.Context
import android.content.Intent
import com.akondi.quandootask.core.platform.BaseActivity

class MerchantDetailsActivity : BaseActivity() {

    companion object {
        private const val INTENT_EXTRA_PARAM_MERCHANT = "com.akondi.quandootask.INTENT_PARAM_MERCHANT"

        fun callingIntent(context: Context, merchant: MerchantView): Intent {
            val intent = Intent(context, MerchantDetailsActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_MERCHANT, merchant)
            return intent
        }
    }

    override fun fragment() = MerchantDetailsFragment.forMerchant(intent.getParcelableExtra(INTENT_EXTRA_PARAM_MERCHANT))
}