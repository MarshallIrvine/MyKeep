package com.marshall.irvine.mykeep.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marshall.irvine.mykeep.R
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@Composable
fun EmptyNotesScreen(bottomSheetState: BottomSheetState) {
    val scope = rememberCoroutineScope()
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            painter = painterResource(id = R.drawable.file_searching_rafiki),
            contentDescription = null,
            modifier = Modifier
                .padding(20.dp)
        )
        ExtendedFloatingActionButton(
            onClick = {
                scope.launch {
                    if (bottomSheetState.isCollapsed) {
                        bottomSheetState.expand()
                    } else {
                        bottomSheetState.collapse()
                    }
                }
            },
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomEnd),
            icon = {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add"
                )
            },
            text = {
                Text(text = "Nouvelle note")
            }
        )
    }
}

