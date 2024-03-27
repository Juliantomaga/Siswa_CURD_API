package com.example.siswa_crud_api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.siswa_crud_api.databinding.ActivityInsertBinding
import com.example.siswa_crud_api.utils.Helper
import org.json.JSONObject

class InsertActivity : AppCompatActivity() {

    private val TAG = "InsertActivity"
    lateinit var binding : ActivityInsertBinding

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityInsertBinding.inflate(layoutInflater)
            setContentView(binding.root)
            setSupportActionBar(findViewById(R.id.toolbar))
            supportActionBar ?.setDisplayHomeAsUpEnabled(true)
            binding.btnTambah.setOnClickListener {
                val nama = binding.editNama.text.toString()
                val jeniskelamin = binding.editJeniskelamin.text.toString()
                val alamat = binding.editAlamat.text.toString()


                val jsonObject = JSONObject()
                jsonObject.put("nama", nama)
                jsonObject.put("jeniskelamin", jeniskelamin)
                jsonObject.put("alamat", alamat)

                val jsonObjectString =jsonObject.toString()
                Helper.Post(jsonObjectString)
                finish()
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }

    }
}