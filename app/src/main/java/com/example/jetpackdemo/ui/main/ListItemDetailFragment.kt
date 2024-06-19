package com.example.jetpackdemo.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackdemo.Bean.DetailContentBean
import com.example.jetpackdemo.databinding.FragmentListItemDetailBinding
import com.example.jetpackdemo.ui.main.ui.dashboard.ApiHelper
import com.example.jetpackdemo.ui.main.ui.dashboard.RetrofitBuilder
import com.example.jetpackdemo.ui.main.ui.dashboard.Status
import com.example.jetpackdemo.ui.main.ui.dashboard.ViewModelFactory

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

        val clickAid = arguments?.getLong("message")
        if (clickAid != null) (activity as MainActivity).hideBottomNavigation()
        viewModel = ViewModelProvider(this,ViewModelFactory(ApiHelper(RetrofitBuilder.apiService, clickAid ?: DetailActivity.deepLinkAid)))
            .get(ListItemDetailViewModel::class.java)

        _binding = FragmentListItemDetailBinding.inflate(inflater, container, false)
        binding.detailToolbarBack.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.popBackStack()
            (activity as MainActivity).showBottomNavigation()
        }
        viewModel.getDetailContent().observe(viewLifecycleOwner, Observer { 
            it.let {resource -> 
                when(resource.status){
                    Status.LOADING ->{
                        
                    }
                    Status.SUCCESS ->{
                        resource.data.let { detailbean -> binding.detailTv.text = getDetailDesc(detailbean?.data) }

                    }
                    Status.ERROR ->{
                        Toast.makeText(context,resource.message, Toast.LENGTH_SHORT).show()
                    }
                }
                
            }
        })
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getDetailDesc(detailContentBean: DetailContentBean?) : String?{
        return if(detailContentBean?.desc == "") "此视频没有描述信息" else detailContentBean?.desc
    }
}