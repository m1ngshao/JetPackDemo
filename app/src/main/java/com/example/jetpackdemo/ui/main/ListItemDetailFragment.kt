package com.example.jetpackdemo.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackdemo.databinding.FragmentListItemDetailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [ListItemDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListItemDetailFragment : Fragment() {

    private var _binding : FragmentListItemDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : ListItemDetailViewModel


    companion object{
        fun newInstance(): ListItemDetailFragment{
            val args = Bundle()
            val fragment = ListItemDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        (activity as MainActivity).hideBottomNavigation()
        viewModel = ViewModelProvider(this)[ListItemDetailViewModel::class.java]
        _binding = FragmentListItemDetailBinding.inflate(inflater, container, false)
        binding.detailToolbarBack.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.popBackStack()
            (activity as MainActivity).showBottomNavigation()
        }
        val des = arguments?.getString("message")
        binding.detailTv.text = if(des.equals("")) "此视频没有描述信息" else des
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}