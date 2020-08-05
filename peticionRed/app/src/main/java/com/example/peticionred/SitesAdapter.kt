package com.example.peticionred

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_site.view.*


class SitesAdapter(
    private val siteList: MutableList<Site> = mutableListOf(),
    private val onDetailClick: (Site) -> Unit
) :
    RecyclerView.Adapter<SitesAdapter.SiteHolder>() {

    override fun onBindViewHolder(holder: SiteHolder, position: Int) =
        holder.bind(siteList[position])


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_site, parent, false)
        return SiteHolder(
            itemView
        ) { onDetailClick(siteList[it]) }
    }

    override fun getItemCount(): Int = siteList.size

    fun addAll(sites: List<Site>) {
        siteList.addAll(sites)
        notifyDataSetChanged()
    }

    fun add(site: Site){
        siteList.add(site)
        notifyDataSetChanged()
    }

    class SiteHolder(
        itemView: View,
        private val onDetailClick: (Int) -> Unit = {}
    ) :
        RecyclerView.ViewHolder(itemView) {
        init {
            itemView.details.setOnClickListener { onDetailClick(adapterPosition) }
        }

        fun bind(siteView: Site) {
            itemView.title.text = siteView.title
            itemView.geocoordinates.text = siteView.geocoordinates
        }
    }

}
