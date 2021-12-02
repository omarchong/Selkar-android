package com.example.examen_omar_chong

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_cliente.view.*

class ClientesAdapter (private val  mContext: Context, private val listaClientes: List<Cliente>): ArrayAdapter<Cliente>(mContext, 0, listaClientes) {

    override  fun getView(position: Int, convertView: View?,parent: ViewGroup): View{
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_cliente, parent, false)

        val cliente = listaClientes[position]

        layout.nombrec.text = cliente.nombrecliente
        layout.giroc.text = cliente.giro
        layout.imageView4.setImageResource(cliente.imagen)


        return layout
    }
}