package com.vasilevskii.testandroiddev.ui.view

import android.content.Context
import android.graphics.*
import android.view.View


class CustomAlertView(context: Context) : View(context) {

    private lateinit var paintLine: Paint

    private lateinit var paintPointOne: Paint
    private lateinit var paintPointTwo: Paint
    private lateinit var paintPointThree: Paint
    private lateinit var paintPointFour: Paint

    private lateinit var paintTextTitle: Paint
    private lateinit var paintTextOnePoint: Paint
    private lateinit var paintTextTwoPoint: Paint
    private lateinit var paintTextThreePoint: Paint
    private lateinit var paintTextFourPoint: Paint

    companion object{
        const val HEIGHT_VIEW = 400

        const val PROGRESS_LINE_START_Y = 200F
        const val PROGRESS_LINE_START_X = 50F

        const val TEXT_TITLE_START_Y = 100F
        const val TEXT_TITLE_START_X_MINUS = 150F
        const val PROGRESS_TEXT_START_Y = 250F
        const val PROGRESS_TEXT_START_X = 10F

        const val PROGRESS_TEXT_TWO_VALUE_MINUS = 50F
        const val PROGRESS_TEXT_THREE_VALUE_MINUS = 40F
        const val PROGRESS_TEXT_FOUR_VALUE_MINUS = 50F

        const val RADIUS_POINT = 20F

        const val TEXT_ONE_POINT = "First"
        const val TEXT_TWO_POINT = "Second"
        const val TEXT_THREE_POINT = "Third"
        const val TEXT_FOUR_POINT = "Fourth"
        const val TEXT_TITLE = "Custom Alert"
    }


    init{
        initLine()

        initPointOne()
        initPointTwo()
        initPointThree()
        initPointFour()

        initTextTitlePoint()
        initTextOnePoint()
        initTextTwoPoint()
        initTextThreePoint()
        initTextFourPoint()
    }



    private fun initLine() {
        paintLine = Paint()
        paintLine.color = Color.BLACK
        paintLine.strokeWidth = 5F
    }

    private fun initPointOne() {
        paintPointOne = Paint()
        paintPointOne.color = Color.BLUE
        paintPointOne.style = Paint.Style.FILL
    }

    private fun initPointTwo() {
        paintPointTwo = Paint()
        paintPointTwo.color = Color.RED
        paintPointTwo.style = Paint.Style.FILL_AND_STROKE
    }

    private fun initPointThree() {
        paintPointThree = Paint()
        paintPointThree.color = Color.GREEN
        paintPointThree.style = Paint.Style.STROKE
    }

    private fun initPointFour() {
        paintPointFour = Paint()
        paintPointFour.color = Color.DKGRAY
        paintPointFour.style = Paint.Style.STROKE
    }

    private fun initTextTitlePoint() {
        paintTextTitle = Paint()
        paintTextTitle.color = Color.BLACK
        paintTextTitle.style = Paint.Style.FILL
        paintTextTitle.textSize = 50F
    }


    private fun initTextOnePoint() {
        paintTextOnePoint = Paint()
        paintTextOnePoint.color = Color.DKGRAY
        paintTextOnePoint.style = Paint.Style.FILL
        paintTextOnePoint.textSize = 30F
    }

    private fun initTextTwoPoint() {
        paintTextTwoPoint = Paint()
        paintTextTwoPoint.color = Color.RED
        paintTextTwoPoint.style = Paint.Style.FILL
        paintTextTwoPoint.textSize = 30F
    }

    private fun initTextThreePoint() {
        paintTextThreePoint = Paint()
        paintTextThreePoint.color = Color.DKGRAY
        paintTextThreePoint.style = Paint.Style.FILL
        paintTextThreePoint.textSize = 30F
    }

    private fun initTextFourPoint() {
        paintTextFourPoint = Paint()
        paintTextFourPoint.color = Color.DKGRAY
        paintTextFourPoint.style = Paint.Style.FILL
        paintTextFourPoint.textSize = 30F
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawText(TEXT_TITLE, width / 2 - TEXT_TITLE_START_X_MINUS, TEXT_TITLE_START_Y, paintTextTitle)

        canvas?.drawLine(PROGRESS_LINE_START_X, PROGRESS_LINE_START_Y, width - PROGRESS_LINE_START_X, PROGRESS_LINE_START_Y, paintLine)

        canvas?.drawCircle(PROGRESS_LINE_START_X, PROGRESS_LINE_START_Y, RADIUS_POINT, paintPointOne)
        canvas?.drawCircle(getStartXPointTwo(PROGRESS_LINE_START_X), PROGRESS_LINE_START_Y, RADIUS_POINT, paintPointTwo)
        canvas?.drawCircle(getStartXPointThree(PROGRESS_LINE_START_X), PROGRESS_LINE_START_Y, RADIUS_POINT, paintPointThree)
        canvas?.drawCircle(getStartXPointFour(PROGRESS_LINE_START_X), PROGRESS_LINE_START_Y, RADIUS_POINT, paintPointFour)

        canvas?.drawText(TEXT_ONE_POINT, PROGRESS_TEXT_START_X , PROGRESS_TEXT_START_Y, paintTextOnePoint)
        canvas?.drawText(TEXT_TWO_POINT, getStartXPointTwo(PROGRESS_LINE_START_X) - PROGRESS_TEXT_TWO_VALUE_MINUS , PROGRESS_TEXT_START_Y, paintTextTwoPoint)
        canvas?.drawText(TEXT_THREE_POINT, getStartXPointThree(PROGRESS_LINE_START_X) - PROGRESS_TEXT_THREE_VALUE_MINUS , PROGRESS_TEXT_START_Y, paintTextThreePoint)
        canvas?.drawText(TEXT_FOUR_POINT, getStartXPointFour(PROGRESS_LINE_START_X) - PROGRESS_TEXT_FOUR_VALUE_MINUS , PROGRESS_TEXT_START_Y, paintTextFourPoint)

    }

    private fun getStartXPointTwo(startX: Float): Float{
        return (width - 2 * startX) / 3 + startX
    }

    private fun getStartXPointThree(startX: Float): Float {
        return width - getStartXPointTwo(startX)
    }

    private fun getStartXPointFour(startX: Float): Float {
        return width - startX
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, HEIGHT_VIEW)
    }
}