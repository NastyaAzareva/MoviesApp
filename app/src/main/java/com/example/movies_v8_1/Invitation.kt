package com.example.movies_v8_1

import android.app.Activity
import android.content.Intent

class Invitation {
    var address = "anaioanidi@gmail.com"
    var subject = "Welcome to MovieApp"
    var emailtext = "Welcome to MovieApp"

    fun inviteViaEmail(activity: Activity){
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "plain/text"

        emailIntent.putExtra(Intent.EXTRA_EMAIL, address) // Кому
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject) // Зачем
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailtext) // О чём

        activity.startActivity(Intent.createChooser(emailIntent,"Отправка письма..."))
    }
}
