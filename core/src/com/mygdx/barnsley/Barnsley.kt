package com.mygdx.barnsley

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx.*
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

class Barnsley : ApplicationAdapter() {
    private lateinit var shapeRenderer: ShapeRenderer
    private lateinit var dots: ArrayList<Dot>

    override fun create() {
        shapeRenderer = ShapeRenderer()
        dots = ArrayList()
        dots.add(Dot(200f, 100f))
    }

    override fun render() {
        gl.glClearColor(255f, 255f, 255f, 1f)
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        addDot()
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        for (dot in dots) {
            dot.draw(shapeRenderer)
        }
        shapeRenderer.end()
    }

    private fun addDot() {
        val rnd = (1..100).shuffled().last()
        app.log(TAG, rnd.toString())
        val lastDot = calculateDown(dots.last())
        app.log(TAG, "${lastDot.x}, ${lastDot.y}")

        if (lastDot.x >= -2.1818 && lastDot.x <= 2.6556
                && lastDot.y >= 0 && lastDot.y <= 9.95851) {

            val lastVector = calculateUp(lastDot.x, lastDot.y, Color.GREEN)
            app.log(TAG, "${lastVector.x}, ${lastVector.y}")

            val color: Color = when (rnd) {
                1 -> Color.valueOf("#de7119")
                in 2..8 -> Color.valueOf("#400082")
                in 9..15 -> Color.valueOf("#18b0b0")
                in 16..100 -> Color.valueOf("#116979")
                else -> Color.GREEN
            }

            val dot: Dot = when (rnd) {
                1 -> Dot(0f, (.16 * lastDot.y).toFloat())
                in 2..8 -> Dot((.2 * lastDot.x - .26 * lastDot.y).toFloat(),
                        (.23 * lastDot.x + .22 * lastDot.y + 1.6).toFloat())
                in 9..15 -> Dot((-.15 * lastDot.x + .28 * lastDot.y).toFloat(),
                        (.26 * lastDot.x + .24 * lastDot.y + .44).toFloat())
                in 16..100 -> Dot((.85 * lastDot.x + .04 * lastDot.y).toFloat(),
                        (-.04 * lastDot.x + .85 * lastDot.y + 1.6).toFloat())
                else -> Dot(0f, 0f)
            }
            app.log(TAG, "${dot.x}, ${dot.y}")
            dots.add(calculateUp(dot.x, dot.y, color))

        }
    }

    private fun calculateDown(dot: Dot): Vector2 {
        val x = (dot.x / MULT) - 3
        val y = dot.y / MULT
        return Vector2(x, y)
    }

    private fun calculateUp(x: Float, y: Float, color: Color): Dot {
        return Dot((x + 3) * MULT, y * MULT, color)
    }

    companion object {
        const val TAG = "TAG"
        const val MULT = 70
    }
}