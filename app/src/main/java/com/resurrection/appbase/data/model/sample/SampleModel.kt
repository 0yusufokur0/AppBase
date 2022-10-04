package com.resurrection.appbase.data.model.sample

data class SampleModel(
    val title: String,
    val description: String,
    val examples: List<SampleChildItemModel>,
    var isExpanded:Boolean = false
)
