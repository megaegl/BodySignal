package com.example.bodysignal

import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class SimodelActivity : AppCompatActivity() {
    private lateinit var interpreter: Interpreter
    private val mModelPath = "model_smoking.tflite"

    private lateinit var resultText: TextView
    private lateinit var gender: EditText
    private lateinit var hemoglobin: EditText
    private lateinit var height: EditText
    private lateinit var weight: EditText
    private lateinit var triglyceride: EditText
    private lateinit var gtp: EditText
    private lateinit var waist: EditText
    private lateinit var serum_creatinine: EditText
    private lateinit var checkButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simodel)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        resultText = findViewById(R.id.txtResult)
        gender = findViewById(R.id.gender)
        hemoglobin = findViewById(R.id.hemoglobin)
        height = findViewById(R.id.height)
        weight = findViewById(R.id.weight)
        triglyceride = findViewById(R.id.triglyceride)
        gtp = findViewById(R.id.gtp)
        waist = findViewById(R.id.waist)
        serum_creatinine = findViewById(R.id.serum_creatinine)
        checkButton = findViewById(R.id.btnCheck)
        checkButton.setOnClickListener {
            var result = doInference(
                gender.text.toString(),
                hemoglobin.text.toString(),
                height.text.toString(),
                weight.text.toString(),
                triglyceride.text.toString(),
                gtp.text.toString(),
                waist.text.toString(),
                serum_creatinine.text.toString()
            )
            runOnUiThread {
                if (result == 0) {
                    resultText.text = "Perokok"
                } else if (result == 1) {
                    resultText.text = "Bukan Perokok"
                }
            }
        }
        initInterpreter()
    }

    private fun initInterpreter() {
        val options = Interpreter.Options()
        options.setNumThreads(5)
        //options.setUseNNAPI(true)
        interpreter = Interpreter(loadModelFile(assets, mModelPath),options)
    }

    private fun loadModelFile(assetManager: AssetManager, modelPath: String): MappedByteBuffer {
        val fileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffSet = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffSet, declaredLength)
    }

    private fun doInference(input1: String, input2: String, input3: String, input4: String, input5: String, input6: String, input7: String, input8: String): Int {
        val inputVal = FloatArray(8)
        inputVal[0] = input1.toFloat()
        inputVal[1] = input2.toFloat()
        inputVal[2] = input3.toFloat()
        inputVal[3] = input4.toFloat()
        inputVal[4] = input5.toFloat()
        inputVal[5] = input6.toFloat()
        inputVal[6] = input7.toFloat()
        inputVal[7] = input8.toFloat()

        val output = Array(1) { FloatArray(2) }
        interpreter.run(inputVal, output)

        Log.e("result", (output[0].toList() + " ").toString())

        return output[0].indexOfFirst { it == output[0].maxOrNull() }
    }
}
