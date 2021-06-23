package com.example.ballrotatearoundballview

import android.view.View
import android.view.MotionEvent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.app.Activity
import android.content.Context

val colors : Array<Int> = arrayOf(
    "#f44336",
    "#004D40",
    "#FFD600",
    "#00C853",
    "#6200EA"
).map {
    Color.parseColor(it)
}.toTypedArray()
val parts : Int = 5
val scGap : Float = 0.02f / parts
val r1Factor : Float = 5.9f
val r2Factor : Float = 21.2f
val delay : Long = 20
val backColor : Int = Color.parseColor("#BDBDBD")
val rot : Float = 180f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n

fun Canvas.drawBallRotateAroundBall(scale : Float, w : Float, h : Float, paint : Paint) {
    val r1 : Float = Math.min(w, h) / r1Factor
    val r2 : Float = Math.min(w, h) / r2Factor
    val sc1 : Float = scale.divideScale(0, parts)
    val sc2 : Float = scale.divideScale(1, parts)
    val sc3 : Float = scale.divideScale(2, parts)
    val sc4 : Float = scale.divideScale(3, parts)
    val sc5 : Float = scale.divideScale(4, parts)
    save()
    translate(w / 2, h / 2)
    drawCircle(0f, 0f, r1 * (sc1 - sc5), paint)
    save()
    rotate(rot * sc3)
    drawCircle(-r2 + (w - r2) * (sc2  - sc4), 0f, r2, paint)
    restore()
    restore()
}

fun Canvas.drawBRABNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = colors[i]
    drawBallRotateAroundBall(scale, w, h, paint)
}
