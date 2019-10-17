# MvvmGenerator
用于生成 MVVM 架构相关模板代码的生成器

### Usage

#### 一般用法 (适用于配合 BindingFragment 使用的 Android Architecture Component 架构)
1. 在 `Constants` 定义相关 path 
2. 运行 `MvvmGenerator.kt` 根据提示输入前缀

> 请输入画面前缀,如: A01_Test
 生成：  
 -> Folder : a01_test  
 -> Fragment : A01TestFragment.kt  
 -> ViewModel : A01TestViewModel.kt  
 -> layout : fragment_a01_test.xml  

#### Advanced

若模板无法满足需求，在`templates` 文件夹下自行调整模板。  
语法采用`FreeMarker Template Language (FTL)`

#### 关于架构请参考 [XAndroidArchitecture](https://github.com/xiejinpeng007/XAndroidArchitecture)
