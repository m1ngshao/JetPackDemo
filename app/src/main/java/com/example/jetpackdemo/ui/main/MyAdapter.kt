package com.example.jetpackdemo.ui.main
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpackdemo.ContentBean
import com.example.jetpackdemo.R



class MyAdapter(private val contentBeanList: ArrayList<ContentBean>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView : ImageView = itemView.findViewById(R.id.dash_item_img)
        val title : TextView = itemView.findViewById(R.id.dash_item_title)
        val desc : TextView = itemView.findViewById(R.id.dash_item_desc)
    }

    fun addContentBean(contentBeanList: List<ContentBean>){
        this.contentBeanList.apply {
            clear()
            addAll(contentBeanList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dash_list_item_view,parent,false)
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return MyViewHolder(view)
    }


    override fun getItemCount(): Int  = contentBeanList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contentBean = contentBeanList[position]

        Glide.with(holder.imageView.context)
            .load(contentBean.pic)
            .into(holder.imageView)

        holder.title.text = contentBean.title
        holder.desc.text = contentBean.des?:"我是默认值"
    }
}