package com.e.tetris

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import java.util.*

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menu?.add(resources.getString(R.string.play_tetris))
        menu?.add(resources.getString(R.string.nor))
        menu?.add(resources.getString(R.string.eng))

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.toString()) {

            (resources.getString(R.string.play_tetris)) -> {startActivity(Intent(this, MainActivity::class.java))}
            (resources.getString(R.string.eng)) -> {
                changeLang(item.toString())
            }
            (resources.getString(R.string.nor)) -> {
                changeLang(item.toString())
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun changeLang(lang : String) {

        var locale = resources.configuration.locale

        if (lang == resources.getString(R.string.eng)) {
            locale = Locale("en", "EN")
        }
        else if (lang == resources.getString(R.string.nor)) {
            locale = Locale("no", "NO")
        }

        val config = Configuration()
        config.locale = locale;
        val res = baseContext.resources
        res.updateConfiguration(config, res.displayMetrics)

        val updateIntent = intent
        finish()
        startActivity(updateIntent)

    }
}
