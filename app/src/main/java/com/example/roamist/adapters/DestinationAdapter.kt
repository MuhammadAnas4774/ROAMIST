package com.example.roamist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roamist.R
import com.example.roamist.models.Destination

/**
 * [F3] Custom RecyclerView Adapter + ViewHolder for the Destination list.
 * [F5] Implements Filterable to support live, real-time search filtering.
 */
class DestinationAdapter(
    private val fullList: MutableList<Destination>,
    private val onItemClick: (Destination) -> Unit
) : RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder>(), Filterable {

    /** Working copy displayed in the RecyclerView — narrowed by filter queries */
    private var filteredList: MutableList<Destination> = fullList.toMutableList()

    // ─── [F3] ViewHolder ───────────────────────────────────────────────────────
    /**
     * Caches all View references for a single row to avoid repeated
     * findViewById calls on every bind pass.
     */
    inner class DestinationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEmoji:    TextView = itemView.findViewById(R.id.tv_dest_emoji)
        val tvName:     TextView = itemView.findViewById(R.id.tv_dest_name)
        val tvRegion:   TextView = itemView.findViewById(R.id.tv_dest_region)
        val tvCategory: TextView = itemView.findViewById(R.id.tv_dest_category)
        val tvRating:   TextView = itemView.findViewById(R.id.tv_dest_rating)
        val tvPrice:    TextView = itemView.findViewById(R.id.tv_dest_price)

        fun bind(destination: Destination) {
            tvEmoji.text    = destination.iconEmoji
            tvName.text     = destination.name
            tvRegion.text   = destination.region
            tvCategory.text = destination.category
            tvRating.text   = "★ ${destination.rating}"
            tvPrice.text    = "PKR ${destination.pricePerNight}/night"
            itemView.setOnClickListener { onItemClick(destination) }
        }
    }

    // ─── Adapter overrides ─────────────────────────────────────────────────────
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_destination_card, parent, false)
        return DestinationViewHolder(view)
    }

    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) =
        holder.bind(filteredList[position])

    override fun getItemCount(): Int = filteredList.size

    // ─── [F5] Filterable implementation ───────────────────────────────────────
    override fun getFilter(): Filter = object : Filter() {

        /** Runs on a background thread — performs the actual text matching */
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val query = constraint?.toString()?.trim()?.lowercase() ?: ""
            val results = FilterResults()
            results.values = if (query.isEmpty()) {
                fullList.toMutableList()
            } else {
                fullList.filter { dest ->
                    dest.name.lowercase().contains(query) ||
                    dest.region.lowercase().contains(query) ||
                    dest.category.lowercase().contains(query)
                }.toMutableList()
            }
            return results
        }

        /** Runs on the main thread — swaps the filtered list and notifies the adapter */
        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            filteredList = (results?.values as? MutableList<Destination>) ?: mutableListOf()
            notifyDataSetChanged()
        }
    }
}
