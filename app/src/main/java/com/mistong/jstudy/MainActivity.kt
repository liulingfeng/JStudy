package com.mistong.jstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dalvik.system.DexClassLoader
import dalvik.system.PathClassLoader
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var count = 0
    private val myLock = MyLock()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val classLoader = DexClassLoader("", "", null, classLoader)
        val pathClassLoader = PathClassLoader("", "", classLoader)
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
