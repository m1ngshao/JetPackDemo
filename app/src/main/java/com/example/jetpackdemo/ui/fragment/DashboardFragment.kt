package com.example.jetpackdemo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.GONE
import androidx.recyclerview.widget.RecyclerView.VISIBLE
import com.example.jetpackdemo.Bean.ContentBean
import com.example.jetpackdemo.R
import com.example.jetpackdemo.api.ApiHelper
import com.example.jetpackdemo.api.RetrofitBuilder
import com.example.jetpackdemo.api.Status
import com.example.jetpackdemo.databinding.FragmentDashboardBinding
import com.example.jetpackdemo.ui.main.ListItemDetailFragment
import com.example.jetpackdemo.adapter.DashboardListAdapter
import com.example.jetpackdemo.viewmodel.DashboardViewModel
import com.example.jetpackdemo.viewmodel.ViewModelFactory

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel : DashboardViewModel
    private lateinit var mAdapter : DashboardListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService,null)))
            .get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        mAdapter = DashboardListAdapter(arrayListOf())
        binding.dashRecyclerView.adapter = mAdapter
        binding.dashRecyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.getPrecious().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when(resource.status){
                   Status.LOADING ->{
                       binding.dashRecyclerView.visibility = GONE
                   }
                    Status.SUCCESS ->{
                        binding.dashRecyclerView.visibility = VISIBLE
                        resource.data?.let { preciousBean ->  retrieveList(preciousBean.data.list)}
                    }
                    Status.ERROR ->{
                        binding.dashRecyclerView.visibility = GONE
                        Toast.makeText(context,resource.message,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        mAdapter.setListener {
            val aid = it.aid
            val bundle = bundleOf(
                Pair("message",aid)
            )
            val fragmentManager = activity?.supportFragmentManager
            val listItemDetailFragment = ListItemDetailFragment.newInstance()
            listItemDetailFragment.arguments = bundle
            fragmentManager?.beginTransaction()?.replace(R.id.main_fragment_container,listItemDetailFragment)
                ?.addToBackStack(null)?.commit()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun retrieveList(contentBeans: List<ContentBean>){
        mAdapter.apply {
            addContentBean(contentBeans)
            notifyDataSetChanged()
        }
    }
}