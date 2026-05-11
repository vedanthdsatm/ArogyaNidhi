package com.arogyaniidhi.app.ui.chatbot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arogyaniidhi.app.data.model.ChatMessage
import com.arogyaniidhi.app.databinding.ItemChatMessageBinding

class ChatMessageAdapter(
    private val messages: MutableList<ChatMessage>
) : RecyclerView.Adapter<ChatMessageAdapter.ChatMessageViewHolder>() {

    inner class ChatMessageViewHolder(private val binding: ItemChatMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatMessage) {
            if (message.isUser) {
                binding.cardUserMessage.visibility = View.VISIBLE
                binding.cardBotMessage.visibility = View.GONE
                binding.tvUserMessage.text = message.text
            } else {
                binding.cardUserMessage.visibility = View.GONE
                binding.cardBotMessage.visibility = View.VISIBLE
                binding.tvBotMessage.text = message.text
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMessageViewHolder {
        val binding = ItemChatMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatMessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatMessageViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount(): Int = messages.size

    fun addMessage(message: ChatMessage) {
        messages.add(message)
        notifyItemInserted(messages.lastIndex)
    }
}
