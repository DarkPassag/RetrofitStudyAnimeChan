package studyingPagination3.tempproj.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ch.ni.an.tempproj.R
import studyingPagination3.tempproj.model.room.AnimeQuote

class AnimeAdapter : PagingDataAdapter<AnimeQuote, AnimeAdapter.AnimeHolder>(animeDiffCallack()){



    override fun onCreateViewHolder(parent :ViewGroup, viewType :Int) :AnimeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return AnimeHolder(view)
    }

    class AnimeHolder(view:View): RecyclerView.ViewHolder(view){

        fun bind(animeQuote :AnimeQuote){
            val textView = itemView.findViewById<TextView>(R.id.textView)
            val textViewName = itemView.findViewById<TextView>(R.id.characterName)
            textViewName.text = animeQuote.character
            textView.text = animeQuote.quote

        }
    }

    override fun onBindViewHolder(holder :AnimeHolder, position :Int) {
        holder.bind(getItem(position)!!)
    }

    class animeDiffCallack: DiffUtil.ItemCallback<AnimeQuote>() {
        override fun areItemsTheSame(oldItem :AnimeQuote, newItem :AnimeQuote) :Boolean {
            return oldItem.quote == newItem.quote
        }

        override fun areContentsTheSame(oldItem :AnimeQuote, newItem :AnimeQuote) :Boolean {
            return oldItem == newItem
        }
    }

}

