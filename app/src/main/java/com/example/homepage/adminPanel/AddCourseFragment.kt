package com.example.homepage.adminPanel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homepage.courseTab.Model.CourseData
import com.example.homepage.databinding.FragmentAddCourseBinding
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class AddCourseFragment : ReplaceFragment() {
    private lateinit var _binding: FragmentAddCourseBinding
    private val binding get() = _binding
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        _binding = FragmentAddCourseBinding.inflate(inflater, container, false)
        database = Firebase.database.reference
        binding.addCourseButtonForm.setOnClickListener {
            val courseCode = binding.courseCode.text.toString()
            val courseName = binding.courseName.text.toString()
            val courseDriveLink = binding.courseDriveLinkText.text.toString()
            val department = binding.spinnerDepartmentList.selectedItem.toString()
            val year = binding.spinnerYearList.selectedItem.toString()
            val semester = binding.spinnerSemesterList.selectedItem.toString()
            if (department != "Department" && year != "Year" && semester != "Semester" && courseCode != "" && courseName != "" && courseDriveLink != "")
                writeNewCourse(
                    courseCode,
                    courseName,
                    courseDriveLink,
                    department,
                    "year" + year + "semester" + semester
                )
            else
                makeToast("Fill up all the fields")
        }
        return binding.root
    }

    private fun writeNewCourse(
        courseCode: String,
        courseName: String,
        courseDriveLink: String,
        department: String,
        yearSemester: String
    ) {

        val newCourse = CourseData(courseCode, courseName, courseDriveLink)
        val taskValues = newCourse.toMap()
        val childUpdates = hashMapOf<String, Any>(
            "/course-list/$department/$yearSemester/$courseCode" to taskValues
        )
        database.updateChildren(childUpdates)
    }


}