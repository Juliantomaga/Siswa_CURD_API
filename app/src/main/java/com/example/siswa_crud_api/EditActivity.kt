package com.example.siswa_crud_api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.siswa_crud_api.databinding.ActivityEditBinding
import com.example.siswa_crud_api.model.SiswaModel
import com.example.siswa_crud_api.utils.Constans
import com.example.siswa_crud_api.utils.Helper
import org.json.JSONObject
import java.util.Objects

class EditActivity : AppCompatActivity() {

    private val TAG = "EditActivity"
    lateinit var binding : ActivityEditBinding


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.btmEdit.setOnClickListener {

        }

        if (intent.hasExtra("id")) {
            Log.d(TAG, "OnCrate : Punya")
            val id = intent.getStringExtra("id").toString()
            Helper.GetAt(id, Constans.siswa)
            setupUI(Constans.siswa)
            binding.btmEdit.setOnClickListener {
                val jsonObject = JSONObject()
                jsonObject.put("id", id)
                jsonObject.put("nama", binding.editnama.text)
                jsonObject.put("jeniskelamin", binding.editjk.text)
                jsonObject.put("alamat", binding.editalamat.text)
                val jsonObjectString = jsonObject.toString()
                Helper.Put(jsonObjectString)
                finish()
                Log.d(TAG, "OnCreate : ${jsonObjectString}")
                startActivity(Intent(this, MainActivity::class.java))
            }
        } else {
            binding.btmEdit.setOnClickListener {
                Toast.makeText(this, "Siswa ga ketemu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupUI(siswa: MutableList<SiswaModel>) {
        binding.editnama.setText(siswa.first().nama)
        binding.editjk.setText(siswa.first().jeniskelamin)
        binding.editalamat.setText(siswa.first().alamat)
    }
}