package com.resurrection.appbase.data.dummy

import com.resurrection.appbase.R
import com.resurrection.appbase.data.model.navigation.NavigationDirections
import com.resurrection.appbase.data.model.sample.SampleChildItemModel
import com.resurrection.appbase.data.model.sample.SampleModel

val samplesDummyData = arrayListOf<SampleModel>(
    SampleModel(
        title = "Passengers",
        description = "Passengers coming from REST API and" +
                " REST API including GET, POST, DELETE, PUT, PATCH." +
                " Additionally, This API is accessible with HTTPS too." +
                " All the endpoints have separate documentation on this page:" +
                " https://www.instantwebtools.net/fake-rest-api",
        examples = listOf(SampleChildItemModel(
            name = "First Sample",
            navigationDirections = NavigationDirections(R.id.passengerFragment)
        ))
    )
)