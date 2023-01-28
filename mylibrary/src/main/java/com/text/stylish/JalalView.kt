package com.text.stylish

import android.R.attr.text
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat


/**
 * TODO: document your custom view class.
 */
class JalalView : View {

    private var _exampleString: String? = null // TODO: use a default from R.string...
    private var _exampleColor: Int = Color.RED // TODO: use a default from R.color...
    private var _exampleDimension: Float = 0f  // TODO: use a default from R.dimen...
    private var _fontFamily = ResourcesCompat.getFont(context, R.font.century)
    private var _isAnimateDrawable = true
    private var _AnimSpeed = 0
    private var _isAnimatTextChar = true
    private var _isAnimatTextCharRepeate = false

    private var animateCharCount = 0

    private lateinit var textPaint: TextPaint
    private var textWidth: Float = 0f
    private var textHeight: Float = 0f
    private var myText = ""
    var path = Path()
    var isFullText: Boolean = false

    var maxX = 0
    var maxY = 0

    private var curerentAlpha = 255

    var counters = 0

    private var myCounter = 0


// Need to keep track of the current alp

    val gradient1 = LinearGradient(
        0f, 0f, 0f,
        height.toFloat(),
        ResourcesCompat.getColor(resources, R.color.main1, null),
        ResourcesCompat.getColor(resources, R.color.main2, null),
        Shader.TileMode.REPEAT
    )


    /**
     * The text to draw
     */
    var fontFamily: Typeface?
        get() = _fontFamily
        set(value) {
            _fontFamily = value
            invalidateTextPaintAndMeasurements()
        }


    /**
     * The text to draw
     */
    var exampleString: String?
        get() = _exampleString
        set(value) {
            _exampleString = value
            myText += _exampleString?.get(0)
            invalidateTextPaintAndMeasurements()
        }

    /**
     * The font color
     */
    var exampleColor: Int
        get() = _exampleColor
        set(value) {
            _exampleColor = value
            invalidateTextPaintAndMeasurements()
        }

    /**
     * In the example view, this dimension is the font size.
     */
    var exampleDimension: Float
        get() = _exampleDimension
        set(value) {
            _exampleDimension = value
            invalidateTextPaintAndMeasurements()
        }

    var isAnimate: Boolean
        get() = _isAnimateDrawable
        set(value) {
            _isAnimateDrawable = value
            invalidateTextPaintAndMeasurements()
        }

    var animSpeed: Int
        get() = _AnimSpeed
        set(value) {
            _AnimSpeed = value
            invalidateTextPaintAndMeasurements()
        }

//    var isAnimateChar: Boolean
//        get() = _isAnimatTextChar
//        set(value) {
//            _isAnimatTextChar = value
//            invalidateTextPaintAndMeasurements()
//        }
//
//    var isAnimateCharRep: Boolean
//        get() = _isAnimatTextCharRepeate
//        set(value) {
//            _isAnimatTextCharRepeate = value
//            invalidateTextPaintAndMeasurements()
//        }

    /**
     * In the example view, this drawable is drawn above the text.
     */
    var exampleDrawable: Drawable? = null

    var myCursor: Bitmap? = null

    var dstBmp: Bitmap? = null

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {

        // Load attributes
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.JalalView, defStyle, 0
        )

        _exampleString = a.getString(
            R.styleable.JalalView_mobiPixel_String
        )
        _exampleColor = a.getColor(
            R.styleable.JalalView_mobiPixel_Color,
            exampleColor
        )
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        _exampleDimension = a.getDimension(
            R.styleable.JalalView_mobiPixel_fontSize,
            exampleDimension
        )

        _isAnimateDrawable = a.getBoolean(R.styleable.JalalView_mobiPixel_isAnimateDrawable, true)
        _AnimSpeed = a.getInt(R.styleable.JalalView_mobiPixel_AnimSpeed, 0)

        /* _isAnimatTextChar = a.getBoolean(R.styleable.JalalView_mobiPixel_isAnimateChar, false)
         _isAnimatTextCharRepeate =
             a.getBoolean(R.styleable.JalalView_mobiPixel_isAnimateCharRepeat, false)*/


