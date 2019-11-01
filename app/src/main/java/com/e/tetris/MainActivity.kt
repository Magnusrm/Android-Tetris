package com.e.tetris

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/*
 * https://gamedevelopment.tutsplus.com/tutorials/implementing-tetris-collision-detection--gamedev-852
 * https://github.com/Odaym/Tetris
 */

class MainActivity : AppCompatActivity() {
    var tetris: TetrisView? = null

    var timer = Timer()

    var gameStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menu?.add(resources.getString(R.string.start_game))
        menu?.add(resources.getString(R.string.end_game))
        menu?.add(resources.getString(R.string.information))
        menu?.add(resources.getString(R.string.nor))
        menu?.add(resources.getString(R.string.eng))

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.toString()) {
            resources.getString(R.string.start_game) -> {if (!gameStarted){
                startGameLoop()
                addGameView()
            }}
            resources.getString(R.string.end_game) -> {if (gameStarted) {
                stopGameLoop()
                removeGameView()
            }}
            (resources.getString(R.string.information)) -> {startActivity(Intent(this, InfoActivity::class.java))}
            (resources.getString(R.string.eng)) -> {
                changeLang(item.toString())
            }
            (resources.getString(R.string.nor)) -> {
                changeLang(item.toString())
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        val x = event.x
        val y = event.y

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                if(x > mainContentLayout.width / 2 && y < mainContentLayout.height * 0.80) {
                    // move right
                    tetris?.move(1)
                }
                else if (x < mainContentLayout.width / 2 && y < mainContentLayout.height * 0.80) {
                    // move left
                    tetris?.move(0)
                } else if (y > mainContentLayout.height* 0.80){
                    tetris?.rotate()
                }
            }
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        stopGameLoop()
    }

    fun addGameView() {
        if (tetris == null) {
            tetris = TetrisView(this)
            mainContentLayout.addView(tetris)
        }
    }

    fun removeGameView() {
        mainContentLayout.removeView(tetris)
        tetris = null
    }

    fun startGameLoop() {
        timer = Timer()

        val gameTimerTask = object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    tetris?.loop()
                }
            }
        }

        timer.schedule(gameTimerTask, 0, 500)
        gameStarted = true
    }

    fun stopGameLoop() {
        timer.cancel()
        gameStarted = false
    }

    fun changeLang(lang : String) {

        var locale = resources.configuration.locale

        if (lang == resources.getString(R.string.eng)) {
            locale = Locale("en", "EN")
        }
        else if (lang == resources.getString(R.string.nor)) {
            locale = Locale("no", "NO")
        }

        val config = Configuration()
        config.locale = locale;
        val res = baseContext.resources
        res.updateConfiguration(config, res.displayMetrics)

        val updateIntent = intent
        finish()
        startActivity(updateIntent)

    }
}