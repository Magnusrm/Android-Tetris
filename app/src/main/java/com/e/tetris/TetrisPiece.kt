package com.e.tetris

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import java.util.*

class TetrisPiece {

    var shape : Array<IntArray> = arrayOf()
    var topLeft : IntArray = intArrayOf(0, 4)
    var potTopLeft : IntArray = intArrayOf(0, 4)


    init {
        getBlock()
    }

    fun getBlock() = when (Random().nextInt(7) + 1) {
        1 -> square()
        2 -> zShape()
        3 -> line()
        4 -> lShape()
        5 -> tShape()
        6 -> jShape()
        7 -> sShape()
        else -> throw Exception("Invalid choice")
    }


    fun square() {
        shape = arrayOf(
            intArrayOf(1, 1),
            intArrayOf(1, 1))

        topLeft = intArrayOf(0, 4)
        potTopLeft = intArrayOf(0, 4)
    }

    fun zShape() {
        shape = arrayOf(
            intArrayOf(4, 4, 0),
            intArrayOf(0, 4, 4))

        topLeft = intArrayOf(0, 4)
        potTopLeft = intArrayOf(0, 4)
    }

    fun line() {
        shape = arrayOf(
            intArrayOf(2),
            intArrayOf(2),
            intArrayOf(2),
            intArrayOf(2))

        topLeft = intArrayOf(0, 4)
        potTopLeft = intArrayOf(0, 4)
    }

    fun lShape() {
        shape  = arrayOf(
            intArrayOf(3, 0),
            intArrayOf(3, 0),
            intArrayOf(3, 3))

        topLeft = intArrayOf(0, 4)
        potTopLeft = intArrayOf(0, 4)
    }

    fun tShape() {
        shape = arrayOf(
            intArrayOf(5, 5, 5),
            intArrayOf(0, 5, 0))

        topLeft = intArrayOf(0, 4)
        potTopLeft = intArrayOf(0, 4)
    }

    fun jShape() {
        shape  = arrayOf(
            intArrayOf(0, 6),
            intArrayOf(0, 6),
            intArrayOf(6, 6))

        topLeft = intArrayOf(0, 4)
        potTopLeft = intArrayOf(0, 4)
    }

    fun sShape() {
        shape = arrayOf(
            intArrayOf(0, 7, 7),
            intArrayOf(7, 7, 0))

        topLeft = intArrayOf(0, 4)
        potTopLeft = intArrayOf(0, 4)
    }

}