package com.example.myfirstapp.test.tetris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.view.MotionEvent
import com.example.myfirstapp.test.tetris.storage.AppPreferences
import android.view.View
import com.example.myfirstapp.test.tetris.view.TetrisView
import com.example.myfirstapp.test.tetris.models.AppModel

class GameActivity : AppCompatActivity() {

    var tvHighScore: TextView? = null
    var tvCurrentScore: TextView? = null

    private lateinit var tetrisView: TetrisView
    var appPreferences: AppPreferences? = null
    private val appModel = AppModel()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        appPreferences = AppPreferences(this)
        appModel.setPreferences(appPreferences)

        val btnRestart = findViewById<Button>(R.id.btn_restart)
        tvHighScore = findViewById<TextView>(R.id.tv_high_score)
        tvCurrentScore = findViewById<TextView>(R.id.tv_current_score)
        tetrisView = findViewById<TetrisView>(R.id.view_tetris)
        tetrisView.setActivity(this)
        tetrisView.setModel(appModel)
        tetrisView.setOnTouchListener(this::onTetrisViewTouch)
        btnRestart.setOnClickListener(this::btnRestartClick)
        updateHighScore()
        updateCurrentScore()
    }

    private fun btnRestartClick(view: View) {
        appModel.restartGame()
    }

    private fun  onTetrisViewTouch(view: View, event: MotionEvent): Boolean {
        if (appModel.isGameOver() || appModel.isGameAwaitingStart()) {
            appModel.startGame()
            tetrisView.setGameCommandWithDelay(AppModel.Motions.DOWN)
        } else if (appModel.isGameActive()) {
            when (resolveTouchDirection(view, event)) {
                0 -> moveTeromino(AppModel.Motions.LEFT)
                1 -> moveTeromino(AppModel.Motions.ROTATE)
                2 -> moveTeromino(AppModel.Motions.DOWN)
                3 -> moveTeromino(AppModel.Motions.RIGHT)
            }
        }
        return true
    }

    private fun resolveTouchDirection(view: View, event: MotionEvent): Int {
        val x = event.x / view.width
        val y = event.y / view.height
        val direction: Int
        direction = if (y > x) {
            if (x > 1 - y) 2 else 0
        } else {
            if (x > 1 - y) 3 else 1
        }
        return direction
    }

    private fun moveTeromino(motion: AppModel.Motions) {
        if (appModel.isGameActive()) {
            tetrisView.setGameCommand(motion)
        }
    }

    private fun updateHighScore() {
        tvHighScore?.text = "${appPreferences?.getHighScore()}"
    }

    private fun updateCurrentScore() {
        tvCurrentScore?.text = "0"
    }

}