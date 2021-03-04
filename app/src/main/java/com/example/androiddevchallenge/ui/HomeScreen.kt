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
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.MainViewModel
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun HomeScreen(viewModel: MainViewModel = viewModel()) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp, vertical = 15.dp)
        ) {
            SetClickText(true, 1, viewModel)
            SetClickText(true, 2, viewModel)
            SetClickText(true, 3, viewModel)
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SetText("${viewModel.hour}")
            Text(":")
            SetText("${viewModel.minute}")
            Text(":")
            SetText("${viewModel.second}")
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp, vertical = 15.dp)
        ) {
            SetClickText(false, 1, viewModel)
            SetClickText(false, 2, viewModel)
            SetClickText(false, 3, viewModel)
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp)
        ) {
            Button(
                {
                    if (viewModel.canDownTimer) {
                        if (viewModel.canStart()) {
                            viewModel.startDownTimer()
                        }
                    } else {
                        viewModel.cancelDownTimer()
                    }
                },
                enabled = viewModel.canStart()
            ) {
                Text(if (viewModel.canDownTimer) "Start" else "Cancel")
            }
        }
    }
}

@Composable
fun SetText(text: String) {
    return Text(text, Modifier.width(80.dp), textAlign = TextAlign.Center)
}

@Composable
fun SetClickText(isAdd: Boolean, type: Int, viewModel: MainViewModel) {
    return Button(
        {
            if (type == 1) {
                if (isAdd) {
                    viewModel.hour += 1
                } else if (viewModel.hour > 0) {
                    viewModel.hour -= 1
                }
            } else if (type == 2) {
                if (isAdd && viewModel.minute < 60) {
                    viewModel.minute += 1
                } else if (!isAdd && viewModel.minute > 0) {
                    viewModel.minute -= 1
                }
            } else {
                if (isAdd && viewModel.second < 60) {
                    viewModel.second += 1
                } else if (!isAdd && viewModel.second > 0) {
                    viewModel.second -= 1
                }
            }
        },
        enabled = viewModel.canDownTimer
    ) {
        Text(if (isAdd) "+" else "-", fontSize = 16.sp)
    }
}

@Preview
@Composable
fun Show() {
    MyTheme {
        HomeScreen()
    }
}
