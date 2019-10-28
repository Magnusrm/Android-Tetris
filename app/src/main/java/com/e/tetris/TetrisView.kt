package com.e.tetris

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class TetrisView(context: Context) : View(context) {

    var landed = Array(16) {Array(10) {0} }

    val painter: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 10f
        style = Paint.Style.FILL
        color = Color.RED
    }

    private var currentPiece = TetrisPiece()


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawBoard(canvas)

    }

    fun drawBoard(canvas : Canvas) {
        for (i in landed.indices) {
            for (j in landed[i].indices) {
                if (landed[i][j] != 0) {
                    canvas.drawRect(50f * (i + 1) + 100f, 50f * (j + 1) + 100f,
                        50f * (i + 2) + 100f, 50f * (j + 2) + 100f, painter)
                }
            }
        }

        for (i in currentPiece.shape.indices) {
            for (j in currentPiece.shape[i].indices) {
                if (currentPiece.shape[i][j] != 0) {
                    canvas.drawRect(50f * (i + currentPiece.topLeft[0] + 1) + 100f,
                        50f * (j + currentPiece.topLeft[1] + 1) + 100f,
                        50f * (i + currentPiece.topLeft[0] + 2) + 100f,
                        50f * (j + currentPiece.topLeft[1] + 2) + 100f, painter)
                }
            }
        }
    }

    fun loop() {
        render()
    }

    fun render() {
        invalidate()
    }

    fun newPiece() {
        currentPiece.getBlock()
    }
}
