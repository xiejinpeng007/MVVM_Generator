package com.mvvm_generator

import freemarker.template.Configuration
import freemarker.template.Version
import java.io.File
import java.io.StringWriter
import java.util.*

//define the Environmental variable in Constants.kt
val projectPath = File("").absolutePath!!
val templatesFolderPath = "$projectPath$moduleTemplatesPath"
val fragmentFileFolderPath = "$projectPath$fragmentFolderPath"
val vmFileFolderPath = "$projectPath$viewModelFolderPath"
val layoutFolderPath = "$projectPath$layoutPath"


object TemplateGenerator {
    @JvmStatic
    fun main(args: Array<String>) {
        println("""
            |
            | 请输入画面前缀,如: A01_Test
            | 生成：
            | -> Folder : a01_test
            | -> Fragment : A01TestFragment.kt
            | -> ViewModel : A01TestViewModel.kt
            | -> layout : fragment_a01_test.xml
            |
        """.trimMargin())
        val scanner = Scanner(System.`in`)
        var prefixName = ""
        if (scanner.hasNext()) prefixName = scanner.nextLine()

        //
        println(File("").absolutePath)
        val configuration = Configuration(Version(2,3,28))
        configuration.setDirectoryForTemplateLoading(File(templatesFolderPath))

        genFragment(configuration, prefixName)
        genViewModel(configuration, prefixName)
        genLayout(configuration, prefixName)

    }

    private fun genLayout(configuration: Configuration, prefixName: String) {
        //generate files
        configuration.clearTemplateCache()
        val stringWriter = StringWriter()
        val template = configuration.getTemplate("layout.ftl")
        val params = hashMapOf(
            "fragmentName" to getFragmentNameByClass(prefixName),
            "basePackage" to basePackage,
            "fragmentPackage" to fragmentPackage,
            "classFolderName" to prefixName.toLowerCase(),
            "layoutName" to getLayoutNameByClass(prefixName)
        )

        template.process(params, stringWriter)
        stringWriter.flush()
        File(layoutFolderPath).mkdirs()
        File("$layoutFolderPath/fragment_${prefixName.toLowerCase()}.xml")
            .writeText(stringWriter.toString())
    }

    private fun genViewModel(configuration: Configuration, prefixName: String) {
        configuration.clearTemplateCache()
        val stringWriter = StringWriter()
        val template = configuration.getTemplate("viewModel.ftl")
        val params = hashMapOf(
            "fragmentName" to getFragmentNameByClass(prefixName),
            "viewModelName" to getViewModelNameByClass(prefixName),
            "basePackage" to basePackage,
            "fragmentPackage" to fragmentPackage,
            "fragmentBasePackage" to fragmentBasePackage,
            "vmBasePackage" to vmBasePackage,
            "classFolderName" to prefixName.toLowerCase()
        )

        template.process(params, stringWriter)
        stringWriter.flush()
        File("$vmFileFolderPath/${prefixName.toLowerCase()}").mkdirs()
        File("$vmFileFolderPath/${prefixName.toLowerCase()}/${getViewModelNameByClass(prefixName)}.kt")
            .writeText(stringWriter.toString())
    }

    private fun genFragment(configuration: Configuration, prefixName: String) {
        configuration.clearTemplateCache()
        val stringWriter = StringWriter()
        val template = configuration.getTemplate("fragment.ftl")
        val params = hashMapOf(
            "fragmentName" to getFragmentNameByClass(prefixName),
            "viewModelName" to getViewModelNameByClass(prefixName),
            "basePackage" to basePackage,
            "fragmentPackage" to fragmentPackage,
            "fragmentBasePackage" to fragmentBasePackage,
            "vmBasePackage" to vmBasePackage,
            "classFolderName" to prefixName.toLowerCase(),
            "layoutName" to getLayoutNameByClass(prefixName),
            "bindingName" to getBindingNameByClass(prefixName)
        )

        template.process(params, stringWriter)
        stringWriter.flush()
        File("$fragmentFileFolderPath/${prefixName.toLowerCase()}").mkdirs()
        File(
            "$fragmentFileFolderPath/${prefixName.toLowerCase()}/" +
                    "${getFragmentNameByClass(prefixName)}.kt"
        )
            .writeText(stringWriter.toString())
    }

    private fun getBindingNameByClass(prefixName: String) =
        "Fragment" + prefixName.replace("_", "") + "Binding"

    private fun getFragmentNameByClass(prefixName: String) =
        prefixName.replace("_", "") + "Fragment"

    private fun getLayoutNameByClass(prefixName: String) = "fragment_${prefixName.toLowerCase()}"

    private fun getViewModelNameByClass(prefixName: String) =
        prefixName.replace("_", "") + "ViewModel"


}
