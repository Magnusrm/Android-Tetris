package com.e.tetris

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import com.e.tetris.shapes.*
import java.util.*

class TetrisPiece {

    var shape : Array<IntArray> = arrayOf()
    var topLeft : Map<String, Int> = mapOf("row" to 0, "col" to 0)

    val painter: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 10f
        style = Paint.Style.FILL
        color = Color.RED
    }

    init {
        getBlock()
    }

    fun getBlock() = when (Random().nextInt(5) + 1) {
        1 -> square()
        2 -> zShape()
        3 -> line()
        4 -> lShape()
        5 -> tShape()
        else -> throw Exception("Invalid choice")
    }


    fun square() {
        shape = arrayOf(
            intArrayOf(1, 1),
            intArrayOf(1, 1))

        topLeft = mapOf("row" to 0, "column" to 4)
    }

    fun zShape() {
        shape = arrayOf(
            intArrayOf(4, 4, 0),
            intArrayOf(0, 4, 4))

        topLeft = mapOf("row" to 0, "column" to 4)
    }

    fun line() {
        shape = arrayOf(
            intArrayOf(2),
            intArrayOf(2),
            intArrayOf(2),
            intArrayOf(2))

        topLeft = mapOf("row" to 0, "column" to 4)
    }

    fun lShape() {
        shape  = arrayOf(
            intArrayOf(3, 0),
            intArrayOf(3, 0),
            intArrayOf(3, 3))

        topLeft = mapOf("row" to 0, "column" to 4)
    }

    fun tShape() {
        shape = arrayOf(
            intArrayOf(5, 5, 5),
            intArrayOf(0, 5, 0))

        topLeft = mapOf("row" to 0, "column" to 4)
    }
}
