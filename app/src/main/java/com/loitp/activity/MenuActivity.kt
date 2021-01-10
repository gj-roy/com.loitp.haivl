package com.loitp.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.annotation.IsFullScreen
import com.annotation.LogTag
import com.core.base.BaseFontActivity
import com.core.common.Constants
import com.core.helper.gallery.albumonly.GalleryCorePhotosOnlyFrm
import com.core.utilities.LUIUtil
import com.loitp.R
import com.loitp.model.Flickr
import com.views.viewpager.viewpagertransformers.ZoomOutSlideTransformer
import kotlinx.android.synthetic.main.activity_menu.*
import kotlin.collections.ArrayList

@LogTag("MenuActivity")
@IsFullScreen(false)
class MenuActivity : BaseFontActivity() {
    private val listFlickr = ArrayList<Flickr>()

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_menu
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
    }

    private fun setupViews() {

        //setup data
        listFlickr.add(Flickr("Độc đáo thú vị", Constants.FLICKR_ID_VN_DOCDAOTHUVI))
        listFlickr.add(Flickr("Cosplay", Constants.FLICKR_ID_COSPLAY))
        listFlickr.add(Flickr("Hại não", Constants.FLICKR_ID_HAINAO))
        listFlickr.add(Flickr("Bạn có biết", Constants.FLICKR_ID_VN_BANCOBIET))
        listFlickr.add(Flickr("Cung hoàng đạo", Constants.FLICKR_ID_VN_CUNGHOANGDAOFUNTFACT))
        listFlickr.add(Flickr("Hehehoro", Constants.FLICKR_ID_VN_CUNGHOANGDAOHEHEHORO))
        listFlickr.add(Flickr("Devvui", Constants.FLICKR_ID_VN_DEVVUI))
        listFlickr.add(Flickr("Funny manga", Constants.FLICKR_ID_VN_FUNNYMANGA))
        listFlickr.add(Flickr("Màn hình", Constants.FLICKR_ID_VN_FUNNYMANHINH))
        listFlickr.add(Flickr("Funny thể thao", Constants.FLICKR_ID_VN_FUNNYTHETHAO))
        listFlickr.add(Flickr("Hài hước", Constants.FLICKR_ID_VN_HAIHUOC))
        listFlickr.add(Flickr("Status vui", Constants.FLICKR_ID_VN_STTVUI))
        listFlickr.add(Flickr("Status đểu chất", Constants.FLICKR_ID_VN_STTDEUCHAT))
        listFlickr.add(Flickr("Troll", Constants.FLICKR_ID_VN_TROLL))
        listFlickr.add(Flickr("Truyện bựa", Constants.FLICKR_ID_VN_TRUYENBUA))
        listFlickr.add(Flickr("Truyện ngắn", Constants.FLICKR_ID_VN_TRUYENNGAN))
        listFlickr.add(Flickr("Tuổi thơ dữ dội", Constants.FLICKR_ID_VN_TUOITHODUDOI))
        listFlickr.add(Flickr("Ảnh chế", Constants.FLICKR_ID_VN_ANHCHESACHGIAOKHOA))
        listFlickr.add(Flickr("Ảnh theo tên", Constants.FLICKR_ID_VN_ANHTHEOTEN))

        viewPager.setPageTransformer(true, ZoomOutSlideTransformer())
        viewPager.adapter = SlidePagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
        LUIUtil.changeTabsFont(tabLayout = tabLayout, fontName = Constants.FONT_PATH)
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

    private inner class SlidePagerAdapter(
            fragmentManager: FragmentManager
    ) : FragmentStatePagerAdapter(
            fragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

        override fun getItem(position: Int): Fragment {
            val flickr = listFlickr[position]

            val frm = GalleryCorePhotosOnlyFrm()
            val bundle = Bundle()
            bundle.putString(Constants.SK_PHOTOSET_ID, flickr.flickrId)
            frm.arguments = bundle

            return frm
        }

        override fun getCount(): Int {
            return listFlickr.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return listFlickr[position].title
        }
    }

}
