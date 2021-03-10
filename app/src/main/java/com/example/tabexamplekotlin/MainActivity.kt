package com.example.tabexamplekotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.util.Log
import java.util.zip.Inflater

const val TAG: String = "MyLogs"

class MainActivity : AppCompatActivity() {

    //private val viewpager = findViewById<View>(R.id.viewpager) as ViewPager
    //private val tabLayout = findViewById<View>(R.id.tabs) as TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "Activity_main is initialized")

        initToolbar()

        val tabLayout: TabLayout = findViewById(R.id.tab_layout)

        val viewPager: ViewPager = findViewById(R.id.view_pager)

        val adapter = MyPagerAdapter(supportFragmentManager)

        Log.d(TAG, "TabLayout, ViewPager, Adapter initialized")

        viewPager.adapter = adapter

        Log.d(TAG,"ViewPager adapter is initialized")
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when(tab.position){
                    0 -> Log.d(TAG,"New Devices Tab is opened")
                    else -> Log.d(TAG,"Added devices Tab is opened")
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                Log.d(TAG, "Tab was unselected")
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                Log.d(TAG,"Tab was reselected")
            }
        })
    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Some title"
        Log.d(TAG, "ToolBar is initialized")
    }

    /*val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
    viewpager.adapter = fragmentAdapter

    tabs.setupWithViewPager(viewpager)*/
    //tabLayout.setupWithViewPager(viewpager)
}