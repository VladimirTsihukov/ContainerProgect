package com.androidapp.containerprogect.coroutinTesting

import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MyClassTest : TestCase() {

    private val myClass = MyClass()

    private val testDispatchers = TestCoroutineDispatcher()

    @Before
    fun setUpCoroutine() {
        Dispatchers.setMain(testDispatchers)
    }

    @Test
    fun testSomeMethod() {
        println("Test start")
        runBlocking {
            println("Launch")
            val actualValue = myClass.someMethod()
            assertEquals("abc", actualValue)
        }
        println("Test end")
    }

    @Test
    fun testSomeMethodRunBlockingTest() {
        println("Test start")
        runBlockingTest {
            println("Launch")
            val actualValue = myClass.someMethod()
            assertEquals("abc", actualValue)
        }
        println("Test end")
    }

    @Test
    fun testTest() = runBlockingTest(testDispatchers) {
            myClass.fetchData()
            assertTrue(myClass.showLoading)
            assertFalse(myClass.showLoading)
        }

    @After
    fun tearDownCoroutine() {
        Dispatchers.resetMain()
        testDispatchers.cleanupTestCoroutines()
    }

}