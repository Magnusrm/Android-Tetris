package com.e.tetris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        mainContentLayout.post {

            addGameView()

            mainContentLayout.setOnClickListener {
                if (gameStarted) {
                    stopGameLoop()
                    removeGameView()
                } else {
                    startGameLoop()
                    addGameView()
                }
            }
        }
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

        timer.schedule(gameTimerTask, 0, 20)
        gameStarted = true
    }

    fun stopGameLoop() {
        timer.cancel()
        gameStarted = false
    }
}