package com.mygdx.barnsley

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class Dot(val x: Float, val y: Float, val color: Color = Color.BLACK, private val radius: Float = 2f) {
    fun draw(shape: ShapeRenderer) {
        shape.color = this.color
        shape.circle(this.x, this.y, this.radius)
    }
}