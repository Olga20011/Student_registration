package com.example.registration_two.models

import com.google.gson.annotations.SerializedName

data class EnrollmentRequest(
    @SerializedName("student_id") var student_id: String,
    @SerializedName("course_id") var course_id: String
)


