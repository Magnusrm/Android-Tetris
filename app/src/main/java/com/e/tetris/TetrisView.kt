package com.e.tetris

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class TetrisView(context: Context) : View(context) {

    val boxSize = 95f
    val marginX = 75f
    val marginY = 10f

    var landed = Array(16) {Array(10) {0} }

    val painter: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 10f
        style = Paint.Style.FILL
        color = Color.RED
    }

    val gridPainter : Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 7f
        style = Paint.Style.STROKE
        color = Color.BLACK
    }

    private var currentPiece = TetrisPiece()


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawBoard(canvas)

    }


    fun drawBoard(canvas : Canvas) {

        for (i in currentPiece.shape.indices) {
            for (j in currentPiece.shape[i].indices) {
                if (currentPiece.shape[i][j] != 0) {
                    canvas.drawRect(boxSize * (j + currentPiece.topLeft[1]) + marginX,
                        boxSize * (i + currentPiece.topLeft[0]) + marginY,
                        boxSize * (j + currentPiece.topLeft[1] + 1) + marginX,
                        boxSize * (i + currentPiece.topLeft[0] + 1) + marginY, painter)
                }
            }
        }

        for (i in landed.indices) {
            for (j in landed[i].indices) {
                if (landed[i][j] != 0) {
                    canvas.drawRect(boxSize * (j) + marginX, boxSize * (i) + marginY,
                        boxSize * (j + 1) + marginX, boxSize * (i + 1) + marginY, painter)
                }
                canvas.drawRect(boxSize * (j) + marginX, boxSize * (i) + marginY,
                    boxSize * (j + 1) + marginX, boxSize * (i + 1) + marginY, gridPainter)
            }
        }
    }

    fun loop() {
        render()
    }

    fun render() {
        fall()
        invalidate()
    }

    fun newPiece() {
        currentPiece.getBlock()
    }

    fun fall() {
        currentPiece.potTopLeft[0]++

        for (i in currentPiece.shape.indices) {
            for (j in currentPiece.shape[i].indices) {
                if (currentPiece.shape[i][j] != 0) {
                    if (currentPiece.potTopLeft[0] + i >= landed.size - 1) {
                        addToLanded()
                        return
                    }
                    if (landed[i + currentPiece.potTopLeft[0] +1][j + currentPiece.potTopLeft[1]] != 0) {
                        addToLanded()
                        return
                    }
                }
            }
        }

        currentPiece.topLeft = currentPiece.potTopLeft
    }

    fun addToLanded() {
        for (i in currentPiece.shape.indices) {
            for (j in currentPiece.shape[i].indices) {
                if (currentPiece.shape[i][j] != 0) {
                    landed[i + currentPiece.topLeft[0]][j + currentPiece.topLeft[1]] = currentPiece.shape[i][j]
                }
            }
        }

        newPiece()
    }

    fun move(dir : Int) {
        if (dir == 1) { // move right
            currentPiece.potTopLeft[1]++
        }

        else if (dir == 0) { // move left
            currentPiece.potTopLeft[1]--
        }

        for (i in currentPiece.shape.indices) {
            for (j in currentPiece.shape[i].indices) {
                if (currentPiece.shape[i][j] != 0) {
                    if (currentPiece.potTopLeft[1] + j > landed[0].size - 1) {
                        currentPiece.potTopLeft[1]--
                        return
                    }
                    else if (currentPiece.potTopLeft[1] + j < 0) {
                        currentPiece.potTopLeft[1]++
                        return
                    }
                    if (landed[i + currentPiece.potTopLeft[0]][j + currentPiece.potTopLeft[1]] != 0) {
                        if(dir == 1) {
                            currentPiece.potTopLeft[1]--
                        } else if (dir == 0) {
                            currentPiece.potTopLeft[1]++
                        }
                        return
                    }
                }
            }
        }
        currentPiece.topLeft = currentPiece.potTopLeft
    }

    fun rotate() {

        val width = currentPiece.shape[0].size
        val height = currentPiece.shape.size
        val potShape = Array(width){IntArray(height)}

        for (i in 0..width - 1) {
            for (j in 0..height - 1)
                potShape[i][j] = currentPiece.shape[height-j-1][i]
        }
        for (i in potShape.indices) {
            for (j in potShape[0].indices) {
                if (potShape[i][j] != 0) {
                    if (j + currentPiece.topLeft[1] < 0) {
                        //this block would be to the left of the playing field
                        return
                    }
                    if (j + currentPiece.topLeft[1] >= landed[0].size) {
                        //this block would be to the right of the playing field
                        return
                    }
                    if (i + currentPiece.topLeft[0] >= landed.size) {
                        //this block would be below the playing field
                        return
                    }
                    if (landed[i + currentPiece.topLeft[0]][j + currentPiece.topLeft[1]] != 0) {
                        //the space is taken
                        return
                    }
                }
            }
        }

        currentPiece.shape = potShape

    }
}
