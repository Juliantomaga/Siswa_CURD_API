package com.example.siswa_crud_api.utils

import android.util.Log
import com.example.siswa_crud_api.model.SiswaModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class Helper {
    companion object Helper{
        private val TAG = "Helper"
        fun Put(jsonString: String) = runBlocking {
            launch(Dispatchers.IO){
                val conn = URL(Constans.url).openConnection() as HttpURLConnection
                conn.doOutput = true
                conn.requestMethod = "PUT"
                conn.setRequestProperty("Accept", "application/json")
                conn.setRequestProperty("Content-type", "application/json")

                val outputStream = OutputStreamWriter(conn.outputStream)
                outputStream.write(jsonString)
                outputStream.flush()
                Log.d(TAG, "Put: ${conn.inputStream}")
            }
        }


        fun Delete(id : String) = runBlocking {
            launch(Dispatchers.IO){
                val conn = URL(Constans.url+"/"+id).openConnection() as HttpURLConnection
                conn.doInput = true
                conn.requestMethod = "DELETE"
                conn.doOutput = false

                if(conn.responseCode == 200) {

                }

            }
        }


        fun Post(jsonString: String) = runBlocking {
            launch(Dispatchers.IO){
                val conn = URL(Constans.url).openConnection() as HttpURLConnection
                conn.doInput = true
                conn.doOutput = true
                conn.requestMethod  = "POST"
                conn.setRequestProperty("Accept", "application/json")
                conn.setRequestProperty("Content-type", "application/json")

                val outputStream = OutputStreamWriter(conn.outputStream)
                outputStream.write(jsonString)
                outputStream.flush()
                if(conn.responseCode == 200) {
                } else {

                }
            }
        }

        fun Get(siswa : MutableList<SiswaModel>) = runBlocking {
            launch(Dispatchers.IO){
                val conn = URL(Constans.url).openConnection() as HttpURLConnection
                val jsonString = conn.inputStream.bufferedReader().readText()
                val jsonArray = JSONArray(jsonString)
                siswa.clear()
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    siswa.add(SiswaModel(
                        jsonObject.getString("id"),
                        jsonObject.getString("nama"),
                        jsonObject.getString("jeniskelamin"),
                        jsonObject.getString("alamat")
                    ))
                }
            }
        }

        fun GetAt(id : String, siswa: MutableList<SiswaModel>) = runBlocking {
            launch(Dispatchers.IO){
                val conn = URL(Constans.url+"/"+id).openConnection() as HttpURLConnection
                val jsonString = conn.inputStream.bufferedReader() .readText()
                val jsonObject = JSONObject(jsonString)
                siswa.clear()
                siswa.add(
                    SiswaModel(
                        jsonObject.getString("id"),
                        jsonObject.getString("nama"),
                        jsonObject.getString("jeniskelamin"),
                        jsonObject.getString("alamat")
                )
                )
            }
        }
    }
}