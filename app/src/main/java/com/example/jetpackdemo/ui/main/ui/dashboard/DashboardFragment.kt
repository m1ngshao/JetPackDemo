package com.example.jetpackdemo.ui.main.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.GONE
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.VISIBLE
import com.example.jetpackdemo.ContentBean
import com.example.jetpackdemo.databinding.FragmentDashboardBinding
import com.example.jetpackdemo.ui.main.MyAdapter

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel : DashboardViewModel
    private lateinit var madapter : MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this,ViewModelFactory(ApiHelper(RetrofitBuilder.apiService)))
            .get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        madapter = MyAdapter(arrayListOf())
        binding.dashRecyclerView.adapter = madapter
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
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun retrieveList(contentBeans: List<ContentBean>){
        madapter.apply {
            addContentBean(contentBeans)
            notifyDataSetChanged()
        }
    }
}