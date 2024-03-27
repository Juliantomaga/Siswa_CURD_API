package com.example.siswa_crud_api.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ListAdapter
import android.widget.TextView
import com.example.siswa_crud_api.EditActivity
import com.example.siswa_crud_api.MainActivity
import com.example.siswa_crud_api.R
import com.example.siswa_crud_api.model.SiswaModel
import com.example.siswa_crud_api.utils.Helper

class SiswaListAdapter(val context: Context,  val siswaList: MutableList<SiswaModel>) : BaseAdapter() {

    private val TAG = "SiswaListAdapter"

    override fun getCount(): Int = siswaList.size

    override fun getItem(p0: Int): Any = siswaList[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.siswa_item, p2, false)
        val resources = context.resources
        val siswa = siswaList[p0]
        val textID = view.findViewById<TextView>(R.id.textID)
        val textnama = view.findViewById<TextView>(R.id.textnama)
        val textjk = view.findViewById<TextView>(R.id.textjeniskelamin)
        val textlamat = view.findViewById<TextView>(R.id.textalamat)

        textID.text  = resources.getString(R.string.id, siswa.id)
        textnama.text  = resources.getString(R.string.nama, siswa.nama)
        textjk.text  = resources.getString(R.string.jeniskelamin, siswa.jeniskelamin)
        textlamat.text  = resources.getString(R.string.alamat, siswa.alamat)


        val remove=view.findViewById<ImageButton>(R.id.btnRemove)
        val edit =view.findViewById<ImageButton>(R.id.btnEdit)
        remove.setOnClickListener {
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Delete")
            alertDialog.setMessage("yakin nih mau hapus?")
            alertDialog.setPositiveButton("Yes"){dialog , id->
                Helper.Delete(siswa.id)
                (context as Activity).finish()
                context.startActivity(Intent(context, MainActivity::class.java))
            }


            alertDialog.setNegativeButton("No") {dialog, id ->
                dialog.dismiss()
            }

            alertDialog.show()
        }


        edit.setOnClickListener {
            val intent = Intent(context, EditActivity::class.java)
            val id = siswa.id
            intent.putExtra("id", id)
            context.startActivity(intent)
        }


        return  view

    }
}