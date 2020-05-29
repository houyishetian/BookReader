package lin.com.bookreader.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import lin.com.bookreader.R
import lin.com.bookreader.adapter.ViewPagerAdapter
import lin.com.bookreader.extensions.hideSoftInput
import lin.com.bookreader.fragment.MainBookMarkFragment
import lin.com.bookreader.fragment.MainScanFragment
import lin.com.bookreader.fragment.MainSearchFragment

class MainActivity : AppCompatActivity() {

    private var menuItem: MenuItem? = null

    private val defaultItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val fragmentList = listOf(
            MainScanFragment(), MainSearchFragment(), MainBookMarkFragment()
        )
        main_view_pager.adapter = ViewPagerAdapter(fragmentList, supportFragmentManager)
        main_view_pager.offscreenPageLimit = 2
        main_view_pager.currentItem = defaultItem
        main_bottom_navitaion_view.menu.getItem(defaultItem).setChecked(true)

        main_view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                menuItem?.setChecked(false)
                menuItem = main_bottom_navitaion_view.menu.getItem(position)
                menuItem?.setChecked(true)
            }

        })
        main_bottom_navitaion_view.setOnNavigationItemSelectedListener {
            hideSoftInput()
            when (it.itemId) {
                R.id.mainScanFragment -> main_view_pager.currentItem = 0
                R.id.mainSearchFragment -> main_view_pager.currentItem = 1
                R.id.mainBookMarkFragment -> main_view_pager.currentItem = 2
            }
            menuItem = it
            return@setOnNavigationItemSelectedListener false
        }
    }
}
