package com.rizqiaryansa.footballmatchschedule.view.view.detail

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import com.google.gson.Gson
import com.rizqiaryansa.footballmatchschedule.R
import com.rizqiaryansa.footballmatchschedule.R.color.colorPrimary
import com.rizqiaryansa.footballmatchschedule.R.string.*
import com.rizqiaryansa.footballmatchschedule.view.api.ApiRequest
import com.rizqiaryansa.footballmatchschedule.view.model.Event
import com.rizqiaryansa.footballmatchschedule.view.model.Team
import com.rizqiaryansa.footballmatchschedule.view.presenter.DetailPresenter
import com.rizqiaryansa.footballmatchschedule.view.util.gone
import com.rizqiaryansa.footballmatchschedule.view.util.visible
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var eventDetail: Event
    private lateinit var badgeHome: Team
    private lateinit var badgeAway: Team

    private lateinit var tvDateEvent: TextView
    private lateinit var tvHomeScore: TextView
    private lateinit var tvAwayScore: TextView
    private lateinit var tvHomeTeam: TextView
    private lateinit var tvAwayTeam: TextView
    private lateinit var tvHomeFormation: TextView
    private lateinit var tvAwayFormation: TextView
    private lateinit var tvHomeShot: TextView
    private lateinit var tvAwayShot: TextView
    private lateinit var tvHomeGoalkeeper: TextView
    private lateinit var tvAwayGoalkeeper: TextView
    private lateinit var tvHomeDefense: TextView
    private lateinit var tvAwayDefense: TextView
    private lateinit var tvHomeMidfield: TextView
    private lateinit var tvAwayMidfield: TextView
    private lateinit var tvHomeForward: TextView
    private lateinit var tvAwayForward: TextView

    private lateinit var ivHomeBadge: ImageView
    private lateinit var ivAwayBadge: ImageView

    private lateinit var lyEventDetail: LinearLayout
    private lateinit var progressBar: ProgressBar

    private var itemHomeId: String? = null
    private var itemAwayId: String? = null

    private lateinit var presenter: DetailPresenter
    private var idEventDetail: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.extras != null) {
            idEventDetail = intent.getStringExtra(getString(item_eventdetail_id))
            itemHomeId = intent.getStringExtra(getString(item_home_id))
            itemAwayId = intent.getStringExtra(getString(item_away_id))
        }

        initView()
        getEventDetail()
    }

    private fun initView() {
        scrollView {
            lparams(width = matchParent, height = matchParent)

            relativeLayout {
                cardView {
                    lyEventDetail = linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER

                        tvDateEvent = textView {
                            gravity = Gravity.CENTER
                            textColor = colorPrimary
                            textSize = 18f
                        }.lparams(width = wrapContent, height = wrapContent) {
                            topMargin = dip(8)
                            bottomMargin = dip(16)
                        }

                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER

                            ivHomeBadge = imageView().lparams(width = dip(48), height = dip(48)) {
                                gravity = Gravity.CENTER
                                topPadding = dip(16)
                                rightMargin = dip(36)
                            }

                            tvHomeScore = textView {
                                gravity = Gravity.CENTER
                                textSize = 48f
                                setTypeface(null, Typeface.BOLD)
                            }.lparams(width = wrapContent, height = wrapContent)

                            textView {
                                text = "vs"
                                textSize = 24f
                            }.lparams(width = wrapContent, height = wrapContent) {
                                leftMargin = dip(8)
                                rightMargin = dip(8)
                            }

                            tvAwayScore = textView {
                                gravity = Gravity.CENTER
                                textSize = 48f
                                setTypeface(null, Typeface.BOLD)
                            }.lparams(width = wrapContent, height = wrapContent)

                            ivAwayBadge = imageView().lparams(width = dip(48), height = dip(48)) {
                                gravity = Gravity.CENTER
                                topPadding = dip(16)
                                leftMargin = dip(36)

                            }

                        }.lparams(height = wrapContent, width = wrapContent)

                        //team and formation
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            weightSum = 1f
                            gravity = Gravity.CENTER

                            linearLayout {
                                orientation = LinearLayout.VERTICAL

                                tvHomeTeam = textView {
                                    textColor = colorPrimary
                                    textSize = 20f
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }

                                tvHomeFormation = textView {
                                    textSize = 14f
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }

                            }.lparams(width = 0, height = wrapContent, weight = 0.45f) {
                                margin = dip(16)
                            }

                            linearLayout {
                                orientation = LinearLayout.VERTICAL
                            }.lparams(width = 0, height = wrapContent, weight = 0.1f) {
                                margin = dip(16)
                            }

                            linearLayout {
                                orientation = LinearLayout.VERTICAL

                                tvAwayTeam = textView {

                                    textColor = colorPrimary
                                    textSize = 20f
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }

                                tvAwayFormation = textView {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                    textSize = 14f
                                }

                            }.lparams(width = 0, height = wrapContent, weight = 0.45f) {
                                margin = dip(16)
                            }
                        }

                        view {
                            backgroundColorResource = android.R.color.darker_gray
                        }.lparams(width = matchParent, height = dip(2)) {
                            bottomMargin = dip(48)
                        }

                        //shot
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            weightSum = 1f

                            relativeLayout {
                                tvHomeShot = textView {
                                    textSize = 20f
                                    setTypeface(null, Typeface.BOLD)
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.START
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(16)
                            }

                            relativeLayout {
                                textView {
                                    textSize = 18f
                                    text = "Shots"
                                    textColor = colorPrimary
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.2f) {
                                topMargin = dip(16)
                            }

                            relativeLayout {
                                tvAwayShot = textView {
                                    textSize = 20f
                                    setTypeface(null, Typeface.BOLD)
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.END
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(16)
                            }
                        }

                        view {
                            backgroundColorResource = android.R.color.darker_gray
                        }.lparams(width = matchParent, height = dip(2)) {
                            bottomMargin = dip(16)
                        }

                        textView {
                            textSize = 18f
                            text = "Lineups"
                        }.lparams(width = wrapContent, height = wrapContent)

                        //goalkeeper
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            weightSum = 1f

                            relativeLayout {
                                tvHomeGoalkeeper = textView {
                                    textSize = 16f

                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.START
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(16)
                            }

                            relativeLayout {
                                textView {
                                    textSize = 14f
                                    text = "Goal Keeper"
                                    textColor = colorPrimary
                                    setTypeface(null, Typeface.BOLD)
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.2f) {
                                topMargin = dip(16)
                            }

                            relativeLayout {
                                tvAwayGoalkeeper = textView {
                                    textSize = 16f
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.END
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(16)
                            }
                        }

                        //Defense
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            weightSum = 1f

                            relativeLayout {
                                tvHomeDefense = textView {
                                    textSize = 16f

                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.START
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(8)
                            }

                            relativeLayout {
                                textView {
                                    textSize = 14f
                                    text = "Defense"
                                    textColor = colorPrimary
                                    setTypeface(null, Typeface.BOLD)
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.2f) {
                                topMargin = dip(8)
                            }

                            relativeLayout {
                                tvAwayDefense = textView {
                                    textSize = 16f
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.END
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(8)
                            }
                        }

                        //Midfield
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            weightSum = 1f

                            relativeLayout {
                                tvHomeMidfield = textView {
                                    textSize = 16f

                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.START
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(8)
                            }

                            relativeLayout {
                                textView {
                                    textSize = 14f
                                    text = "Midfield"
                                    textColor = colorPrimary
                                    setTypeface(null, Typeface.BOLD)
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.2f) {
                                topMargin = dip(8)
                            }

                            relativeLayout {
                                tvAwayMidfield = textView {
                                    textSize = 16f
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.END
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(8)
                            }
                        }

                        //Forward
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            weightSum = 1f

                            relativeLayout {
                                tvHomeForward = textView {
                                    textSize = 16f

                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.START
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(8)
                            }

                            relativeLayout {
                                textView {
                                    textSize = 14f
                                    text = "Forward"
                                    textColor = colorPrimary
                                    setTypeface(null, Typeface.BOLD)
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.2f) {
                                topMargin = dip(8)
                            }

                            relativeLayout {
                                tvAwayForward = textView {
                                    textSize = 16f
                                }.lparams(width = wrapContent, height = wrapContent) {
                                    gravity = Gravity.END
                                }
                            }.lparams(width = dip(0), height = wrapContent, weight = 0.4f) {
                                topMargin = dip(8)
                            }
                        }

                    }.lparams(width = matchParent, height = wrapContent) {
                        margin = dip(16)
                    }

                }.lparams(width = matchParent, height = matchParent)

                progressBar = progressBar {
                }.lparams {
                    centerInParent()
                }
            }.lparams(width = matchParent, height = matchParent)
        }
    }

    private fun bindView() {

        val timeEvent = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .parse(eventDetail.dateEvent)
        val dateEvent = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
                .format(timeEvent)

        tvDateEvent.text = dateEvent
        tvHomeScore.text = eventDetail.intHomeScore
        tvAwayScore.text = eventDetail.intAwayScore
        tvHomeTeam.text = eventDetail.strHomeTeam
        tvHomeFormation.text = eventDetail.strHomeFormation
        tvAwayFormation.text = eventDetail.strAwayFormation
        tvHomeShot.text = eventDetail.intHomeShots
        tvAwayShot.text = eventDetail.intAwayShots
        tvAwayTeam.text = eventDetail.strAwayTeam
        tvHomeGoalkeeper.text = setPlayerTeam(eventDetail.strHomeLineupGoalKeeper)
        tvAwayGoalkeeper.text = setPlayerTeam(eventDetail.strAwayLineupGoalkeeper)
        tvHomeDefense.text = setPlayerTeam(eventDetail.strHomeLineupDefense)
        tvAwayDefense.text = setPlayerTeam(eventDetail.strAwayLineupDefense)
        tvHomeMidfield.text = setPlayerTeam(eventDetail.strHomeLineupMidfield)
        tvAwayMidfield.text = setPlayerTeam(eventDetail.strAwayLineupMidfield)
        tvHomeForward.text = setPlayerTeam(eventDetail.strHomeLineupForward)
        tvAwayForward.text = setPlayerTeam(eventDetail.strAwayLineupForward)

        Picasso.get().load(badgeHome.teamBadge).resize(50,50).into(ivHomeBadge)
        Picasso.get().load(badgeAway.teamBadge).resize(50,50).into(ivAwayBadge)

    }

    private fun getEventDetail() {
        presenter = DetailPresenter(this, ApiRequest(), Gson())
        presenter.getEventDetail(idEventDetail, itemHomeId, itemAwayId)
    }

    private fun setPlayerTeam(playerName: String?): String? {
        val bulkPlayer = playerName?.split(";".toRegex())?.dropLastWhile {
            it.isEmpty()
        }?.map { it.trim() }?.toTypedArray()?.joinToString("\n")

        return bulkPlayer
    }

    override fun hideLoading() {
        progressBar.gone()
        lyEventDetail.visible()
    }

    override fun showLoading() {
        progressBar.visible()
        lyEventDetail.gone()
    }

    override fun showEventList(data: List<Event>, home: List<Team>, away: List<Team>) {
        eventDetail = data[0]
        badgeAway = away[0]
        badgeHome = home[0]

        bindView()
    }

}
