package com.example.homepage.profileTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.homepage.R
import com.example.homepage.superClass.ReplaceFragment
import com.google.firebase.auth.FirebaseAuth


class EditProfileFragment : ReplaceFragment() {

    private val user = FirebaseAuth.getInstance().currentUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        container?.removeAllViewsInLayout()

        val v = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        val email = user?.email.toString()
        setInformation(email)

        val userId = v.findViewById<TextView>(R.id.IdReal)
        val userName = v.findViewById<TextView>(R.id.userNameReal)
        val userFullName = v.findViewById<TextView>(R.id.fullNameReal)
        val userDept = v.findViewById<TextView>(R.id.departmentReal)
        val userSession = v.findViewById<TextView>(R.id.sessionReal)

        userId.text = getUserId()
        userFullName.text = getUserEmail()
        userDept.text = getDepartment()
        userSession.text = getSession()
        userName.text = getUserName()


        return v
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                // in here you can do logic when backPress is clicked
//                val manager: FragmentManager = requireActivity().supportFragmentManager
//                if(manager.backStackEntryCount > 0 ) {
//                    manager.popBackStack();//Pops one of the added fragments
//                }
//            }
//        })
//    }



}