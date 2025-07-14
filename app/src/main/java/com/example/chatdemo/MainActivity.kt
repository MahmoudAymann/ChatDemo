package com.example.chatdemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chat.chatsdk.ChatSdkManager
import com.example.chatdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ChatSdkManager.initChatSdk(
            appId = "c09493260b8153ac",
            appKey = "bfc6f3084a8c0bd7e194fb89ecdede39",
            language = "ENGLISH",
            userId = "6293b288-473a-4500-a946-de7591853afe",
            userName = "Youssef",
            email = "youssefbadway@gmail.com",
            phoneNumber = "+201000000000",
            onSuccess = {
                Toast.makeText(this, "Success init", Toast.LENGTH_SHORT).show()
            }, onFailure = {
                Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
            }
        )
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            addUsers()
        }

        //addUsers()
    }

    /*
    * add users to start chat with them
    * */
    private fun addUsers() {
        ChatSdkManager.addUserToChat(
            context = this,
            userIdReq = "2010",
            userNameReq = "Lara Smith",
            countryCodeReq = "+44",
            mobileNumberReq = "7555555555",
            emailReq = "lara.smith@mobiloitte.com",
            profileReq = "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?q=80&w=1740&auto=format&fit=crop",
            onSuccess = {
                //User Added
                //start chat with user
                startChat("2010")
            },
            onFailure = {
                //Error adding user
                Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
            })
    }

    private fun startChat(id: String) {
        ChatSdkManager.startChat(
            context = this,
            receiverIdReq = id
        ) {
            Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun startPChat(uId:String, pId: String, pName:String, pImage: String) {
        ChatSdkManager.startProductChat(
            context = this,
            receiverIdReq = uId,
            productIdReq = pId,
            productNameReq = pName,
            productImageReq = pImage
        ) {
            Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    
    

    private fun showMyChats() {
        ChatSdkManager.showChatList(
            context = this,
            userId = "2010", //logged in userID
            userName = "My name"
        )
    }
}