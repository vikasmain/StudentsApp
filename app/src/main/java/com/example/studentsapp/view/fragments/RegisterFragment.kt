package com.example.studentsapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.studentsapp.R
import com.example.studentsapp.databinding.RegisterFragmentBinding

class RegisterFragment : Fragment() {

    lateinit var registerFragmentBinding: RegisterFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registerFragmentBinding = RegisterFragmentBinding.inflate(layoutInflater)
        registerFragmentBinding.button2.setOnClickListener {
            findNavController().navigate(R.id.action_register_to_login)
        }
        return registerFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
