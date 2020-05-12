package com.mistong.jstudy

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.LayoutInflaterCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private var count = 0
    private val myLock = MyLock()
    override fun onCreate(savedInstanceState: Bundle?) {
        LayoutInflaterCompat.setFactory2(layoutInflater, object : LayoutInflater.Factory2 {
            override fun onCreateView(
                parent: View?,
                name: String,
                context: Context,
                attrs: AttributeSet
            ): View? {
                val view = delegate.createView(parent, name, context, attrs)
                when (view) {
                    is AppCompatTextView -> {
                        view.text = "去你妈的"
                    }
                }
                return view
            }

            override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
                return onCreateView(null, name, context, attrs)
            }

        })
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv.setOnClickListener {
            if (count > 0) {
                Toast.makeText(this, "你好小米", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "你好小万", Toast.LENGTH_SHORT).show()
            }
        }
        
        threadPool()
    }

    private fun threadPool() {
        val threadPool = ThreadPoolExecutor(
            2, 300, 60L, TimeUnit.SECONDS,
            LinkedBlockingQueue(2),
            Executors.defaultThreadFactory(),
            ThreadPoolExecutor.DiscardPolicy()
        )

        threadPool.execute {
            try {
                Thread.sleep(2000)
            } catch (e: Exception) {

            }

        }
        Log.e("线程池", "等待队列中有${threadPool.queue.size}个")
    }

    private fun testThread() {
        var x = 0
        var y = 0
        val p1 = Thread(Runnable {
            val r1 = x
            y = 1
            Log.e("德玛", "r1的值${r1.toString()}")
        })

        val p2 = Thread(Runnable {
            val r2 = y
            x = 2
            Log.e("德玛", "r2的值${r2.toString()}")
        })
        p1.start()
        p2.start()
        p1.join()
        p2.join()
    }

    private fun testReadWrite() {
        val t1 = Thread(ReadWrite.Reader(), "读线程1")
        val t2 = Thread(ReadWrite.Reader(), "读线程2")
        val t3 = Thread(ReadWrite.Writer(), "写线程")
        t1.start()
        t2.start()
        t3.start()
    }

    fun testLagou() {
        val l1 = LagouSynchronizedMethods()
        val l2 = LagouSynchronizedMethods()
        val t1 = Thread(Runnable {
            l1.printLog2()
        })

        val t2 = Thread(Runnable {
            l2.printLog2()
        })
        t1.start()
        t2.start()
    }


    fun testAQS() {
        val runnable = Runnable {
            try {
                myLock.lock()
                for (index in 0 until 10000) {
                    count++
                }
            } catch (e: Exception) {

            } finally {
                myLock.unlock()
            }
        }

        val thread1 = Thread(runnable)
        val thread2 = Thread(runnable)
        thread1.start()
        thread2.start()
        thread1.join()
        thread2.join()
        Log.e("德玛", count.toString())
    }
}
