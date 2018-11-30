package com.example.grizel.tss

import android.app.Activity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : Activity(),TextToSpeech.OnInitListener {

    var tts : TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this, this)
        Log.i("Language", Locale.getAvailableLocales().toString())

        btnRead!!.setOnClickListener {
            val frase = etText.text.toString()
            if (frase != ""){
                Toast.makeText(this, "presionado", Toast.LENGTH_SHORT).show()
                tts!!.speak(frase, TextToSpeech.QUEUE_FLUSH, null, null)
            }else{
                tts!!.speak("campo vacío", TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }


    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale("es_MX"))
            if (result != TextToSpeech.LANG_NOT_SUPPORTED || result != TextToSpeech.LANG_MISSING_DATA){
            }else{
                Toast.makeText(this,"no soportado",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        if (tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

}
