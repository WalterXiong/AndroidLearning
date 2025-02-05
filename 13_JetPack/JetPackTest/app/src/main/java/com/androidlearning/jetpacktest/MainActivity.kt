package com.androidlearning.jetpacktest

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.androidlearning.jetpacktest.lifecyclestest.MyObserver
import com.androidlearning.jetpacktest.roomtest.AppDatabase
import com.androidlearning.jetpacktest.viewmodeltest.MainViewModel
import com.androidlearning.jetpacktest.viewmodeltest.MainViewModelFactory
import com.androidlearning.jetpacktest.viewmodeltest.User
import com.androidlearning.jetpacktest.workmanagertest.SimpleWork
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var infoText: TextView
    lateinit var nameText: TextView
    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        infoText = findViewById<TextView>(R.id.infoText)
        nameText = findViewById<TextView>(R.id.nameText)
        val plusOneBtn = findViewById<Button>(R.id.plusOneBtn)
        val clearBtn = findViewById<Button>(R.id.clearBtn)
        val set100Btn = findViewById<Button>(R.id.set100Btn)
        val getUserBtn = findViewById<Button>(R.id.getUserBtn)

        sp = getPreferences(MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)
        viewModel =
            ViewModelProvider(this, MainViewModelFactory(countReserved))[MainViewModel::class.java]

        plusOneBtn.setOnClickListener {
            viewModel.plusOne()
        }

        clearBtn.setOnClickListener {
            viewModel.clear()
        }

        set100Btn.setOnClickListener {
            viewModel.postSet(100)
            viewModel.createUser()
        }

        getUserBtn.setOnClickListener {
            val userId = (1..10000).random().toString()
            viewModel.getUser(userId)
        }

        viewModel.counter.observe(this) { count ->
            infoText.text = count.toString()
        }

        viewModel.userName.observe(this) { uName ->
            nameText.text = uName.toString()
        }

        viewModel.user.observe(this) { user ->
            infoText.text = user.firstName
        }

        lifecycle.addObserver(MyObserver(lifecycle))

        // =========================================================================================
        //                       ROOM
        // =========================================================================================

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom", "Brady", 40)
        val user2 = User("Tom", "Hanks", 63)

        findViewById<Button>(R.id.addDataBtn).setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }

        findViewById<Button>(R.id.updateDataBtn).setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }

        findViewById<Button>(R.id.deleteDataBtn).setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }

        }

        findViewById<Button>(R.id.queryDataBtn).setOnClickListener {
            thread {
                for (user in userDao.queryAllUser()) {
                    Log.d("MainActivity", user.toString())
                }
            }
        }

        // =========================================================================================
        //                       WorkManager
        // =========================================================================================
        findViewById<Button>(R.id.doWorkBtn).setOnClickListener {
            // 最简单的任务
            // val request = OneTimeWorkRequest.Builder(SimpleWork::class.java).build()

            val request = OneTimeWorkRequest.Builder(SimpleWork::class.java)
                // 让后台任务在指定的时间延迟后执行
                .setInitialDelay(5, TimeUnit.MINUTES)
                // 给后台任务添加标签
                .addTag("simple")
                /*
                 * setBackoffCriteria()方法接收3个参数：
                 * 第一个参数用于指定如果任务再次执行失败，下次重试的时间应该以什么样的形式延迟。
                 * 第二个和第三个参数用于指定在多久之后重新执行任务，时间最短不能少于10秒钟；
                 */
                .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
                .build()

            // 通过标签停用所有打了该标签的任务
            WorkManager.getInstance(this).cancelAllWorkByTag("simple")
            // 通过 Id 停用该任务
            WorkManager.getInstance(this).cancelWorkById(request.id)
            // 停用所有后台任务
            WorkManager.getInstance(this).cancelAllWork()

            WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(request.id)
                .observe(this) { workInfo ->
                    workInfo?.let {
                        if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                            Log.d("MainActivity", "do work succeeded")
                        } else if (workInfo.state == WorkInfo.State.FAILED) {
                            Log.d("MainActivity", "do work failed")
                        }
                    }
                }

            /*
             * 链式任务的执行
             */
            val sync = OneTimeWorkRequest.Builder(SimpleWork::class.java).build()
            val compress = OneTimeWorkRequest.Builder(SimpleWork::class.java).build()
            val upload = OneTimeWorkRequest.Builder(SimpleWork::class.java).build()
            WorkManager.getInstance(this)
                .beginWith(sync)
                .then(compress)
                .then(upload)
                .enqueue()
        }
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
    }
}