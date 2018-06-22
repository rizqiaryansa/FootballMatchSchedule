package com.rizqiaryansa.footballmatchschedule.view.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class FavoriteDatabaseHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx,
        "FavoriteFootball.db", null, 1) {

    companion object {
        private var instance: FavoriteDatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): FavoriteDatabaseHelper {
            if(instance == null) {
                instance = FavoriteDatabaseHelper(ctx.applicationContext)
            }
            return instance as FavoriteDatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(FavoriteDB.TABLE_FAVORITE, true,
                FavoriteDB.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteDB.EVENT_ID to TEXT + UNIQUE,
                FavoriteDB.EVENT_TIME to TEXT,
                FavoriteDB.HOME_TEAM to TEXT,
                FavoriteDB.HOME_SCORE to TEXT,
                FavoriteDB.AWAY_TEAM to TEXT,
                FavoriteDB.AWAY_SCORE to TEXT,
                FavoriteDB.HOME_TEAM_ID to TEXT,
                FavoriteDB.AWAY_TEAM_ID to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteDB.TABLE_FAVORITE, true)
    }
}