package zuper.dev.android.dashboard.data

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Date {

    fun getCurrentDate(): String {
        val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d uuuu", Locale.ENGLISH)
        return LocalDateTime.now().format(formatter)
    }



}