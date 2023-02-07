package com.marshall.irvine.mykeep.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.marshall.irvine.mykeep.domain.model.Note
import com.marshall.irvine.mykeep.ui.viewmodel.NoteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@Composable
fun NoteListScreen(noteViewModel: NoteViewModel) {
    val bottomSheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )
    val scope = rememberCoroutineScope()

    var noteText by remember {
        mutableStateOf("")
    }
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Row {
                    Text(
                        text = "Nouvelle note",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(
                        onClick = {
                            scope.launch {
                                bottomSheetState.collapse()
                            }
                        },
                    ) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "Close"
                        )
                    }

                }

                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = noteText,
                    onValueChange = {
                        noteText = it
                    },
                    label = {
                        Text(text = "Votre note")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    if (noteText != "") {
                        noteViewModel.addNewNote(Note(content = noteText))
                        noteText = ""
                        scope.launch {
                            bottomSheetState.collapse()
                        }
                    }
                }) {
                    Text(
                        text = "Ajouter"
                    )
                }

            }
        },
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Vos notes !",
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(20.dp)
            )
            val notes = noteViewModel.notesList.collectAsState(initial = listOf())
            if (notes.value.isEmpty()) {
                EmptyNotesScreen(bottomSheetState)
            } else {
                ListOfNotesContainer(noteViewModel = noteViewModel, bottomSheetState, scope)
            }
        }

    }
}

@ExperimentalMaterialApi
@Composable
fun ListOfNotesContainer(
    noteViewModel: NoteViewModel,
    bottomSheetState: BottomSheetState,
    scope: CoroutineScope
) {
    val notes = noteViewModel.notesList.collectAsState(initial = listOf())
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(notes.value) { note ->
                NoteListItem(note = note, noteViewModel)
            }
        }
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


@Composable
fun NoteListItem(note: Note, noteViewModel: NoteViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = note.content,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .clickable(
                        enabled = true,
                        onClick = {

                        }
                    )
            )

            IconButton(
                onClick = {
                    noteViewModel.delete(note)
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)

            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "R.string.app_name",
                    tint = Color.Red
                )
            }


        }
    }

}
