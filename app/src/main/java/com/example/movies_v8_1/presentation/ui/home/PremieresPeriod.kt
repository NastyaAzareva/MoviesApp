package com.example.movies_v8_1.presentation.ui.home

import java.text.SimpleDateFormat
import java.util.*

//возвращает месяц и год в нужном формете для запроса по номеру страницы
class PremieresPeriod(page: Int) {
    var year: Int
    var month: String

    init {
        val c = Calendar.getInstance()
        c.add(Calendar.MONTH, -1 * (page - 1))

        year = c.get(Calendar.YEAR)

        val dateFormat = SimpleDateFormat("MMMM", Locale.ENGLISH)
        dateFormat.timeZone = c.timeZone
        dateFormat.format(c.time)

        month = dateFormat.format(c.time).uppercase()
    }
}