package com.example.myquizapp
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.myquizapp.R.drawable.default_option_border_bg
import com.example.myquizapp.R.drawable.default_selected_border_bg

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null

    private var mSelectedOptionPosition: Int = 0
    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var ivImage: ImageView? = null
    private var tvQuestion: TextView? = null
    private var tvOption1: TextView? = null
    private var tvOption2: TextView? = null
    private var tvOption3: TextView? = null
    private var tvOption4: TextView? = null
    private var btnSubmit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tvProgress)
        ivImage = findViewById(R.id.ivImage)
        tvQuestion = findViewById(R.id.tvQuestion)
        tvOption1 = findViewById(R.id.tvOption1)
        tvOption2 = findViewById(R.id.tvOption2)
        tvOption3 = findViewById(R.id.tvOption3)
        tvOption4 = findViewById(R.id.tvOption4)
        btnSubmit = findViewById(R.id.btnSubmit)

        tvOption1?.setOnClickListener(this)
        tvOption2?.setOnClickListener(this)
        tvOption3?.setOnClickListener(this)
        tvOption4?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        mQuestionList = Constants.getQuestion()

        setQuestion()
        defaultOptionsView()
    }

    private fun setQuestion() {

        defaultOptionsView()

        val question: Question = mQuestionList!![mCurrentPosition - 1]
        tvQuestion?.text = question.question
        ivImage?.setImageResource(question.image)
        tvProgress?.text = "$mCurrentPosition /${progressBar?.max} "
        progressBar?.progress = mCurrentPosition
        tvOption1?.text = question.optionOne
        tvOption2?.text = question.optionTwo
        tvOption3?.text = question.optionThree
        tvOption4?.text = question.optionFour

        if (mCurrentPosition == mQuestionList!!.size) {
            btnSubmit?.text = getString(R.string.finish)
        } else {
            btnSubmit?.text = getString(R.string.submit)
        }
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        tvOption1?.let {
            options.add(0, it)
        }
        tvOption2?.let {
            options.add(1, it)
        }
        tvOption3?.let {
            options.add(2, it)
        }
        tvOption4?.let {
            options.add(3, it)
        }
        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this, R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.default_selected_border_bg
        )
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tvOption1 -> {
                tvOption1?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.tvOption2 -> {
                tvOption2?.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.tvOption3 -> {
                tvOption3?.let {
                    selectedOptionView(it, 3)
                }
            }

            R.id.tvOption4 -> {
                tvOption4?.let {
                    selectedOptionView(it, 4)
                }
            }

            R.id.btnSubmit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else ->{
                            Toast.makeText(this, "You made it to the end", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionList!!.size){
                        btnSubmit?.text = "FINISH"
                    }else{
                        btnSubmit?.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0

                }
            }
        }
    }
        private fun answerView(answer: Int, drawableView: Int) {
            when (answer) {
                1 -> {
                    tvOption1?.background = ContextCompat.getDrawable(
                        this,
                        drawableView
                    )
                }
                2 -> {
                    tvOption2?.background = ContextCompat.getDrawable(
                        this,
                        drawableView
                    )
                }
                3 -> {
                    tvOption3?.background = ContextCompat.getDrawable(
                        this,
                        drawableView
                    )
                }
                4 -> {
                    tvOption4?.background = ContextCompat.getDrawable(
                        this,
                        drawableView
                    )
                }
            }
        }
    }
