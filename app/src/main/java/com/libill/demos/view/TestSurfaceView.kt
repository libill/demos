package com.libill.demos.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.util.Random


class TestSurfaceView(context: Context) : SurfaceView(context), SurfaceHolder.Callback, Runnable {

    private val mSurfaceHolder by lazy { holder }
    private val mPaint = Paint()
    private lateinit var mCanvas: Canvas
    private var workerThread: Thread? = null
    private var workerThreadRunning = false

    init {
        mSurfaceHolder.addCallback(this)
        mPaint.color = Color.RED
        mPaint.strokeWidth = 10f
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        workerThread = Thread(this)
        workerThread?.start()
        workerThreadRunning = true
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        //TODO: 根据需要调整View的大小或位置
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        //设置标志位为false，表示子线程可以停止运行
        workerThreadRunning = false
        try {
            //等待子线程结束，并释放子线程对象
            workerThread?.join()
            workerThread = null
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    override fun run() {
        while (workerThreadRunning) {
            val start = System.currentTimeMillis()

            //调用draw方法进行绘制操作
            draw()

            //获取结束时间，用于计算绘制时间
            val end = System.currentTimeMillis()

            //如果绘制时间小于16ms，则延时一段时间，保证每秒60帧的刷新率
            if (end - start < 16) {
                try {
                    Thread.sleep(16 - (end - start))
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }


    //获取canvas对象，并通过lockCanvas和unlockCanvasAndPost方法进行绘制操作
    private fun draw() {
        try {
            //通过lockCanvas方法获取canvas对象，如果surface不可用，则返回null
            mCanvas = mSurfaceHolder.lockCanvas()
            if (mCanvas != null) {
                //TODO: 在canvas上进行绘制操作，例如画线、画圆、画文字等

                //在本例中，我们简单地使用随机数生成一些坐标点，并用画笔连接它们，形成一条折线图

                //生成一个随机数对象

                val random: Random = Random()
                //生成一个点的集合，用于存储坐标点
                val points: MutableList<Point> = ArrayList()
                //循环生成10个随机坐标点，并添加到集合中
                for (i in 0..9) {
                    val x: Int = random.nextInt(mCanvas.getWidth())
                    val y: Int = random.nextInt(mCanvas.getHeight())
                    points.add(Point(x.toFloat(), y.toFloat()))
                }
                //遍历点的集合，用画笔连接相邻的两个点，形成一条折线图
                for (i in 0 until points.size - 1) {
                    val p1 = points[i]
                    val p2 = points[i + 1]
                    mCanvas.drawLine(p1.x, p1.y, p2.x, p2.y, mPaint)
                }

                //通过unlockCanvasAndPost方法将绘制好的内容显示到屏幕上，并释放canvas对象
                mSurfaceHolder.unlockCanvasAndPost(mCanvas)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}