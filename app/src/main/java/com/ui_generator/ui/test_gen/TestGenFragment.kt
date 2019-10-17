package com.ui_generator.ui.test_gen

import com.ui_generator.R
import com.ui_generator.databinding.FragmentTestGenBinding
import com.ui_generator.ui.base.BindingFragment

class TestGenFragment : BindingFragment<FragmentTestGenBinding, TestGenViewModel>(
        TestGenViewModel::class.java, layoutRes = R.layout.fragment_test_gen
) {


    override fun initView() {

        renderView { binding ->
        }
    }


    override fun initData() {

    }
}