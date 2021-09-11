package com.example.registration_two.models

import com.google.gson.annotations.SerializedName

data class Courses(
    @SerializedName("couse_id") var courseId:String,
    @SerializedName("course_name") var courseName:String,
    @SerializedName("course_code") var courseCode:String,
    var description:String,
    var instructors:String
)
