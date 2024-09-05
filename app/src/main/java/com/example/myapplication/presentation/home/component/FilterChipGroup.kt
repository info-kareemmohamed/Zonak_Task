package com.example.myapplication.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun FilterChipGroup(
    chips: List<String>,
    selectedChip: String?, onChipSelected: (String) -> Unit
) {


    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items(chips) { chip ->
            val isSelected = chip == selectedChip
            FilterChipItem(
                chipName = chip,
                isSelected = isSelected,
                onChipClicked = {
                    onChipSelected(chip)
                }
            )
        }
    }
}


@Composable
fun FilterChipItem(
    chipName: String,
    isSelected: Boolean,
    onChipClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(end = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onChipClicked)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = chipName,
            color = if (isSelected) Color.White else Color.Black,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilterChipGroupPreview() {
    val chips =
        listOf("business", "entertainment", "general", "health", "science", "sports", "technology")

    val selectedChip = remember { mutableStateOf<String?>("business") }

    FilterChipGroup(
        chips = chips,
        selectedChip = selectedChip.value,
        onChipSelected = { chip ->
            selectedChip.value = chip
        }
    )
}