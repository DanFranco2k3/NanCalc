package com.example.myapplication


import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var colorcombination: TextView
    private lateinit var colors: String
    private var stringList = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorcombination = findViewById(R.id.resultDisplay)

        val infoButton = findViewById<ImageButton>(R.id.infoButton)
        infoButton.setOnClickListener {
            Toast.makeText(this, "Button is online!", Toast.LENGTH_SHORT).show()

            val inflater = layoutInflater
            val view = inflater.inflate(R.layout.activity_popupwindow,null)

            val window = PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,true)

            window.showAtLocation(view, Gravity.CENTER, 0, 0)
        }

    }


    // ImageButton
    fun onColorCombineClick(view: View) {
        //        val zoomInAnimation = AnimationUtils.loadAnimation(context:this, R.anim.zoom_in)
        //        view.startAnimation(zoomInAnimation)

        colors = when (view.id) {
            R.id.redButton -> "Red"
            R.id.blueButton -> "Blue"
            R.id.yellowButton -> "Yellow"
            R.id.blackButton -> "Black"

            else -> ""
        }
        stringList.add(colors)
        colorcombination.text = "$stringList selected"
    }


    // Button
    fun combineButton(view: View) {
        val selectedColors = stringList
        val colorMessage = when (selectedColors.size) {
            0 -> "No Colors to be combined"
            1 -> "This is color ${selectedColors[0]}."
            2 -> {
                if ("Red" in selectedColors && "Yellow" in selectedColors) {
                    "This is Orange"
                } else if ("Yellow" in selectedColors && "Blue" in selectedColors) {
                    "This is Green"
                } else if ("Blue" in selectedColors && "Red" in selectedColors) {
                    "This is Violet"
                } else if ("Black" in selectedColors && "Red" in selectedColors) {
                    "This is Dark Red"
                } else if ("Black" in selectedColors && "Yellow" in selectedColors) {
                    "This is Dark Yellow"
                } else if ("Black" in selectedColors && "Blue" in selectedColors) {
                    "This is Dark Blue"
                } else {
                    "Colors selected, ready to combine?"
                }
            }

            3 -> "You must choose two colors only."
            else -> "Colors are selected"
        }
        colorcombination.text = colorMessage
        stringList.clear()
    }
}
