package com.example.skincare

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import android.graphics.BitmapFactory
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    private lateinit var uploadButton: Button
    private lateinit var skinAnalysisButton: Button
    private lateinit var skinImageView: ImageView
    private lateinit var skinTipsTextView: TextView

    private var uploadedImage: Bitmap? = null

    // Registering image selection from gallery
    private val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            // Display the selected image
            skinImageView.setImageURI(it)
            uploadedImage = getBitmapFromUri(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the UI components
        uploadButton = findViewById(R.id.uploadButton)
        skinAnalysisButton = findViewById(R.id.skinAnalysisButton)
        skinImageView = findViewById(R.id.skinImageView)
        skinTipsTextView = findViewById(R.id.skinTipsTextView)

        // Set OnClickListener for upload button
        uploadButton.setOnClickListener {
            // Open gallery to choose an image
            getImage.launch("image/*")
        }

        // Set OnClickListener for skin analysis button
        skinAnalysisButton.setOnClickListener {
            uploadedImage?.let {
                // Perform skin analysis
                val skinAnalysisResult = analyzeSkin()

                // Display the skin analysis result
                skinTipsTextView.text = skinAnalysisResult
            }
        }
    }

    // Convert Uri to Bitmap
    private fun getBitmapFromUri(uri: android.net.Uri): Bitmap {
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        return BitmapFactory.decodeStream(inputStream)
    }

    // Simulated skin analysis function (replace this with actual logic/API call)
    // Simulated skin analysis function (replace this with actual logic/API call)
    private fun analyzeSkin(): String {
        // In a real-world scenario, send the image to an AI model/API for analysis
        // Here we return a mock response with multiple attributes
        return """
        Skin Analysis Report:
        
        • Skin Tone: Light with slight redness
        • Texture: Slightly rough with visible pores
        • Wrinkles: Fine lines present around eyes and forehead
        • Moisture: Low moisture level — skin appears dry
        
        🔍 Recommendation:
        - Use a hydrating moisturizer with hyaluronic acid
        - Apply sunscreen daily to prevent wrinkle deepening
        - Exfoliate 2 times/week to improve texture
        - Use a serum with Vitamin C to even skin tone
    """.trimIndent()
    }

}
