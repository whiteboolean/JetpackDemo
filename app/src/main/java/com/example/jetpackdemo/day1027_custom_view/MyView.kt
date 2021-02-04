package com.example.jetpackdemo.day1027_custom_view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.withRotation
import androidx.core.graphics.withTranslation
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.jetpackdemo.R
import kotlinx.coroutines.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * @Class: MyView
 * @Description:
 * @author: Jett
 * @Date: 10/27/20-9:39 AM
 */
private const val TAG = "MyView"

class MyView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), LifecycleObserver {

    private var mWidth = 0f
    private var mHeight = 0f
    private var mRadius = 0f
    private var mAngle = 10f
    private lateinit var launchJob: Job
    private var sinWaveSamplesPath = Path()

    private val dashedLinePaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 5f
        pathEffect = DashPathEffect(floatArrayOf(10f, 10f), 0f)
        color = ContextCompat.getColor(context, R.color.colorAccent1)
    }

    private val fillCirclePaint = Paint().apply {
        style = Paint.Style.FILL
        strokeWidth = 5f
        color = ContextCompat.getColor(context, R.color.colorBlack)
    }

    private val textPaint = Paint().apply {
        textSize = 40f
        typeface = Typeface.DEFAULT_BOLD ////汉字是等宽的
        color = ContextCompat.getColor(context, R.color.colorBlack)
    }
    private val solidLinePaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 5f
        color = ContextCompat.getColor(context, R.color.colorBlack)
    }

    private val vectorLinePaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 5f
        color = ContextCompat.getColor(context, R.color.colorAccent1)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        Log.d(TAG, "onFinishInflate: ")
    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(TAG, "onAttachedToWindow: ")
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d(TAG, "onMeasure: ")
    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r, b)
        Log.d(TAG, "layout: ")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d(TAG, "onLayout: ")
    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(TAG, "onDetachedFromWindow: ")
    }


    /**
     * View最终尺寸确认
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w.toFloat()
        mHeight = h.toFloat()
        mRadius = if (w < h / 2) w / 2.toFloat() else h / 4.toFloat()
        mRadius -= 20f

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw: ")
        canvas?.apply {
            drawAxises(this)
            drawLabel(this)
            drawDashedCircle(this)
            drawVector(this)
            drawProjections(this)
            drawSinWave(this)
        }

    }

    private fun drawAxises(canvas: Canvas) {
        canvas.withTranslation(mWidth / 2, mHeight / 2) {
            drawLine(-mWidth / 2, 0f, mWidth / 2, 0f, solidLinePaint)
            drawLine(0f, -mHeight / 2, 0f, mHeight / 2, solidLinePaint)
        }

        canvas.withTranslation(mWidth / 2, mHeight / 4 * 3) {
            drawLine(-mWidth / 2, 0f, mWidth / 2, 0f, solidLinePaint)
        }

    }


    private fun drawLabel(canvas: Canvas) {
        canvas.apply {
            drawRect(100f, 100f, 500f, 250f, solidLinePaint)
            drawText("指数函数与旋转矢量", 120f, 195f, textPaint)
        }
    }


    private fun drawDashedCircle(canvas: Canvas) {
        canvas.withTranslation(mWidth / 2, mHeight / 4 * 3) {
            drawCircle(0f, 0f, mRadius, dashedLinePaint)
        }
    }

    private fun drawVector(canvas: Canvas) {
        canvas.withTranslation(mWidth / 2, mHeight / 4 * 3) {
            withRotation(-mAngle) {
                drawLine(0f, 0f, mRadius, 0f, vectorLinePaint)
            }
        }
    }

    private fun drawProjections(canvas: Canvas) {
        canvas.withTranslation(mWidth / 2, mHeight / 2) {
            drawCircle((mRadius * cos(mAngle.toRadians())), 0f, 20f, fillCirclePaint)
        }

        canvas.withTranslation(mWidth / 2, mHeight / 4 * 3) {
            drawCircle((mRadius * cos(mAngle.toRadians())), 0f, 20f, fillCirclePaint)
        }

        canvas.withTranslation(mWidth / 2, mHeight / 4 * 3) {
            val x = mRadius * cos(mAngle.toRadians())
            val y = mRadius * sin(mAngle.toRadians())
            withTranslation(x, -y) {
                drawLine(0f, 0f, 0f, y, solidLinePaint)
                drawLine(0f, 0f, 0f, -mHeight / 4 + y, dashedLinePaint)
            }
        }
    }

    private fun drawSinWave(canvas: Canvas) {
        canvas.withTranslation(mWidth / 2, mHeight / 2) {
            val samplesCount = 100
            val dy = mHeight / 2 / samplesCount
            sinWaveSamplesPath.reset()
            sinWaveSamplesPath.moveTo(mRadius * cos(mAngle.toRadians()), 0f)
            repeat(samplesCount) {
                val x = mRadius * cos(it * -0.15 + mAngle.toRadians())
                val y = -dy * it
                sinWaveSamplesPath.quadTo(x.toFloat(), y,x.toFloat(), y)
            }
            drawPath(sinWaveSamplesPath,vectorLinePaint)
            drawTextOnPath("hello world",sinWaveSamplesPath,1000f,0f,textPaint)

        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun startRotating() {
        launchJob = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(20)
                mAngle += 1f
                invalidate()
            }
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pauseRotating() {
        launchJob.cancel()
    }


    fun Float.toRadians() = this / 180 * PI.toFloat()

}