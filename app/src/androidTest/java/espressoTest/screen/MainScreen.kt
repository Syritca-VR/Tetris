package espressoTest.screen

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.myfirstapp.test.tetris.MainActivity
import com.example.myfirstapp.test.tetris.R
import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.components.kautomator.screen.UiScreen
import org.hamcrest.Matcher

object MainScreen: UiScreen<MainScreen>() {
    override val packageName: String = "com.example.myfirstapp.test.tetris"

    val gameName = UiTextView {withText("TETRIS")}
    val highScore = UiTextView {withId("com.example.myfirstapp.test.tetris",
        "tv_high_score")}
    val newGameButton = UiTextView {withText("NEW GAME")}
    val resetScoreButton = UiTextView {withText("RESET SCORE")}
    val exitButton = UiTextView {withText("EXIT")}
    private val highScoreText: ViewInteraction = onView (withId(R.id.tv_high_score))
    val highScoreGetText = getText(highScoreText)

    fun getText(matcher: ViewInteraction): String {
        var text = String()
        matcher.perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(TextView::class.java)
            }

            override fun getDescription(): String {
                return "Text of the view"
            }

            override fun perform(uiController: UiController?, view: View?) {
                val tv = view as TextView
                text = tv.text.toString()
            }
        })

        return text
    }
}