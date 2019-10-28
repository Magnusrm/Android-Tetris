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
        for (row in landed) {
            for (col in row) {
                if (row[col] != 0) {
                    canvas.drawRect(50f * (landed.indexOf(row) + 1) + 100f, 50f * (row.indexOf(col) + 1) + 100f,
                        50f * (landed.indexOf(row) + 2) + 100f, 50f * (row.indexOf(col) + 2) + 100f, painter)
                }
            }
        }

        for (row in currentPiece.shape) {
            for (col in row) {
                if (row[col] != 0) {
                    canvas.drawRect(50f * (currentPiece.shape.indexOf(row) + currentPiece.topLeft["row"]!! + 1) + 100f,
                        50f * (row.indexOf(col) + currentPiece.topLeft["col"]!! + 1) + 100f,
                        50f * (currentPiece.shape.indexOf(row) + currentPiece.topLeft["row"]!! + 2) + 100f,
                        50f * (row.indexOf(col) + currentPiece.topLeft["col"]!! + 2) + 100f, painter)
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
