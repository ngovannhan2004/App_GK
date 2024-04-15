package com.nhan.gk_22it201

import adapters.StudentAdapter
import adapters.StudentAdapterListener
import adapters.StudentButtonRemoveListener
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nhan.gk_22it201.databinding.ActivityMainBinding
import helper.Helper
import models.Student


public lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity(), StudentAdapterListener, StudentButtonRemoveListener {

    private lateinit var editTextMsv: EditText
    private lateinit var editTextHoTen: EditText
    private lateinit var showEmailCreate: TextView  // Khởi tạo biến
    private lateinit var buttonTaoEmail: Button
    private lateinit var buttonXem: Button
    private lateinit var recyclerView: RecyclerView
    private var stateHideRecyclerView = true
    private val students = ArrayList<Student>()
    private val studentAdapter = StudentAdapter(students, this, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editTextMsv = binding.editTextMsv
        editTextMsv.setText("22IT220")
        editTextHoTen = binding.editTextHoTen
        editTextHoTen.setText("Ngô Văn Nhân")
        showEmailCreate = binding.showEmailCreate
        buttonTaoEmail = binding.buttonTaoEmail
        buttonXem = binding.buttonXem
        recyclerView = binding.recyclerView

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = studentAdapter
        if (stateHideRecyclerView) {
            recyclerView.visibility = View.GONE
        }

        buttonTaoEmail.setOnClickListener {
            val msv = editTextMsv.text.toString()
            val hoTen = editTextHoTen.text.toString()
            if (validateInput(msv, hoTen)) {
                val name = Helper.normalizeName(hoTen)
                val email = Helper.createEmail(name, msv)
                showEmailCreate.text = "Email là: " + email
                val student = Student(name, msv, email)
                createNewStudent(student)
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

    private fun validateInput(msv: String, hoTen: String): Boolean {
        if (msv.isEmpty()) {
            showMessage("Vui lòng nhập mã sinh viên");
            return false
        }
        if (hoTen.isEmpty()) {
            showMessage("Vui lòng nhập họ tên");
            return false
        }
        return true
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onStudentClick(student: Student) {
    }

    override fun onStudentRemove(student: Student) {
        this.students.remove(student)
        studentAdapter.notifyDataSetChanged()
    }

    private fun checkExistByMsv(msv: String): Boolean {
        for (student in students) {
            if (student.msv == msv) {
                return true
            }
        }
        return false
    }

    private fun createNewStudent(student: Student) {
        if (!checkExistByMsv(student.msv)) {
            showMessage("Tạo sinh viên thành công")
            students.add(student)
            studentAdapter.notifyDataSetChanged()
        } else {
            showMessage("Sinh viên đã tồn tại")
        }
    }


}