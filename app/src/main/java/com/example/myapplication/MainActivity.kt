package com.example.myapplication

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {


    private var largeArrayList: ArrayList<MainActivity.someObjectWithStrings> = arrayListOf()

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        for (i in 1 .. 100000) {
            largeArrayList.add(someObjectWithStrings(
                Random.nextBytes(size = 50).toString(),
                Random.nextBytes(size = 60).toString(),
                Random.nextBytes(size = 70).toString(),
                Random.nextBytes(size = 80).toString(),
                Random.nextInt()
            ))
        }

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }

        binding.btForEach.setOnClickListener {
            var time = measureTimeMillis {
                var newArrayList: ArrayList<Int> = arrayListOf()
                largeArrayList.forEach {
                    newArrayList.add(it.Integer1)
                }
                Log.i("forEach", newArrayList.toString())
            }

            binding.textView.setText("Time taken by forEach is $time ms")
        }

        binding.button2.setOnClickListener {
            var time = measureTimeMillis {
                var newArrayList = largeArrayList.map {
                    it.Integer1
                }

                Log.i("map", newArrayList.toString())
            }

            binding.textView.setText("Time taken by map is $time ms")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    inner class someObjectWithStrings(
        var String1: String,
        var String2: String,
        var String3: String,
        var String4: String,
        var Integer1: Int
    )
}