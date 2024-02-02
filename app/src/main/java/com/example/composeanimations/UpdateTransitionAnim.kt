package com.example.composeanimations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp

@Composable
fun FAQsScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Frequently Asked Questions",
                style = MaterialTheme.typography.headlineLarge
            )
        }
        items(5) { index ->
            FAQCard(
                question = "Question ${index + 1} : What is Lorem ipsum? we see everywhere.",
                answer = "Lorem ipsum is placeholder text commonly used in the graphic, print," +
                        " and publishing industries for previewing layouts and visual mockups"
            )
        }
    }
}

@Composable
fun FAQCard(
    modifier: Modifier = Modifier,
    question: String,
    answer: String
) {
    var isExpanded by remember { mutableStateOf(false) }

    val transition = updateTransition(targetState = isExpanded, label = "")
    val iconRotationDegree by transition.animateFloat(label = "") { expandedState ->
        if (expandedState) 180f else 0f
    }
    val titleColor by transition.animateColor(label = "") { expandedState ->
        if (expandedState) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.onSurface
    }
    val backgroundColorAlpha by transition.animateFloat(label = "") { expandedState ->
        if (expandedState) 1f else 0.5f
    }
    Card(
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = { isExpanded = !isExpanded }
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = backgroundColorAlpha)
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(9f),
                    text = question,
                    style = MaterialTheme.typography.titleMedium,
                    color = titleColor
                )
                Icon(
                    modifier = Modifier
                        .weight(1f)
                        .rotate(iconRotationDegree),
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Expand Card",
                    tint = MaterialTheme.colorScheme.onSurface.copy(backgroundColorAlpha)
                )
            }
            AnimatedVisibility(visible = isExpanded) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = answer,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}