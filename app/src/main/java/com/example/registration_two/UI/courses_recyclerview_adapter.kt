package com.example.registration_two

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.registration_two.models.Courses

class courses_recyclerview_adaptervar ( var courseList: List<Courses>):RecyclerView.Adapter<Courses_ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Courses_ViewHolder {
        var itemView=
            LayoutInflater.from(parent.context).inflate(R.layout.courses_list_item,parent,false)
        return Courses_ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: Courses_ViewHolder, position: Int) {
        var currentCourse=courseList.get(position)
        holder.tvcourseName.text=currentCourse.courseName
        holder.tvdescription.text=currentCourse.description
        holder.tvCourseCode.text=currentCourse.courseCode
        holder.tvInstructor.text=currentCourse.instructors
    }

    override fun getItemCount(): Int {
        return courseList.size
    }
}
class Courses_ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    var tvcourseName=itemView.findViewById<TextView>(R.id.tvcourseName)
    var tvdescription=itemView.findViewById<TextView>(R.id.tvdescription)
    var tvCourseCode=itemView.findViewById<TextView>(R.id.tvCourseCode)
    var tvInstructor=itemView.findViewById<TextView>(R.id.tvInstructor)
}
