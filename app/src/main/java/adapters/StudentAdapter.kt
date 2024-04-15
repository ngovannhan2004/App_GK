package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nhan.gk_22it201.R
import models.Student

interface StudentAdapterListener {
    fun onStudentClick(student: Student)
}

interface StudentButtonRemoveListener {
    fun onStudentRemove(student: Student)
}

class StudentAdapter(
    private val students: ArrayList<Student>,
    private val listener: StudentAdapterListener,
    private val buttonRemoveListener: StudentButtonRemoveListener
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.student_info_item, parent, false)

        return StudentViewHolder(view)

    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {

        val student = students[position]

        holder.msvTextView.text = student.msv
        holder.hoTenTextView.text = student.ten
        holder.emailTextView.text = student.email

        holder.itemView.setOnClickListener {
            listener.onStudentClick(student)
        }
        holder.itemView.findViewById<View>(R.id.removeButton).setOnClickListener {
            buttonRemoveListener.onStudentRemove(student)
        }
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var msvTextView: TextView = itemView.findViewById(R.id.idStudent)
        var hoTenTextView: TextView = itemView.findViewById(R.id.nameStudent)
        var emailTextView: TextView = itemView.findViewById(R.id.emailStudent)


    }

}