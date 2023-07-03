package com.example.snapchatfilter

import android.content.Context
import android.graphics.*
import androidx.appcompat.widget.AppCompatImageView
import android.util.SparseArray
import android.graphics.drawable.BitmapDrawable
import com.google.android.gms.vision.face.Face

class MaskedImageView(context: Context) : AppCompatImageView(context) {
    private var faces: SparseArray<Face>? = null
    private var maskType = MainActivity.MaskType.BEAR
    var mPaint = Paint()
    private var mBitmap: Bitmap? = null

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mBitmap = (drawable as BitmapDrawable).bitmap
        if (mBitmap == null) {
            return
        }
        val viewWidth = width.toDouble()
        val viewHeight = height.toDouble()
        val imageWidth = mBitmap!!.width.toDouble()
        val imageHeight = mBitmap!!.height.toDouble()
        val scale = Math.min(viewWidth / imageWidth, viewHeight / imageHeight)
        drawBitmap(canvas, scale)
        when (maskType) {
            MainActivity.MaskType.BEAR -> drawBearMaskOnCanvas(canvas, scale)
            MainActivity.MaskType.CAT -> drawCatMaskOnCanvas(canvas, scale)
            MainActivity.MaskType.DOG -> drawDogMaskOnCanvas(canvas, scale)
            MainActivity.MaskType.HAT -> drawHatMaskOnCanvas(canvas, scale)
            MainActivity.MaskType.MASK -> drawFaceMaskOnCanvas(canvas, scale)
            MainActivity.MaskType.GLASSES1 -> drawGlassesOneMaskOnCanvas(canvas, scale)
            MainActivity.MaskType.GLASSES2 -> drawGlassesTwoMaskOnCanvas(canvas, scale)
            else -> {}
        }
    }

    fun drawMask(faces: SparseArray<Face>?, maskType: MainActivity.MaskType) {
        this.faces = faces
        this.maskType = maskType
        this.invalidate()
    }

    private fun drawBitmap(canvas: Canvas, scale: Double) {
        val imageWidth = mBitmap!!.width.toDouble()
        val imageHeight = mBitmap!!.height.toDouble()
        val destBounds = Rect(0, 0, (imageWidth * scale).toInt(), (imageHeight * scale).toInt())
        canvas.drawBitmap(mBitmap!!, null, destBounds, null)
    }

    private fun drawBearMaskOnCanvas(canvas: Canvas, scale: Double) {
        for (i in 0 until faces!!.size()) {
            val face = faces!!.valueAt(i)
            val x = scale.toFloat() * (face.position.x + face.width / 2)
            val y = scale.toFloat() * (face.position.y + face.height / 2)
            var deltaX: Float = (1.2f * scale * (face.width / 2)).toFloat()
            var deltaY: Float = (1.3f * scale * (face.height / 2)).toFloat()
            var mask: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.bear)
            val left = (x - deltaX).toInt()
            val top = (y - deltaY).toInt()
            val right = (x + deltaX).toInt()
            val bottom = (y + deltaY).toInt()
            val maskBounds = Rect(left, top, right, bottom)
            canvas.drawBitmap(mask, null, maskBounds, null)
        }
    }

    private fun drawCatMaskOnCanvas(canvas: Canvas, scale: Double) {
        for (i in 0 until faces!!.size()) {
            val face = faces!!.valueAt(i)
            val x = scale.toFloat() * (face.position.x + face.width / 2)
            val y = scale.toFloat() * (face.position.y + face.height / 2)
            var deltaX: Float = (1.5f * scale * (face.width / 2.5f)).toFloat()
            var deltaY: Float = (1.4f * scale * (face.height / 2.5f)).toFloat()
            var mask: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.cat)
            val left = (x - deltaX).toInt()
            val top = (y - deltaY).toInt()
            val right = (x + deltaX).toInt()
            val bottom = (y + deltaY).toInt()
            val maskBounds = Rect(left, top, right, bottom)
            canvas.drawBitmap(mask, null, maskBounds, null)
        }
    }

    private fun drawDogMaskOnCanvas(canvas: Canvas, scale: Double) {
        for (i in 0 until faces!!.size()) {
            val face = faces!!.valueAt(i)
            val x = scale.toFloat() * (face.position.x + face.width / 2)
            val y = scale.toFloat() * (face.position.y + face.height / 2)
            var deltaX: Float = (1.5f * scale * (face.width / 2.5f)).toFloat()
            var deltaY: Float = (1.4f * scale * (face.height / 2.5f)).toFloat()
            var mask: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.dog)
            val left = (x - deltaX).toInt()
            val top = (y - deltaY).toInt()
            val right = (x + deltaX).toInt()
            val bottom = (y + deltaY).toInt()
            val maskBounds = Rect(left, top, right, bottom)
            canvas.drawBitmap(mask, null, maskBounds, null)
        }
    }

    private fun drawHatMaskOnCanvas(canvas: Canvas, scale: Double) {
        for (i in 0 until faces!!.size()) {
            val face = faces!!.valueAt(i)
            val x = scale.toFloat() * (face.position.x + face.width / 2.5f)
            val y = scale.toFloat() * (face.position.y + face.height / 2.5f)
            var deltaX: Float = (1.80f * scale * (face.width / 2)).toFloat()
            var deltaY: Float = (1.70f * scale * (face.height / 2)).toFloat()
            var mask: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.hat)
            val left = (x - deltaX).toInt()
            val top = (y - deltaY).toInt()
            val right = (x + deltaX).toInt()
            val bottom = (y + deltaY).toInt()
            val maskBounds = Rect(left, top, right, bottom)
            canvas.drawBitmap(mask, null, maskBounds, null)
        }
    }

    private fun drawFaceMaskOnCanvas(canvas: Canvas, scale: Double) {
        for (i in 0 until faces!!.size()) {
            val face = faces!!.valueAt(i)
            val x = scale.toFloat() * (face.position.x + face.width / 2)
            val y = scale.toFloat() * (face.position.y + face.height / 2)
            var deltaX: Float = (1.3f * scale * (face.width / 2)).toFloat()
            var deltaY: Float = (1.3f * scale * (face.height / 2)).toFloat()
            var mask: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.mask)
            val left = (x - deltaX).toInt()
            val top = (y - deltaY).toInt()
            val right = (x + deltaX).toInt()
            val bottom = (y + deltaY).toInt()
            val maskBounds = Rect(left, top, right, bottom)
            canvas.drawBitmap(mask, null, maskBounds, null)
        }
    }

    private fun drawGlassesOneMaskOnCanvas(canvas: Canvas, scale: Double) {
        for (i in 0 until faces!!.size()) {
            val face = faces!!.valueAt(i)
            val x = scale.toFloat() * (face.position.x + face.width / 2.5f)
            val y = scale.toFloat() * (face.position.y + face.height / 2.5f)
            var deltaX: Float = (1.8f * scale * (face.width / 2)).toFloat()
            var deltaY: Float = (1.7f * scale * (face.height / 2)).toFloat()
            var mask: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.glasses1)
            val left = (x - deltaX).toInt()
            val top = (y - deltaY).toInt()
            val right = (x + deltaX).toInt()
            val bottom = (y + deltaY).toInt()
            val maskBounds = Rect(left, top, right, bottom)
            canvas.drawBitmap(mask, null, maskBounds, null)
        }
    }

    private fun drawGlassesTwoMaskOnCanvas(canvas: Canvas, scale: Double) {
        for (i in 0 until faces!!.size()) {
            val face = faces!!.valueAt(i)
            val x = scale.toFloat() * (face.position.x + face.width / 2)
            val y = scale.toFloat() * (face.position.y + face.height / 2)
            var deltaX: Float = (1.1f * scale * (face.width / 2)).toFloat()
            var deltaY: Float = (1.1f * scale * (face.height / 2)).toFloat()
            var mask: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.glasses2)
            val left = (x - deltaX).toInt()
            val top = (y - deltaY).toInt()
            val right = (x + deltaX).toInt()
            val bottom = (y + deltaY).toInt()
            val maskBounds = Rect(left, top, right, bottom)
            canvas.drawBitmap(mask, null, maskBounds, null)
        }
    }
}