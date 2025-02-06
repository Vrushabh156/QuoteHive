package com.vrushabh.quotehive.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vrushabh.quotehive.R
import com.vrushabh.quotehive.ui.viewmodels.CategoryViewModel

@Composable
fun CategoryScreen(onClick: (category: String) -> Unit) {
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val categories: State<List<String>> = categoryViewModel.categories.collectAsState()

    // Define a list of colors for the categories
    val colors = listOf(
        Color(0xFF1bb2b2),
        Color(0xFFff7544),
        Color(0xFFfa5a7e),
        Color(0xFF8676fe),
        Color(0xFF1bb2b2),
        Color(0xFF4278df)
    )

    if (categories.value.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...", style = MaterialTheme.typography.headlineSmall)
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            val indexedCategories = categories.value.distinct().withIndex().toList()

            items(indexedCategories) { indexedCategory ->
                val index = indexedCategory.index
                val category = indexedCategory.value
                // Use the index to select a color from the list
                val color = colors[index % colors.size]
                CategoryItem(category = category, color = color, onClick)
            }
        }
    }
}

@Composable
fun CategoryItem(category: String, color: Color, onClick: (category: String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onClick(category)
            }
            .size(100.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color)
            .border(1.dp, Color(0xFFEEEEEE)),
        contentAlignment = Alignment.BottomStart
    ) {
        Text(
            text = category,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(15.dp, 15.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
