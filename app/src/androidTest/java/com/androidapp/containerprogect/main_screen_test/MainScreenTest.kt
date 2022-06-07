package com.androidapp.containerprogect.main_screen_test

import android.util.Log
import androidx.test.rule.ActivityTestRule
import com.androidapp.containerprogect.MainActivity
import com.androidapp.containerprogect.TAG_TEST
import com.androidapp.containerprogect.main_screen_test.screen.FragmentScreen
import com.androidapp.containerprogect.main_screen_test.screen.MainScreen
import com.androidapp.containerprogect.main_screen_test.screen.SecondScreen
import com.androidapp.containerprogect.main_screen_test.screen.ThirdScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class MainScreenTest : TestCase() {

    @get: Rule
    val activityMainTest = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun openMainActivityTest() =
        before {
            activityMainTest.launchActivity(null)
            Log.i(TAG_TEST, "Before test")
        }.after {
            Log.i(TAG_TEST, "Close test")
        }.run {
            step("Click btn") {
                MainScreen {
                    btnMain {
                        isVisible()
                    }
                    btnMain {
                        click()
                    }
                }
            }
        }

    @Test
    fun navigateToThirdActivity() =
        before {
            activityMainTest.launchActivity(null)
            Log.i(TAG_TEST, "Before test")
        }.after {
            Log.i(TAG_TEST, "Close test")
        }.run {
            step("Click btn") {
                MainScreen {
                    btnMain { isVisible() }
                    btnMain { click() }
                }
            }

            step("Check second activity") {
                SecondScreen {
                    btnSecondActivity { isVisible() }
                    btnSecondActivity { click() }
                }
            }

            step("Visible toolbar third Activity") {
                ThirdScreen {
                    toolbar {
                        isVisible()
                    }
                }
            }

            step("Check third activity") {
                FragmentScreen {
                    button { isVisible() }
                    button { click() }
                }
            }

            step("Check main screen") {
                SecondScreen {
                    btnSecondActivity {
                        flakySafely(timeoutMs = 15_000) { isVisible() }
                    }
                }
            }
        }
}