package com.example.soulsync.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.soulsync.states.ChatStates
import com.example.soulsync.R
import com.google.firebase.auth.FirebaseAuth

private val Tag = "UserAdapter"


class chatbotAdapter(val c: Context, val messageLst : ArrayList<ChatStates>) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val ITEM_RECIEVE = 1
    val ITEM_SENT = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 1) {
            val view: View = LayoutInflater.from(c).inflate(R.layout.recyler_chat_view2, parent, false)
            return ReceiverViewHolder(view)
        }
        else {
            val view: View = LayoutInflater.from(c).inflate(R.layout.recyler_chat_view, parent, false)
            return SenderViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageLst[position]

        if(holder.javaClass == SenderViewHolder::class.java){
            val viewHolder  = holder as SenderViewHolder
            holder.sentTxt.text = currentMessage.message
        }else{
            val viewHolder  = holder as ReceiverViewHolder
            holder.recvTxt.text = currentMessage.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageLst[position]

        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.user)){
            return ITEM_SENT
        }
        else{
            return ITEM_RECIEVE
        }
    }

    override fun getItemCount(): Int {
        return messageLst.size
    }
    inner class ReceiverViewHolder(view:View) :RecyclerView.ViewHolder(view){
        val recvTxt : TextView = view.findViewById(R.id.tbx2)
    }
    inner class SenderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val sentTxt : TextView = view.findViewById(R.id.tbx)
    }

}
