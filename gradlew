package learning.mahmoudmabrok.englishtime.feature.feature.formSentace

import android.content.Context
import android.os.*
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_form_sentence.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB
import learning.mahmoudmabrok.englishtime.feature.models.Sentence
import learning.mahmoudmabrok.englishtime.feature.utils.ListsOpt
import learning.mahmoudmabrok.englishtime.feature.utils.setValue


class FormSentence : AppCompatActivity() {

    private lateinit var db: LocalDB
    private val TAG: String = "FormSentence"
    private lateinit var adapterTop: SentenceAdapter
    private lateinit var adapterBottom: SentenceAdapter
    private lateinit var sentences: MutableList<Sentence>

    var currentSentence = 0
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_sentence)
        setUpSentences()
        initRv()
        loadSentence()
        tvScoreForm.setMessage("Score:: ")

    }

    override fun onResume() {
        super.onResume()
        loadScore()
    }

    private fun loadScore() {
        db = LocalDB.getINSTANCE(this)
        score = db.score
        tvScoreForm.setValue(score, 1500)
    }
    override fun onPause() {
        super.onPause()
        db.score = score
    }

    private fun initRv() {
        // make rv to be filled from user
        adapterTop = SentenceAdapter()
        adapterTop.setSentenceList(listOf())
        rvEnglishTo.setHasFixedSize(true)
        rvEnglishTo.adapter = adapterTop
        val lManager2 = LinearLayoutManager(this)
        lManager2.orientation = RecyclerView.VERTICAL
        rvEnglishTo.layoutManager = lManager2
        // make given rv
        adapterBottom = SentenceAdapter()
        adapterBottom.setSentenceList(listOf())
        rvEnglishFrom.setHasFixedSize(true)
        rvEnglishFrom.adapter = adapterBottom
        val lManager = LinearLayoutManager(this)
        lManager.orientation = RecyclerView.VERTICAL
        rvEnglishFrom.layoutManager = lManager
        // Animations

        // listener for each rv
        adapterBottom.setSentenceListener { pos, item ->
            adapterBottom.removeSentence(pos)
            adapterTop.addSentence(item)
            // check if it be full
            if (adapterBottom.itemCount == 0) {
                val ans = adapterTop.fullSentence
                val q = sentences[currentSentence - 1].enSentence
                if (ans == q) {
                    Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    updateScore(10)
                    loadSentence()
                } else {
                    // get indexes of wrong
                    val waList = adapterTop.list
                            .zip(adapterBottom.originalList)
                            .mapIndexed { index, pair -> if (pair.first == pair.second) -1 else index }

                   //  Toast.makeText(this, "! WA " + waList, Toast.LENGTH_SHORT).show()
                    val a = ListsOpt.getDiff(adapterTop.list,adapterBottom.originalList)
                    Toast.makeText(this, "@ WA " + a, Toast.LENGTH_SHORT).show()
                    adapterTop.setWA(a)
                    object : CountDownTimer(500, 500){
                        override fun onFinish() {
                            // reload data , first decrease currentSentcne to point to question which is now updated
                            // then load which will clear rv and fill bottom one
                            currentSentence--
                            loadSentence()
                        }

                        override fun onTick(millisUntilFinished: Long) {
                        }

                    }.start()
                    vibrate()
                }
            }
        }

        adapterTop.setSentenceListener { pos, item ->
            adapterTop.removeSentence(pos)
            adapterBottom.addSentence(item)
        }

    }

    private fun updateScore(i: Int) {
        score += i
        tvScoreForm.animateTo(score, 500)
        tvScoreForm.updateValue(10,1000)
    }

    private fun setUpSentences() {
        sentences = mutableListOf()
        sentences.add(Sentence("ما عمرك", "How old are you ?"))
        sentences.add(Sentence("ما أسمك", "What is your name ?"))
        sentences.add(Sentence("ما لونك المفضل", "What is your favourite color ?"))
    }

    private fun loadSentence() {
        try {
            // clear them first
            adapterTop.clear()
            adapterBottom.clear()

            val temp = sentences.get(currentSentence)
            tvArSentence.text = temp.arSentence
            // make given words
            val words = temp.enSentence.split(" ")
            adapterBottom.setSentenceList(words)
     