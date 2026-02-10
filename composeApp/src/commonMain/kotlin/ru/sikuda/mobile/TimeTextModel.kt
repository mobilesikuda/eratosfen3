package ru.sikuda.mobile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.measureTime
import kotlin.time.measureTimedValue


class TimeTextModel: ViewModel() {
    private val timeTextState = MutableStateFlow("")
    val timeText: StateFlow<String> = timeTextState
    var fReadyWorking = true //working only once

    fun calcAsync() {

        if (!fReadyWorking) return

        viewModelScope.launch {
            fReadyWorking = false
            timeTextState.value = "ASync calc..."

            val (_, duration) = measureTimedValue {
                calculateAsync()
            }
            timeTextState.value = "ASync calc: $duration"
            fReadyWorking = true
        }
    }

    fun calcSync() {
        timeTextState.value = "Sync calc..."
        val interval = measureTime {
            calculate()
        }
        timeTextState.value = "Sync calc: $interval"
    }

    private fun calculate() {

        val n = 50_000_000
        //val n = 5_000_000
        //val n = 5_000
        val array: Array<Int> = Array(n + 1) { 1 }
        array[0] = 0
        array[1] = 0

        var i = 2
        while (i <= n) {
            if (array[i] == 1) {
                val sq: Long = i.toLong() * i
                if (sq <= n) {
                    var m: Int = sq.toInt()
                    while (m <= n) {
                        array[m] = 0
                        m += i
                    }
                }
            }
            i += 1
        }
    }

    suspend fun calculateAsync() {
        withContext(Dispatchers.Default) {
            calculate()
            //delay(1000L) // pretend we are doing something useful here
        }
    }
}