package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nhan.gk_22it201.R
import models.SPs

interface StudentAdapterListener {
    fun onStudentClick(student: SPs)
}

interface StudentButtonRemoveListener {
    fun onStudentRemove(student: SPs)
}

class SPAdapter(
    private val sPs: ArrayList<SPs>,
    private val listener: StudentAdapterListener,
    private val buttonRemoveListener: StudentButtonRemoveListener
) : RecyclerView.Adapter<SPAdapter.SPViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SPViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.sp_info_item, parent, false)

        return SPViewHolder(view)

    }

    override fun getItemCount(): Int {
        return sPs.size
    }

    override fun onBindViewHolder(holder: SPViewHolder, position: Int) {
        val sPs = sPs[position]

        holder.sttTextView.text = sPs.stt.toString()
        holder.tenTextView.text = sPs.ten
        holder.soLuongTextView.text = sPs.soluong
        holder.itemView.setOnClickListener {
            listener.onStudentClick(sPs)
        }
        holder.itemView.findViewById<View>(R.id.removeButton).setOnClickListener {
            buttonRemoveListener.onStudentRemove(sPs)
        }
    }

    class SPViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sttTextView: TextView = itemView.findViewById(R.id.stt)
        var tenTextView: TextView = itemView.findViewById(R.id.Ten)
        var soLuongTextView: TextView = itemView.findViewById(R.id.SoLuong)
    }

}