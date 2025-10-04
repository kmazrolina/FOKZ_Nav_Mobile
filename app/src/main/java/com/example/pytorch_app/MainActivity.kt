package com.example.pytorch_app

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.pytorch.IValue
import org.pytorch.LiteModuleLoader
import org.pytorch.Module
import org.pytorch.Tensor
import java.io.File
import java.io.IOException
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var module: Module? = null
    private lateinit var btnInfer: Button
    private lateinit var tvResult: TextView

    companion object {
        const val TAG = "PyTorchApp"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnInfer = findViewById(R.id.btnInfer)
        tvResult = findViewById(R.id.tvResult)

        // Load the model
        try {
            module = LiteModuleLoader.load(assetFilePath("mobile_ronin_lite.pt"))
        } catch (e: IOException) {
            Log.e(TAG, "Unable to load model", e)
            tvResult.text = "Error loading model"
        }

        // Button click to run inference
        btnInfer.setOnClickListener {
            val result = runInference()
            tvResult.text = result
        }
    }

    private fun runInference(): String {
        module?.let { mod ->
            // Create random tensor of shape (1, 6, 200)
            val data = FloatArray(1 * 6 * 200) { Random.nextFloat() }
            val inputTensor = Tensor.fromBlob(data, longArrayOf(1, 6, 200))

            // Run the model
            val outputTensor = mod.forward(IValue.from(inputTensor)).toTensor()

            // Convert output tensor to float array
            val outputData = outputTensor.dataAsFloatArray

            // Return as string (first 10 elements for simplicity)
            return outputData.take(10).joinToString(prefix = "[", postfix = ", ...]")
        } ?: run {
            return "Model not loaded"
        }
    }

    // Utility function to load model from assets
    @Throws(IOException::class)
    private fun assetFilePath(assetName: String): String {
        val file = File(filesDir, assetName)
        if (!file.exists()) {
            assets.open(assetName).use { input ->
                file.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
        }
        return file.absolutePath
    }
}
