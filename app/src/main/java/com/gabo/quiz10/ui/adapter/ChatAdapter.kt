package com.gabo.quiz10.ui.adapter

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gabo.quiz10.R
import com.gabo.quiz10.comon.extensions.loadImage
import com.gabo.quiz10.ui.model.MessageType
import com.gabo.quiz10.comon.helpers.getData
import com.gabo.quiz10.databinding.ChatItemViewBinding
import com.gabo.quiz10.ui.model.ChatModelUi

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatVH>() {
    private var list: List<ChatModelUi> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<ChatModelUi>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class ChatVH(private val binding: ChatItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ChatModelUi) {
            with(binding) {
                ivIcon.loadImage(model.avatar)
                tvName.text = "${model.firstName} ${model.lastName}"
                if (model.updatedDate != null) {
                    tvTime.text = getData(model.updatedDate, "hh:mm a")
                } else {
                    tvTime.text = "just now"
                }
                if (model.isTyping) {
                    cvUnreadMessages.visibility = View.GONE
                    ivTyping.visibility = View.VISIBLE
                }
                if (model.unreadMessage != 0) {
                    tvMessage.setTypeface(null, Typeface.BOLD)
                    tvTime.setTypeface(null, Typeface.BOLD)
                    tvMessage.setTextColor(
                        ContextCompat.getColor(tvMessage.context, R.color.white)
                    )
                    tvTime.setTextColor(ContextCompat.getColor(tvTime.context, R.color.white))
                    if (model.unreadMessage > 1) {
                        if (model.isTyping) {
                            cvUnreadMessages.visibility = View.GONE
                            ivTyping.visibility = View.VISIBLE
                        } else {
                            cvUnreadMessages.visibility = View.VISIBLE
                            tvUnreadMessages.text = model.unreadMessage.toString()
                            ivTyping.visibility = View.GONE
                        }
                    }
                } else {
                    tvMessage.setTypeface(null, Typeface.NORMAL)
                    tvMessage.setTextColor(
                        ContextCompat.getColor(tvMessage.context, R.color.txt_gray)
                    )
                    tvTime.setTypeface(null, Typeface.NORMAL)
                    tvTime.setTextColor(ContextCompat.getColor(tvTime.context, R.color.white))
                }
                when (model.messageType) {
                    MessageType.Text -> {
                        tvMessage.text = model.lastMessage
                        cvVoiceOrAttachment.visibility = View.GONE
                    }
                    MessageType.Attachment -> {
                        cvVoiceOrAttachment.visibility = View.VISIBLE
                        ivVoiceOrAttachment.setImageResource(R.drawable.ic_attachment)
                        tvMessage.text = Resources.getSystem().getString(R.string.attachment_notification)
                    }
                    MessageType.Voice -> {
                        cvVoiceOrAttachment.visibility = View.VISIBLE
                        ivVoiceOrAttachment.setImageResource(R.drawable.ic_voice)
                        tvMessage.text = Resources.getSystem().getString(R.string.voice_notification)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatVH {
        val binding =
            ChatItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatVH(binding)
    }

    override fun onBindViewHolder(holder: ChatVH, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size
}


