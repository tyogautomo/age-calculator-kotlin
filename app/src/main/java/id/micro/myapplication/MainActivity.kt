package id.micro.myapplication

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView? = null;
    private var tvInMinutesText: TextView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main)

        val buttonPicker: Button = findViewById(R.id.btnDatePicker)
        buttonPicker.setOnClickListener {
           onClickSelectDate()
        }

        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        tvInMinutesText = findViewById((R.id.tvInMinutesText))
    }

    private fun onClickSelectDate() {
        val date = Calendar.getInstance()
        val year = date.get(Calendar.YEAR)
        val month = date.get(Calendar.MONTH)
        val day = date.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate?.text = selectedDate

                val simpleDateFormat = java.text.SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = simpleDateFormat.parse(selectedDate);

                theDate?.let {
                    val dateInMinutes = theDate.time / 60000

                    val currentDate = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))

                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000

                        val difference = currentDateInMinutes - dateInMinutes

                        tvInMinutesText?.text = difference.toString()
                    }
                }
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }

}