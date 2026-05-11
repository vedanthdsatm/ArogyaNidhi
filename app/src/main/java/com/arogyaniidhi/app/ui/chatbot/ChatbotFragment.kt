package com.arogyaniidhi.app.ui.chatbot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.arogyaniidhi.app.data.model.ChatMessage
import com.arogyaniidhi.app.data.repository.ChatbotRepository
import com.arogyaniidhi.app.databinding.FragmentChatbotBinding

class ChatbotFragment : Fragment() {

    private var _binding: FragmentChatbotBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ChatMessageAdapter
    private val messages = mutableListOf<ChatMessage>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatbotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ChatMessageAdapter(messages)
        binding.rvChat.layoutManager = LinearLayoutManager(requireContext())
        binding.rvChat.adapter = adapter

        adapter.addMessage(
            ChatMessage(
                text = "Namaste! I can answer health scheme queries, eligibility help, and documents checklist questions.",
                isUser = false
            )
        )

        binding.btnSend.setOnClickListener {
            val question = binding.etMessage.text?.toString()?.trim().orEmpty()
            if (question.isEmpty()) return@setOnClickListener

            adapter.addMessage(ChatMessage(question, true))
            binding.etMessage.setText("")

            val response = ChatbotRepository.getResponse(question)
            adapter.addMessage(ChatMessage(response, false))
            binding.rvChat.scrollToPosition(adapter.itemCount - 1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
