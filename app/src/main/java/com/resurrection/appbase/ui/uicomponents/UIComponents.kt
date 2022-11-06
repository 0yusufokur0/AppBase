package com.resurrection.appbase.ui.uicomponents

import android.os.Bundle
import android.view.View
import com.resurrection.appbase.R
import com.resurrection.appbase.databinding.FragmentUiComponentsBinding
import com.resurrection.base.core.fragment.CoreFragment
import com.resurrection.base.extensions.delegated.viewdatabinding.viewBinding

class UIComponents : CoreFragment(R.layout.fragment_ui_components) {

    val binding by viewBinding(FragmentUiComponentsBinding::inflate)

    override fun init(view: View, savedInstanceState: Bundle?) {
    }

}