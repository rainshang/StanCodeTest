package com.example.android.pictureinpicture

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(JUnit4::class)
class MainViewModelTest {
    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    private val uptimeProvider: MainViewModel.UptimeProvider = mock {
        on { uptimeMillis() } doReturn System.currentTimeMillis()
    }
    private lateinit var viewModel: MainViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = MainViewModel(uptimeProvider)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `start and pause work as expectation`() {
        viewModel.startOrPause()
        Assert.assertEquals(viewModel.started.value, true)
        viewModel.startOrPause()
        Assert.assertEquals(viewModel.started.value, false)
    }

    @Ignore("Dispatchers.Unconfined.dispatch function can only be used by the yield function. If you wrap Unconfined dispatcher in your code, make sure you properly delegate isDispatchNeeded and dispatch calls")
    @Test
    fun `timer can work`() {
        whenever(uptimeProvider.uptimeMillis()) doReturn (1000L)
        viewModel.startOrPause()
        Assert.assertEquals(viewModel.time.value, "00:00:01")
    }
}