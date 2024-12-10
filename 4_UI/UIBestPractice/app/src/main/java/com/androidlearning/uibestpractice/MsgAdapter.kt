package com.androidlearning.uibestpractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MsgAdapter(private val msgList: List<MsgEntity>) :
    RecyclerView.Adapter<MsgAdapter.MsgViewHolder>() {

    /**
     * 使用密封类优化代码
     */
    sealed class MsgViewHolder(view: View) : RecyclerView.ViewHolder(view)

    inner class LeftMsgViewHolder(view: View) : MsgViewHolder(view) {
        val leftMsg: TextView = view.findViewById(R.id.leftMsg)
    }

    inner class RightMsgViewHolder(view: View) : MsgViewHolder(view) {
        val rightMsg: TextView = view.findViewById(R.id.rightMsg)
    }

    override fun getItemViewType(position: Int): Int {
        val msg = msgList[position]
        return msg.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsgViewHolder {
        if (viewType == MsgEntity.TYPE_RECEIVED) {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.msg_left_item, parent, false)
            return LeftMsgViewHolder(view)
        } else {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.msg_right_item, parent, false)
            return RightMsgViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: MsgViewHolder, position: Int) {
        val msgEntity = msgList[position]
        when (holder) {
            is LeftMsgViewHolder -> holder.leftMsg.text = msgEntity.content
            is RightMsgViewHolder -> holder.rightMsg.text = msgEntity.content
        }
    }

    override fun getItemCount() = msgList.size
}