        if (a.hasValue(R.styleable.JalalView_mobiPixel_Drawable)) {
            exampleDrawable = a.getDrawable(
                R.styleable.JalalView_mobiPixel_Drawable
            )
            exampleDrawable?.callback = this
        }

        val bitmapDrawable = exampleDrawable?.let { it as BitmapDrawable }
        myCursor = (bitmapDrawable)?.bitmap

        myCursor = myCursor?.let { Bitmap.createBitmap(it) }


        a.recycle()

        // Set up a default TextPaint object
        textPaint = TextPaint().apply {
            flags = Paint.ANTI_ALIAS_FLAG
            textAlign = Paint.Align.LEFT
        }


        setWillNotDraw(false)
        isFocusable = true

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements()
    }

    private fun invalidateTextPaintAndMeasurements() {
        textPaint.let {
            it.textSize = exampleDimension
            it.color = exampleColor
            textWidth = it.measureText(exampleString)
            textHeight = it.fontMetrics.bottom
            it.typeface = _fontFamily
            it.strokeWidth = 2f
            textPaint.style = Paint.Style.STROKE
            textPaint.shader = gradient1
            textPaint.alpha = curerentAlpha


            maxX = height - paddingTop - paddingBottom
            maxY = width - paddingLeft - paddingRight

            dstBmp = myCursor?.let { that ->
                Bitmap.createScaledBitmap(
                    that,
                    textWidth.toInt() + 150,
                    that.height + 100,
                    false
                )
            }

        }
    }

    fun setText(str: String) {
        exampleString = str
        invalidate()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // TODO: consider storing these as member variables to reduce

        // allocations per draw cycle.
        val paddingLeft = paddingLeft
        val paddingTop = paddingTop
        val paddingRight = paddingRight
        val paddingBottom = paddingBottom

        val contentWidth = width - paddingLeft - paddingRight
        val contentHeight = height - paddingTop - paddingBottom


        setBackgroundColor(Color.TRANSPARENT)

        exampleString?.let {

            val textLen = it.length

            textPaint.getTextPath(
                exampleString,
                0,
                (textLen),
                0f,
                (contentHeight.toFloat() / 1.5).toFloat(),
                path
            )

            canvas.drawPath(path, textPaint)
        }


        if (isFullText) {
            exampleString?.let {
                canvas.save()
                canvas.clipPath(path)

                counters--

                if (counters == 0) {
                    counters = 0
                    maxX = 0
                    maxY = 0
                    isFullText = false
                }

                if (isAnimate)
                    dstBmp?.let {
                        canvas.drawBitmap(
                            it,
                            (maxX--).toFloat(),
                            (maxY--).toFloat(),
                            textPaint
                        )
                    }
                else
                    dstBmp?.let {
                        canvas.drawBitmap(
                            it,
                            (0).toFloat(),
                            (0).toFloat(),
                            textPaint
                        )
                    }

                canvas.restore()
            }

        } else {
            exampleString?.let {

                canvas.save()
                canvas.clipPath(path)
                counters++

                if (counters == textHeight.toInt()*4) {

                    maxX = counters
                    maxY = counters
                    isFullText = true
                }
                if (isAnimate)
                    dstBmp?.let {
                        canvas.drawBitmap(
                            it,
                            (maxX++).toFloat(),
                            (maxY++).toFloat(),
                            textPaint
                        )
                    }
                else
                    dstBmp?.let {
                        canvas.drawBitmap(
                            it,
                            (0).toFloat(),
                            (0).toFloat(),
                            textPaint
                        )
                    }

                canvas.restore()
            }
        }

        Log.e("customLog", animSpeed.toLong().toString())

        postDelayed(
            {
                if (isAnimate)
                    invalidate()
            },
            animSpeed.toLong()
        )


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val mTextHeight: Int
        val textBounds = Rect()
        exampleString?.let { textPaint.getTextBounds(exampleString, 0, it.length, textBounds) }
        mTextHeight = textBounds.height()

        setMeasuredDimension(
            (textBounds.width()+20),
            (mTextHeight*1.5).toInt()
        )
    }
}


