package com.ui_generator.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.*


abstract class BindingFragment<Bind : ViewDataBinding, VM : ViewModel>
constructor(private val clazz: Class<VM>, private val bindingCreator: (LayoutInflater, ViewGroup?) -> Bind)
    : Fragment() {

    constructor(clazz: Class<VM>, @LayoutRes layoutRes: Int) : this(clazz, { inflater, group ->
        DataBindingUtil.inflate(inflater, layoutRes, group, false)
    })

    val viewModel: VM by lazy { ViewModelProviders.of(this).get(clazz) }

    lateinit var binding: Bind

    //method

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = bindingCreator.invoke(layoutInflater, container)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initData()
    }

    abstract fun initView()

    abstract fun initData()

    protected fun renderView(render: (Bind) -> (Unit)) {
        render.invoke(binding)
    }



    open fun initDataAfterAnim() {

    }

    open fun initViewAfterAnim() {

    }

    //ext

    protected fun <T> LiveData<T>.observe(observer: (T?) -> Unit) where T : Any =
        observe(this@BindingFragment, Observer<T> { v -> observer(v) })

    protected fun <T> LiveData<T>.observeNonNull(observer: (T) -> Unit) {
        this.observe(this@BindingFragment, Observer {
            if (it != null) {
                observer(it)
            }
        })
    }
}
