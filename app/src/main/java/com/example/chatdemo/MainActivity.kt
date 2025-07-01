package com.example.chatdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chat.chatsdk.ChatSdkManager
import com.chat.chatsdk.utils.EncryptSharedPreferenceManager
import com.example.chatdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ChatSdkManager.configured(
            appId = "c09493260b8153ac",
            appKey = "bfc6f3084a8c0bd7e194fb89ecdede39",
            language = "ENGLISH",
            userId = "6293b288-473a-4500-a946-de7591853afe",
            userName = "Youssef",
            userEmail = "youssefbadway@gmail.com"
        )
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            showMyChats()
        }

        addUsers()
    }

    /*
    * add users to start chat with them
    * */
    private fun addUsers() {
        ChatSdkManager.addUser(
            context = this,
            userIdReq = "2010",
            userNameReq = "Lara Smith",
            countryCodeReq = "+44",
            mobileNumberReq = "7555555555",
            emailReq = "lara.smith@mobiloitte.com",
            profileReq = "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?q=80&w=1740&auto=format&fit=crop"
        )

        ChatSdkManager.addUser(
            context = this,
            userIdReq = "2011",
            userNameReq = "Product A",
            countryCodeReq = "+1",
            mobileNumberReq = "4081234567",
            emailReq = "john.doe@mobiloitte.com",
            profileReq = "https://images.unsplash.com/photo-1603415526960-f8f0a9891313?q=80&w=1740&auto=format&fit=crop"
        )
    }

    private fun startChat(id: String) {
        ChatSdkManager.chatInit(
            context = this,
            receiverIdReq = id, //start chat with user id 2011 that was already added
            productIdReq = "12345",
            productNameReq = "Product A",
            productImageReq = "https://www.mdxblog.io/images/posts/how-to-use-images/grass-tree-sky.jpg"
        )
    }

    private fun showMyChats() {
        ChatSdkManager.initializeChatList(
            context = this,
            userId = EncryptSharedPreferenceManager.getInstance(this).userId, //logged in userID
            userName = "My name"
        )
    }
}