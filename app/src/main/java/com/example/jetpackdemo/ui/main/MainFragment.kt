package com.example.jetpackdemo.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jetpackdemo.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var binding: FragmentMainBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = FragmentMainBinding.inflate(inflater,container,false)
        binding!!.mainfragmentResult.text = viewModel.getResult().toString()
        binding!!.convertbtn.setOnClickListener {
            if(binding!!.dollartext.text.isNotEmpty()){
                viewModel.setAmount(binding!!.dollartext.text.toString())
                binding!!.mainfragmentResult.text = viewModel.getResult().toString()
            } else {
                binding!!.mainfragmentResult.text = "No Value"
            }
        }
        val view  = binding!!.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}