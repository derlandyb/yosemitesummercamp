package br.com.derlandybelchior.yosemitesummercamp.adapter

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.derlandybelchior.yosemitesummercamp.R
import br.com.derlandybelchior.yosemitesummercamp.domain.MenuItem
import br.com.derlandybelchior.yosemitesummercamp.domain.MenuItemStaus

class MenuViewHolder(
    val adataper: MenuAdapter,
    val changeButtonStatusCallback: (List<MenuItem>) -> Unit,
    itemView: View
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var cvIcon: CardView
    private var ivSelected: ImageView
    private var vvGradient: View
    private var ivIcon: ImageView
    private var tvLabel: TextView

    init {
        itemView.setOnClickListener(this)

        cvIcon = itemView.findViewById(R.id.cv_icon)
        ivSelected = itemView.findViewById(R.id.iv_selected)
        vvGradient = itemView.findViewById(R.id.vv_gradient)
        ivIcon = itemView.findViewById(R.id.iv_icon)
        tvLabel = itemView.findViewById(R.id.tv_label)
    }

    fun setModel(item: MenuItem) {
        tvLabel.text = item.label
        ivIcon.setImageResource(item.icon)
        ivIcon.contentDescription = item.label

        setStyle(item = item)
    }

    private fun setStyle(item: MenuItem) {
        var cvBackgroundResource = R.drawable.cv_background_normal
        var ivSelectedVisibility = View.INVISIBLE
        var vvGradientVisibility = View.VISIBLE
        var tvLabelColor = R.color.colorDarkGrey
        var ivIconColor = ResourcesCompat.getColor(
            adataper.context.resources,
            R.color.colorDarkPurple,
            null)

        if(item.isSelected == MenuItemStaus.SELECTED) {
            cvBackgroundResource = R.drawable.cv_background_selected
            ivSelectedVisibility = View.VISIBLE
            vvGradientVisibility = View.INVISIBLE
            ivIconColor = Color.WHITE
            tvLabelColor = R.color.colorDarkPurple
        }

        cvIcon.setBackgroundResource(cvBackgroundResource)
        ivSelected.visibility = ivSelectedVisibility
        vvGradient.visibility = vvGradientVisibility
        ivIcon.setColorFilter(ivIconColor, PorterDuff.Mode.SRC_IN)

        tvLabel.setTextColor(
            ResourcesCompat.getColor(
                adataper.context.resources,
                tvLabelColor,
            null
            )
        )
    }

    override fun onClick(p0: View?) {
        with(adataper.items [adapterPosition]) {
            this.isSelected = when(this.isSelected) {
                MenuItemStaus.SELECTED -> MenuItemStaus.NOT_SELECTED
                else -> MenuItemStaus.SELECTED
            }
        }
        adataper.notifyItemChanged(adapterPosition)
        changeButtonStatusCallback(adataper.items)
    }
}