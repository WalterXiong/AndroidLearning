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
import com.androidlearning.jetpacktest.lifecyclestest.MyObserver
import com.androidlearning.jetpacktest.roomtest.AppDatabase
import com.androidlearning.jetpacktest.viewmodeltest.MainViewModel
import com.androidlearning.jetpacktest.viewmodeltest.MainViewModelFactory
import com.androidlearning.jetpacktest.viewmodeltest.User
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
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
    }
}