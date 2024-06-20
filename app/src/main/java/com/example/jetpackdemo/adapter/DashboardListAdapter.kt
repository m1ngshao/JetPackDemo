package com.example.jetpackdemo.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpackdemo.Bean.ContentBean
import com.example.jetpackdemo.R



class DashboardListAdapter(private val contentBeanList: ArrayList<ContentBean>) : RecyclerView.Adapter<DashboardListAdapter.MyViewHolder>() {
    private var listener : ((contentBean: ContentBean) ->Unit?)? = null

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView : ImageView = itemView.findViewById(R.id.dash_item_img)
        val title : TextView = itemView.findViewById(R.id.dash_item_title)
        val owner : TextView = itemView.findViewById(R.id.dash_item_owner)
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
        holder.owner.text = contentBean.owner.name
        holder.itemView.setOnClickListener {
            listener?.invoke(contentBeanList[position])
        }

    }

    fun setListener(listener : ((contentBean: ContentBean) ->Unit)){
        this.listener = listener
    }
}