package com.rizqiaryansa.footballmatchschedule.view.view

import android.os.Bundle
import android.support.constraint.ConstraintLayout.LayoutParams.PARENT_ID
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat.getDrawable
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.rizqiaryansa.footballmatchschedule.R
import com.rizqiaryansa.footballmatchschedule.R.color.colorPrimary
import com.rizqiaryansa.footballmatchschedule.R.color.colorWhite
import com.rizqiaryansa.footballmatchschedule.view.view.favorites.FavoritesFragment
import com.rizqiaryansa.footballmatchschedule.view.view.next.NextFragment
import com.rizqiaryansa.footballmatchschedule.view.view.prev.PrevFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.design.bottomNavigationView

class LeagueActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toolbar = supportActionBar as ActionBar

        LeagueActivityUI().setContentView(this)

        val bottomNavigation: BottomNavigationView = find(R.id.navigation)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if(savedInstanceState == null) {
            addFragment(PrevFragment.prevInstance())
        }
    }

    class LeagueActivityUI : AnkoComponent<LeagueActivity> {
        override fun createView(ui: AnkoContext<LeagueActivity>) = with(ui) {
            relativeLayout {
                lparams(width = matchParent, height = matchParent)

                frameLayout {
                    id = R.id.container
                }.lparams(width = matchParent, height = matchParent) {
                    above(R.id.bottom_layout)
                }

                linearLayout {
                    id = R.id.bottom_layout
                    orientation = LinearLayout.VERTICAL

                    view {
                        background = resources.getDrawable(R.drawable.shadow)
                    }.lparams(height = dip(4), width = matchParent)

                    bottomNavigationView {
                        id = R.id.navigation
                        inflateMenu(R.menu.navigation)
                        itemBackgroundResource = colorPrimary
                        backgroundColor = android.R.attr.windowBackground
                        itemTextColor = resources.getColorStateList(colorWhite)
                        itemIconTintList = resources.getColorStateList(colorWhite)
                    }.lparams(width = matchParent, height = wrapContent) {
                        marginEnd = dip(0)
                        marginStart = dip(0)
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    alignParentBottom()
                }
            }
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_prev_league -> {
                val prevFragment = PrevFragment.prevInstance()
                addFragment(prevFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_next_league -> {
                val nextFragment = NextFragment.nextInstance()
                addFragment(nextFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {
                val favoFragment = FavoritesFragment.favoInstance()
                addFragment(favoFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
