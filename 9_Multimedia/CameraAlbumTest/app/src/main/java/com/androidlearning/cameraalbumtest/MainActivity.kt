package com.androidlearning.cameraalbumtest

import android.R.attr.bitmap
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.exifinterface.media.ExifInterface
import java.io.File

class MainActivity : AppCompatActivity() {

    val takePhoto = 1
    lateinit var imageUri: Uri
    lateinit var outputImage: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val takePhotoBtn: Button = findViewById(R.id.takePhotoBtn)
        val imageView: ImageView = findViewById(R.id.imageView)


        /**
         * 调用相机实现拍照之后显示照片
         */
        val takePhotoResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            if (result.resultCode == RESULT_OK) {
                // 将拍摄的照片显示出来
                val bitmap = BitmapFactory.decodeStream(
                    contentResolver.openInputStream(imageUri)
                )
                imageView.setImageBitmap(rotateIfRequired(bitmap))
            }
        }
        takePhotoBtn.setOnClickListener {
            // 用于存储拍摄的照片
            outputImage = File(externalCacheDir, "output_image.jpg")
            if (outputImage.exists()) {
                outputImage.delete()
            }

            outputImage.createNewFile()
            imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                FileProvider.getUriForFile(
                    this, "com.androidlearning.cameraalbumtest.fileprovider", outputImage
                )
            } else {
                Uri.fromFile(outputImage)
            }

            // 启动相机
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            takePhotoResultLauncher.launch(intent)
        }


        // =========================================================================================


        val fromAlbumBtn: Button = findViewById(R.id.fromAlbumBtn)

        /**
         * 从相册中选择照片
         */
        val fromAlbumResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            var data = result.data
            if (result.resultCode == RESULT_OK && data != null) {
                data.data?.let { uri ->

                    // val bitmap = getBitmapFromUri(uri)

                    // 这里假设你想要图片的最大宽高为400像素
                    val bitmap = decodeSampledBitmapFromUri(uri, 400, 400)

                    imageView.setImageBitmap(bitmap)
                }
            }
        }

        fromAlbumBtn.setOnClickListener {

            // 打开文件选择器
//            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
//            intent.addCategory(Intent.CATEGORY_OPENABLE)
            // 指定显示图片
//            intent.type = "image/*"
//            fromAlbumResultLauncher.launch(intent)

            //
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            fromAlbumResultLauncher.launch(intent)
        }
    }

    private fun rotateIfRequired(bitmap: Bitmap): Bitmap {
        val exif = ExifInterface(outputImage.path)
        val orientation = exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )

        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap, 270)
            else -> bitmap
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedBitmap = Bitmap.createBitmap(
            bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true
        )
        bitmap.recycle() // 将不再需要的Bitmap对象回收
        return rotatedBitmap
    }


    /**
     * 读取照片
     */
    private fun getBitmapFromUri(uri: Uri) = contentResolver
        .openFileDescriptor(uri, "r")?.use {
            BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
        }


    private fun decodeSampledBitmapFromUri(uri: Uri, reqWidth: Int, reqHeight: Int): Bitmap? {

        // 第一步：读取图片的尺寸
        val options = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
        }

        contentResolver.openInputStream(uri)?.use {
            BitmapFactory.decodeStream(it, null, options)
        }

        // 计算压缩比例
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

        // 第二步：使用压缩比例加载图片
        options.inJustDecodeBounds = false

        return contentResolver.openInputStream(uri)
            ?.use { BitmapFactory.decodeStream(it, null, options) }
    }

    private fun calculateInSampleSize(
        options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int
    ): Int {

        val (height: Int, width: Int) = options.run { outHeight to outWidth }

        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            // 半高
            val halfHeight: Int = height / 2
            // 半宽
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }
}
