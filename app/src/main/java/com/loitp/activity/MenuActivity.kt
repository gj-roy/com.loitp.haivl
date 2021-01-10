package com.loitp.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.annotation.IsFullScreen
import com.annotation.LogTag
import com.core.base.BaseFontActivity
import com.core.common.Constants
import com.core.utilities.LStoreUtil
import com.core.utilities.LUIUtil
import com.loitp.R
import com.views.viewpager.viewpagertransformers.ZoomOutSlideTransformer
import kotlinx.android.synthetic.main.activity_menu.*
import java.util.*

@LogTag("MenuActivity")
@IsFullScreen(false)
class MenuActivity : BaseFontActivity() {
    private val resList: MutableList<Int> = ArrayList()

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_menu
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        for (i in 0..19) {
            resList.add(LStoreUtil.randomColor)
        }
        viewPager.setPageTransformer(true, ZoomOutSlideTransformer())
        viewPager.adapter = SlidePagerAdapter()
        tabLayout.setupWithViewPager(viewPager)
        LUIUtil.changeTabsFont(tabLayout, Constants.FONT_PATH)
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        showLongInformation(msg = getString(R.string.press_again_to_exit), isTopAnchor = false)
        Handler(Looper.getMainLooper()).postDelayed({
            doubleBackToExitPressedOnce = false
        }, 2000)
    }

    private inner class SlidePagerAdapter : PagerAdapter() {
        @SuppressLint("SetTextI18n")
        override fun instantiateItem(collection: ViewGroup, position: Int): Any {
//            val res = resList[position]
            val inflater = LayoutInflater.from(this@MenuActivity)
            val layout = inflater.inflate(R.layout.item_photo_slide_iv, collection, false) as ViewGroup
            val imageView = layout.findViewById<ImageView>(R.id.imageView)
            if (position % 2 == 0) {
                imageView.setImageResource(R.drawable.l_bkg_city)
            } else {
                imageView.setImageResource(R.drawable.logo)
            }
            val tv = layout.findViewById<TextView>(R.id.textView)
            tv.text = position.toString() + "/" + resList.size
            collection.addView(layout)
            return layout
        }

        override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
            if (view is View) {
                collection.removeView(view)
            }
        }

        override fun getCount(): Int {
            return resList.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return "Page Title $position"
        }
    }

}
