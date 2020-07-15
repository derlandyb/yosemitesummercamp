package br.com.derlandybelchior.yosemitesummercamp

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import br.com.derlandybelchior.yosemitesummercamp.adapter.MenuAdapter
import br.com.derlandybelchior.yosemitesummercamp.data.Menu
import br.com.derlandybelchior.yosemitesummercamp.domain.MenuItemStaus
import kotlinx.android.synthetic.main.content_intinerary.*

class IntineraryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intinerary)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        initIntinerary()
    }

    private fun initIntinerary() {
        val layoutManager = GridLayoutManager(
            this,
            Menu.NUMBER_COLUMNS
        )

        rv_menu_items.layoutManager = layoutManager
        rv_menu_items.setHasFixedSize(true)
        rv_menu_items.adapter = MenuAdapter(
            context = this,
            items = Menu.getItems(),
            changeButtonStatusCallback = {
                items -> changeButtonStatus(items)
            }
        )
    }

    private fun changeButtonStatus(items: List<br.com.derlandybelchior.yosemitesummercamp.domain.MenuItem>) {
        var isEnabled = false
        var backgroundId = R.color.colorMediumGrey

        val status = items.any {
            it.isSelected == MenuItemStaus.SELECTED
        }

        if(status) {
            isEnabled = true

            backgroundId = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                R.drawable.bt_orange_ripple
            } else {
                R.color.colorAccent
            }
        }

        bt_continue.isEnabled  = isEnabled
        bt_continue.background = ResourcesCompat.getDrawable(
            resources,
            backgroundId,
            null
        )
    }
}