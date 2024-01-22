package espressoTest.test

import android.app.Activity
import android.util.Log
import android.widget.TextView
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myfirstapp.test.tetris.MainActivity
import com.kaspersky.components.alluresupport.withAllureSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import io.qameta.allure.kotlin.junit4.DisplayName
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.myfirstapp.test.tetris.R
import espressoTest.screen.MainScreen

@RunWith(AndroidJUnit4::class)
class MainScreenTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder
        .withAllureSupport()
) {

    /*@get: Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )*/

    @get : Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    @DisplayName("RefreshHighScore")
    fun test1() {
        run {
            step ("Check high score") {
                Log.d("Score1", MainScreen.highScoreGetText)
                MainScreen.highScore.hasText("High score: 0")
            }
        }
    }

}