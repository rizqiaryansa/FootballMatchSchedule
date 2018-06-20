package com.rizqiaryansa.footballmatchschedule.view.view

import android.graphics.Typeface
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import com.rizqiaryansa.footballmatchschedule.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class EventMatchUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        cardView {
            lparams(width = matchParent, height = wrapContent) {
                topMargin = dip(8)
                leftMargin = dip(16)
                rightMargin = dip(16)
                radius = dip(4).toFloat()
            }

            linearLayout {
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER
                padding = dip(8)

                textView {
                    id = R.id.time_schedule
                    textColor = R.color.colorPrimary
                    gravity = Gravity.CENTER
                    setTypeface(null, Typeface.BOLD)
                    textSize = 14f
                }.lparams{
                    bottomMargin = dip(8)
                }

                linearLayout {
                    lparams(width = wrapContent, height = matchParent)
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER_HORIZONTAL

                    textView {
                        id = R.id.tvHomeTeam
                        textSize = 18f
                        textColor = R.color.colorBlack
                    }.lparams(width = wrapContent, height = wrapContent) {

                    }

                    textView {
                        id = R.id.tvHomeScore
                        textSize = 16f
                        setTypeface(null, Typeface.BOLD)
                    }.lparams(width = wrapContent, height = wrapContent) {
                        leftMargin = dip(8)
                    }

                    textView {
                        id = R.id.tvVersus
                        text = "vs"
                        textSize = 14f
                    }.lparams(height = wrapContent, width = wrapContent) {
                        leftMargin = dip(4)
                    }

                    textView {
                        id = R.id.tvAwayScore
                        setTypeface(null, Typeface.BOLD)
                        textSize = 16f
                    }.lparams(width = wrapContent, height = wrapContent) {
                        leftMargin = dip(4)
                    }

                    textView {
                        id = R.id.tvAwayTeam
                        textSize = 18f
                        textColor = R.color.colorBlack
                    }.lparams(width = wrapContent, height = wrapContent) {
                        leftMargin = dip(8)
                    }
                }
            }.lparams(width = matchParent, height = wrapContent) {
                margin = dip(8)
            }
        }
    }
}