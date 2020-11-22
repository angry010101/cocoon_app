package com.yakymovych.simon.yogaapp.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.yakymovych.simon.yogaapp.R
import com.yakymovych.simon.yogaapp.presentation.di.DaggerViewModelFactory
import com.yakymovych.simon.yogaapp.presentation.ui.BaseFragment
import com.yakymovych.simon.yogaapp.presentation.ui.BaseViewModel
import javax.inject.Inject

class CollectionFragment : BaseFragment() {

    @Inject
    lateinit var viewModeFactory: DaggerViewModelFactory
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    private lateinit var demoCollectionPagerAdapter: DemoCollectionPagerAdapter
    private lateinit var viewPager: ViewPager


    lateinit var mainViewModel: MainViewModel


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mainViewModel = ViewModelProvider(activity!!, viewModeFactory)
                .get(MainViewModel::class.java)
        return inflater.inflate(R.layout.collection_demo, container, false)
    }

    override fun getBaseViewModel(): BaseViewModel = mainViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        demoCollectionPagerAdapter = DemoCollectionPagerAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = demoCollectionPagerAdapter
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if (position == 1){
                    mainViewModel.requestCached()
                }
            }

        })
    }
}

class DemoCollectionPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int  = 2

    override fun getItem(i: Int): Fragment {
        return if (i == 0){
            MainFragment()
        }
        else {
            FavoritesFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return if (position == 0) "TOP" else "FAVORITES"
    }
}