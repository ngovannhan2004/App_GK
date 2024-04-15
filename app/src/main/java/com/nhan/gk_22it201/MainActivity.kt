package com.nhan.gk_22it201

import adapters.SPAdapter
import adapters.StudentAdapterListener
import adapters.StudentButtonRemoveListener
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nhan.gk_22it201.databinding.ActivityMainBinding
import models.SPs


public lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity(), StudentAdapterListener, StudentButtonRemoveListener {
    private lateinit var edtSP: EditText
    private lateinit var edtSoLuong: EditText
    private lateinit var showSP: TextView  // Khởi tạo biến
    private lateinit var showSL: TextView  // Khởi tạo biến
    private lateinit var buttonThemSP: Button
    private lateinit var buttonXem: Button
    private lateinit var recyclerView: RecyclerView
    private var stateHideRecyclerView = true
    private val sPs = ArrayList<SPs>()
    private var counter = 1
    private var totalQuantity = 0
    private val spAdapter = SPAdapter(sPs, this, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        edtSP = binding.edtSP
        edtSoLuong = binding.edtSoLuong
        showSP = binding.showSP
        showSL = binding.showSL
        buttonThemSP = binding.buttonThemSP
        buttonXem = binding.buttonXem
        recyclerView = binding.recyclerView

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = spAdapter
        if (stateHideRecyclerView) {
            recyclerView.visibility = View.GONE
        }

        buttonThemSP.setOnClickListener {
            val ten = edtSP.text.toString()
            val soluong = edtSoLuong.text.toString()

            if (validateInput(ten, soluong)) {
                val quantity = soluong.toInt()
                totalQuantity += quantity
                showSP.text = "Tên SP là: " + ten
                showSL.text = " Tổng Số Lượng: " +totalQuantity
                val sp = SPs(counter, ten, soluong)
                createNewSP(sp)
                edtSP.text.clear()
                edtSoLuong.text.clear()
                counter++
            }
        }
        buttonXem.setOnClickListener {
            stateHideRecyclerView = !stateHideRecyclerView
            buttonXem.text = if (stateHideRecyclerView) "Xem" else "Ẩn"
            if (stateHideRecyclerView) {
                recyclerView.visibility = View.GONE
            } else {
                recyclerView.visibility = View.VISIBLE
            }
        }

    }

    private fun validateInput(ten: String, soluong: String): Boolean {
        if (ten.isEmpty()) {
            showMessage("Vui lòng nhập tên SP");
            return false
        }
        if (soluong.isEmpty()) {
            showMessage("Vui lòng nhập số lượng");
            return false
        }
        return true
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onStudentClick(student: SPs) {
    }

    override fun onStudentRemove(sPs: SPs) {
        this.sPs.remove(sPs)
        spAdapter.notifyDataSetChanged()

    }
    private fun createNewSP(sPs: SPs) {

        showMessage("Thêm SP thành công")
        this.sPs.add(sPs)
        spAdapter.notifyDataSetChanged()
    }


}