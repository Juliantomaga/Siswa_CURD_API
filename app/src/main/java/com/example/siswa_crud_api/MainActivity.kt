package com.example.siswa_crud_api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.siswa_crud_api.adapter.SiswaListAdapter
import com.example.siswa_crud_api.databinding.ActivityMainBinding
import com.example.siswa_crud_api.utils.Constans
import com.example.siswa_crud_api.utils.Helper

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var binding : ActivityMainBinding

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, InsertActivity::class.java)
        startActivity(intent)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        Helper.Get(Constans.siswa)
        val adapter = SiswaListAdapter(this, Constans.siswa)
        binding.listView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "OnResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "OnPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "OnStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "OnDestroy called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "OnRestart called")
        Helper.Get(Constans.siswa)
    }
}