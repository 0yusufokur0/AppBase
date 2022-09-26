package com.resurrection.appbase.data.model.sample

import androidx.navigation.NavDirections

data class SampleModel(
    val title: String,
    val description: String,
    val firstExample: SampleActionModel?,
    val secondExample: SampleActionModel?,
    val isExpanded:Boolean = false
)

data class SampleActionModel(
    val title: String,
    val navDirections: NavDirections
)