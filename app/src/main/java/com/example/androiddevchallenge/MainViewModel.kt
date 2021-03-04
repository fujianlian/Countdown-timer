/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var hour by mutableStateOf(0)
    var minute by mutableStateOf(0)
    var second by mutableStateOf(0)
    var canDownTimer by mutableStateOf(true)
    private var countDownTimer: CountDownTimer? by mutableStateOf(null)

    fun startDownTimer() {
        if (canDownTimer) {
            canDownTimer = false
            val totalTime = (hour * 3600 + minute * 60 + second) * 1000
            countDownTimer = object : CountDownTimer(totalTime.toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val time = millisUntilFinished / 1000
                    hour = (time / 3600).toInt()
                    minute = ((time - hour * 3600) / 60).toInt()
                    second = (time % 60).toInt()
                }

                override fun onFinish() {
                    canDownTimer = true
                }
            }
            countDownTimer?.start()
        }
    }

    fun cancelDownTimer() {
        if (countDownTimer != null) {
            countDownTimer?.cancel()
            countDownTimer = null
            canDownTimer = true
            hour = 0
            minute = 0
            second = 0
        }
    }

    fun canStart(): Boolean {
        return hour > 0 || minute > 0 || second > 0
    }

    override fun onCleared() {
        super.onCleared()
        cancelDownTimer()
    }
}
