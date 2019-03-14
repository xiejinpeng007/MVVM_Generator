package ${basePackage}.${fragmentPackage}.${classFolderName}

import ${basePackage}.R
import ${basePackage}.databinding.${bindingName}
import ${basePackage}.${fragmentBasePackage}.BindingFragment

class ${fragmentName} : BindingFragment<${bindingName}, ${viewModelName}>(
        ${viewModelName}::class.java, layoutRes = R.layout.${layoutName}
) {


    override fun initView() {

        renderView { binding ->
        }
    }


    override fun initData() {

    }
}