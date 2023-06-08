package com.ajoudb.ajouwiki.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ajoudb.ajouwiki.R
import com.ajoudb.ajouwiki.network.wikiDetail.WikiDetailResponseBody

/* Todo: WikiDetailResponse 정의 및 알맞게 바꾸기 */

class WikiDetailAdapter internal constructor(var wikiDetail: List<WikiDetailResponseBody>)
    : RecyclerView.Adapter<WikiDetailAdapter.ListViewHolder>() {


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(_list: WikiDetailResponseBody) {

            itemView.findViewById<TextView>(R.id.wiki_detail_title).text = _list.wiki[0].name

            itemView.findViewById<TextView>(R.id.wikiText).text = _list.wiki[0].tags?.name

        }
    }
    override fun getItemCount(): Int = wikiDetail.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    // ViewHolder를 생성해 반환한다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            // 새로운 뷰를 생성해 뷰홀더에 인자로 넣어준다.
            LayoutInflater.from(parent.context).inflate(R.layout.wiki_detail_list, parent, false)
        )
    }

    // 반환된 ViewHolder에 데이터를 연결한다.
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(wikiDetail[position])
        holder.itemView.setOnClickListener{
            itemClickListner.onClick(it,position)
        }
    }

    interface ItemClickListener{
        fun onClick(view: View,position: Int)
    }
    //를릭 리스너
    private lateinit var itemClickListner: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }

}