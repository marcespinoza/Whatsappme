package com.whatsapp.me

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.view.View
import android.widget.EditText


class actividad_principal : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var codigo: EditText
    private lateinit var numero: EditText
    private lateinit var mensaje:EditText
    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_principal)
        codigo = findViewById(R.id.codigo) as EditText;
        numero = findViewById(R.id.numero) as EditText;
        button = findViewById(R.id.mensajear) as Button
        mensaje = findViewById(R.id.mensaje) as EditText
        prefs = getPreferences(Context.MODE_PRIVATE)
        button.setOnClickListener{
            val editor = prefs!!.edit()
            editor.putString("codigo",codigo.text.toString())
            editor.putString("numero",numero.text.toString())
            editor.putString("mensaje",mensaje.text.toString())
            editor.apply()
            val url = "https://wa.me/54"+codigo.text+numero.text+"?text="+mensaje.text;
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        };
        loadPreviousMessage()
    }

    private fun loadPreviousMessage() {
        codigo.setText(prefs.getString("codigo", ""))
        numero.setText(prefs.getString("numero", ""))
        mensaje.setText(prefs.getString("mensaje", ""))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_actividad_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
