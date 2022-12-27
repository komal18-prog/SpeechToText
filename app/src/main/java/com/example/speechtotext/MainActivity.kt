package com.example.speechtotext


import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    var iv_mic:ImageView?=null
    var tv_speech_to_text:TextView?=null
    val REQUEST_CODE_SPEECH_INPUT = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    iv_mic=findViewById(R.id.iv_mic)
    tv_speech_to_text=findViewById(R.id.tv_speech_to_text)

        iv_mic?.setOnClickListener {
            var intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text")
            try {
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, " " + e.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQUEST_CODE_SPEECH_INPUT){
            if(resultCode == RESULT_OK && data!=null){
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            }
            tv_speech_to_text?.setText(Objects.requireNonNull(resultCode))

        }
    }
    fun onBeginningOfSpeech() {
        tv_speech_to_text?.setText("Listening...")
    }
}

