package zuper.dev.android.dashboard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

inline fun <reified VM : ViewModel> viewModelFactory(noinline initializer: () -> VM): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass.isAssignableFrom(VM::class.java)) { "Unexpected view model class: ${modelClass.name}" }
            @Suppress("UNCHECKED_CAST") // Safe due to reified type and require check
            return initializer() as T
        }
    }
}




fun convertTime(start: String, end: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())

    val startTime = inputFormat.parse(start)
    val endTime = inputFormat.parse(end)

    if (startTime == null || endTime == null) {
        return "Invalid input format"
    }

    val startTimeFormatted = outputFormat.format(startTime)
    val endTimeFormatted = outputFormat.format(endTime)

    val startCalendar = Calendar.getInstance()
    startCalendar.time = startTime
    val endCalendar = Calendar.getInstance()
    endCalendar.time = endTime

    val startHour = startCalendar.get(Calendar.HOUR_OF_DAY)
    val endHour = endCalendar.get(Calendar.HOUR_OF_DAY)

    val startMinute = startCalendar.get(Calendar.MINUTE)
    val endMinute = endCalendar.get(Calendar.MINUTE)

    val amPmStart = if (startHour < 12) "AM" else "PM"
    val amPmEnd = if (endHour < 12) "AM" else "PM"

    val formattedStartHour = if (startHour > 12) startHour - 12 else startHour
    val formattedEndHour = if (endHour > 12) endHour - 12 else endHour

    val startTimeString = "$formattedStartHour:${String.format("%02d", startMinute)} $amPmStart"
    val endTimeString = "$formattedEndHour:${String.format("%02d", endMinute)} $amPmEnd"

    return "$startTimeFormatted $startTimeString - $endTimeString"
}