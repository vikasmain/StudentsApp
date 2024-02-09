package com.example.studentsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.studentsapp.R
import com.example.studentsapp.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    lateinit var loginFragmentBinding: LoginFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginFragmentBinding = LoginFragmentBinding.inflate(layoutInflater, container, false)
        loginFragmentBinding.button.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_home)
        }
        return loginFragmentBinding.root
    }
}
