package com.example.myquizapp

object Constants {

    fun getQuestion(): ArrayList<Question> {
        val questionList = ArrayList<Question>()

        val que1 = Question(
            1, "what country does this flag belong to", R.drawable.ic_flag_of_argentina,
            "Belgium",
            "Argentina",
            "Australia",
            "Canada",
            2,
        )
        questionList.add(que1)

        val que2 = Question(
            2, "what country does this flag belong to", R.drawable.ic_flag_of_brazil,
            "Brazil",
            "Argentina",
            "Australia",
            "Canada",
            1,
        )
        questionList.add(que2)

        val que3 = Question(
            3, "what country does this flag belong to", R.drawable.ic_flag_of_germany,
            "Brazil",
            "Argentina",
            "Australia",
            "Germany",
            4,
        )
        questionList.add(que3)

        val que4 = Question(
            4, "what country does this flag belong to", R.drawable.ic_flag_of_fiji,
            "Brazil",
            "Argentina",
            "Australia",
            "Fiji",
            4,
        )
        questionList.add(que4)

        val que5 = Question(
            5, "what country does this flag belong to", R.drawable.ic_flag_of_kuwait,
            "Kuwait",
            "Argentina",
            "Australia",
            "Fiji",
            1,
        )
        questionList.add(que5)

        return questionList
    }
}