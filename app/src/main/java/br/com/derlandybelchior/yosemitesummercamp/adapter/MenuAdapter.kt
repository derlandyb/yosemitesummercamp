package br.com.derlandybelchior.yosemitesummercamp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.derlandybelchior.yosemitesummercamp.R
import br.com.derlandybelchior.yosemitesummercamp.domain.MenuItem

class MenuAdapter(
    val context: Context,
    val items: List<MenuItem>,
    private val changeButtonStatusCallback: (List<MenuItem>) -> Unit
) : RecyclerView.Adapter<MenuViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val layout = LayoutInflater.from(context)
            .inflate(R.layout.menu_item, parent, false)

        return MenuViewHolder(
            adataper = this,
            changeButtonStatusCallback = changeButtonStatusCallback,
            itemView = layout
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.setModel(item = items[position])
    }
}