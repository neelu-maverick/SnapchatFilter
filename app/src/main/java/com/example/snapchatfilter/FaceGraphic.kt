package com.example.snapchatfilter

import android.content.Context
import com.example.snapchatfilter.camera.GraphicOverlay
import com.example.snapchatfilter.camera.GraphicOverlay.Graphic
import kotlin.jvm.Volatile
import com.example.snapchatfilter.MainActivity.MaskType
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import com.google.android.gms.vision.face.Face

class FaceGraphic internal constructor(overlay: GraphicOverlay?, private val context: Context) :
    Graphic(overlay) {
    @Volatile
    private var mFace: Face? = null
    private var maskType = MaskType.BEAR
    fun setMaskType(maskType: MaskType) {
        this.maskType = maskType
    }

    fun updateFace(face: Face?) {
        mFace = face
        postInvalidate()
    }

    override fun draw(canvas: Canvas?) {
        val face = mFace ?: return
        val x = translateX(face.position.x + face.width / 2)
        val y = translateY(face.position.y + face.height / 2)
        val deltaX: Float
        val deltaY: Float
        val mask: Bitmap
        if (maskType == MaskType.CAT) {
            deltaX = 1.6f * scaleX(mFace!!.width / 2)
            deltaY = 1.2f * scaleY(mFace!!.height / 2)
            mask = BitmapFactory.decodeResource(context.resources, R.drawable.cat)
        } else if (maskType == MaskType.BEAR) {
            deltaX = 1.1f * scaleX(mFace!!.width / 2)
            deltaY = 1.3f * scaleY(mFace!!.height / 2)
            mask = BitmapFactory.decodeResource(context.resources, R.drawable.bear)
        } else if (maskType == MaskType.DOG) {
            deltaX = 1.4f * scaleX(mFace!!.width / 2)
            deltaY = 1.3f * scaleY(mFace!!.height / 2)
            mask = BitmapFactory.decodeResource(context.resources, R.drawable.dog)
        } else if (maskType == MaskType.MASK) {
            deltaX = 1.3f * scaleX(mFace!!.width / 2)
            deltaY = 1.3f * scaleY(mFace!!.height / 2)
            mask = BitmapFactory.decodeResource(context.resources, R.drawable.mask)
        } else if (maskType == MaskType.HAT) {
            deltaX = 1.70f * scaleX(mFace!!.width / 2)
            deltaY = 1.40f * scaleY(mFace!!.height / 2)
            mask = BitmapFactory.decodeResource(context.resources, R.drawable.hat)
        } else if (maskType == MaskType.GLASSES1) {
            deltaX = 1.7f * scaleX(mFace!!.width / 2)
            deltaY = 1.6f * scaleY(mFace!!.height / 2)
            mask = BitmapFactory.decodeResource(context.resources, R.drawable.glasses1)
        } else if (maskType == MaskType.GLASSES2) {
            deltaX = 1.4f * scaleX(mFace!!.width / 2)
            deltaY = 1.2f * scaleY(mFace!!.height / 2)
            mask = BitmapFactory.decodeResource(context.resources, R.drawable.glasses2)
        } else {
            deltaX = 1f * scaleX(mFace!!.width / 2)
            deltaY = 1.3f * scaleY(mFace!!.height / 2)
            mask = BitmapFactory.decodeResource(context.resources, R.drawable.bear)
        }
        val left = (x - deltaX).toInt()
        val top = (y - deltaY).toInt()
        val right = (x + deltaX).toInt()
        val bottom = (y + deltaY).toInt()
        val destBounds = Rect(left, top, right, bottom)
        canvas?.drawBitmap(mask, null, destBounds, null)
    }
}