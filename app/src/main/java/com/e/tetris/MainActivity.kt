package com.e.tetris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        addGameView()
        startGameLoop()

        mainContentLayout.post {
            /*
            addGameView()
            startGameLoop()


            mainContentLayout.setOnClickListener {
                if (gameStarted) {
                    stopGameLoop()
                    removeGameView()
                } else {
                    startGameLoop()
                    addGameView()
                }
            }
            */

        }
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
}