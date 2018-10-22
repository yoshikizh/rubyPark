package io.rubypark.app.helper

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.animation.OvershootInterpolator
import android.widget.Scroller
import java.lang.reflect.Field


//
// View Pager + Tab 的切换实现
//
class ViewPagerAdapter(fm: FragmentManager, _fragmets:  ArrayList<Fragment>) : FragmentPagerAdapter(fm) {

    val mFragments: ArrayList<Fragment> = _fragmets

    override fun getItem(index: Int): Fragment {
        return mFragments[index]
    }

    override fun getCount(): Int {
        return mFragments.size
    }
}

class ViewPagerScroller(context: Context?, interpolator: OvershootInterpolator) : Scroller(context) {

    var mDuration: Int = 0

    public fun setDuration(_mDuration: Int) {
        mDuration = _mDuration;
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
        super.startScroll(startX, startY, dx, dy, this.mDuration);
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        super.startScroll(startX, startY, dx, dy, this.mDuration);
    }

}

fun AppCompatActivity.setViewPager(default_select_index: Int, view_pager: ViewPager, fragmens:  ArrayList<Fragment>){
    view_pager.adapter = ViewPagerAdapter(getSupportFragmentManager(),fragmens)

    val f: Field = ViewPager::class.java.getDeclaredField("mScroller")
    f.isAccessible = true
    val vpc: ViewPagerScroller = ViewPagerScroller(view_pager.context, OvershootInterpolator(0.6f))
    f.set(view_pager,vpc)
    vpc.duration = 700
    view_pager.setCurrentItem(default_select_index)
}


fun AppCompatActivity.setViewPagerAndFragment(default_select_index: Int, view_pager: ViewPager, tablayout_id: Int, fragmens:  ArrayList<Fragment>){
    view_pager.adapter = ViewPagerAdapter(getSupportFragmentManager(),fragmens)

    val f: Field = ViewPager::class.java.getDeclaredField("mScroller")
    f.isAccessible = true
    val vpc: ViewPagerScroller = ViewPagerScroller(view_pager.context, OvershootInterpolator(0.6f))
    f.set(view_pager,vpc)
    vpc.duration = 700


    // 默认选中
    val _tablayout = findViewById<TabLayout>(tablayout_id)
    _tablayout.getTabAt(default_select_index)!!.select()
    view_pager.setCurrentItem(default_select_index)

    view_pager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            _tablayout.getTabAt(position)!!.select()
        }

    })
}
