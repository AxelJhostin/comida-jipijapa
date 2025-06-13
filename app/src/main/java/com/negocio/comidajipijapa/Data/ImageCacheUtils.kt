package com.negocio.comidajipijapa.Data

import android.content.Context
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import android.graphics.Bitmap

suspend fun getImageFromCacheOrDownload(context: Context, imageUrl: String): File? {
    return withContext(Dispatchers.IO) {
        try {
            val cacheDir = File(context.cacheDir, "comidajipijapa_images")
            if (!cacheDir.exists()) cacheDir.mkdirs()

            val filename = imageUrl.hashCode().toString() + ".jpg"
            val imageFile = File(cacheDir, filename)

            if (imageFile.exists()) {
                return@withContext imageFile
            } else {
                val inputStream = URL(imageUrl).openStream()
                val bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream.close()

                FileOutputStream(imageFile).use { fos ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos)
                }

                return@withContext imageFile
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext null
        }
    }
}
