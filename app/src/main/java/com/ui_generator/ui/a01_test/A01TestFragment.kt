package com.ui_generator.ui.a01_test

import com.ui_generator.R
import com.ui_generator.databinding.FragmentA01TestBinding
import com.ui_generator.ui.base.BindingFragment

class A01TestFragment : BindingFragment<FragmentA01TestBinding, A01TestViewModel>(
        A01TestViewModel::class.java, layoutRes = R.layout.fragment_a01_test
) {


    override fun initView() {

        renderView { binding ->
        }
    }


    override fun initData() {

    }
}