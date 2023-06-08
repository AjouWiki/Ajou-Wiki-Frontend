package com.ajoudb.ajouwiki.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ajoudb.ajouwiki.R
import com.ajoudb.ajouwiki.Wiki

/* Todo: WikiListResponse 정의 및 알맞게 바꾸기 */

class WikiListAdapter internal constructor(var wikiList: List<Wiki>)
        : RecyclerView.Adapter<WikiListAdapter.ListViewHolder>() {
        inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                fun bind(_list: Wiki) {

                        itemView.findViewById<TextView>(R.id.iv_name).text = _list.name!!

                        var hashtags: String=""
                        for(tag in _list.tags){
                                hashtags += "#"
                                hashtags += tag.name
                                hashtags += " "
                        }

                        if(hashtags.length>15) {
                                hashtags = hashtags.substring(0..15) + "..."
                        }

                        itemView.findViewById<TextView>(R.id.iv_tag).text = hashtags

                }
        }
        override fun getItemCount(): Int = wikiList.size

        override fun getItemViewType(position: Int): Int {
                return position
        }

        // ViewHolder를 생성해 반환한다.
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
                return ListViewHolder(
                        // 새로운 뷰를 생성해 뷰홀더에 인자로 넣어준다.
                        LayoutInflater.from(parent.context).inflate(R.layout.wiki_list, parent, false)
                )
        }

        // 반환된 ViewHolder에 데이터를 연결한다.
        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
                holder.bind(wikiList[position])
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