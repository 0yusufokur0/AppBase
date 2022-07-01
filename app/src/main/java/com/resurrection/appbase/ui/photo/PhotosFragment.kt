package com.resurrection.appbase.ui.photo

import android.os.Bundle
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.FragmentPhotosBinding
import com.resurrection.base.core.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding, PhotosViewModel>
    (R.layout.fragment_photos, PhotosViewModel::class.java) {

    override fun init(savedInstanceState: Bundle?) {



    }
    // read external storage permission




}

class ASD:XXX{
    override val asd: String
        get() {
            super.asd = "asd"
            return  ""
        }
    var asd
        get() {
            return "sşdflıg"
        }
}

interface XXX {
}