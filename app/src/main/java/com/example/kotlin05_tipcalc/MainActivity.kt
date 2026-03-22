package com.example.kotlin05_tipcalc

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {

    var currentBillTotal: Double = 0.0
    var currentCustomPercent: Int = 18
    lateinit var tip10EditText: EditText
    lateinit var tip15EditText: EditText
    lateinit var tip20EditText: EditText
    lateinit var total10EditText: EditText
    lateinit var total15EditText: EditText
    lateinit var total20EditText: EditText
    lateinit var billEditText: EditText
    lateinit var tipCustomEditText: EditText
    lateinit var totalCustomEditText: EditText
    lateinit var customTipTexView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tip10EditText = findViewById(R.id.tip10EditText)
        tip15EditText = findViewById(R.id.tip15EditText)
        tip20EditText = findViewById(R.id.tip20EditText)
        total10EditText = findViewById(R.id.total10EditText)
        total15EditText = findViewById(R.id.total15EditText)
        total20EditText = findViewById(R.id.total20EditText)
        billEditText = findViewById(R.id.billEditText)
        tipCustomEditText = findViewById(R.id.tipCustomEditText)
        totalCustomEditText = findViewById(R.id.totalCustomEditText)
        customTipTexView = findViewById(R.id.customTipTexView)

        var customSeekBar = findViewById<SeekBar>(R.id.customSeekBar)

        currentCustomPercent = customSeekBar.progress
        billEditText.addTextChangedListener(billTextWatcher)
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener)

    }

    @SuppressLint("DefaultLocale")
    fun updateStandard() {
        var tenPercentTip = currentBillTotal * 0.10
        var tenPercentTotal = currentBillTotal + tenPercentTip
        var fifteenPercentTip = currentBillTotal * 0.15
        var fifteenPercentTotal = currentBillTotal + fifteenPercentTip
        var twentyPercentTip = currentBillTotal * 0.20
        var twentyPercentTotal = currentBillTotal + twentyPercentTip

        tip10EditText.setText(String.format("%.2f", tenPercentTip))
        total10EditText.setText(String.format("%.2f", tenPercentTotal))
        tip15EditText.setText(String.format("%.2f", fifteenPercentTip))
        total15EditText.setText(String.format("%.2f", fifteenPercentTotal))
        tip20EditText.setText(String.format("%.2f", twentyPercentTip))
        total20EditText.setText(String.format("%.2f", twentyPercentTotal))
    }

    @SuppressLint("DefaultLocale")
    fun updateCustom() {
        customTipTexView.text = "$currentCustomPercent %"

        var customTipAmount = currentBillTotal * currentCustomPercent * .01
        var customTotalAmount = currentBillTotal + customTipAmount

        tipCustomEditText.setText(String.format("%.2f", customTipAmount))
        totalCustomEditText.setText(String.format("%.2f", customTotalAmount))
    }

    private val customSeekBarListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            currentCustomPercent = progress
            updateCustom()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}

        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    }

    private val billTextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            try {
                currentBillTotal = s.toString().toDouble()
            } catch (e: NumberFormatException) {
                currentBillTotal = 0.0
            }
            updateStandard()
            updateCustom()
        }

        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    }
}
