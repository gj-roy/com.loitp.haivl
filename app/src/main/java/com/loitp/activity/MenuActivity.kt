package com.loitp.activity

import android.os.Bundle
import com.annotation.IsFullScreen
import com.annotation.LogTag
import com.core.base.BaseFontActivity
import com.loitp.R

@LogTag("MenuActivity")
@IsFullScreen(false)
class MenuActivity : BaseFontActivity() {

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_menu
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
    }
    
}
