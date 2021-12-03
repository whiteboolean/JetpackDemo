package com.example.jetpackdemo.day1203_2021_coroutine

sealed class MyColor {

    class Yellow :MyColor()

    class Red: MyColor()

    class Black:MyColor()
}


sealed class ColorA{
    class White:MyColor()
}


sealed class ColorB :MyColor(){

}

//fun main(args: Array<String>) {
////    val color = MyColor() //密封类型无法实例化
//}

//open class MyColor2{
//    class Yellow : MyColor2()
//
//    class Black :MyColor2()
//
//    class Red : MyColor2()
//
//}
//
//
//fun evals(color2: MyColor2) = when(color2){
//    is MyColor2.Yellow -> println("yellow")
//    is  MyColor2.Black -> println("black")
//    is  MyColor2.Red -> println("red")
//    else ->println("other") //必须包含else
//}
//
//fun main() {
//    evals(MyColor2.Yellow())
//}


sealed class MyFamily{
    class Mom : MyFamily()

    class Dad: MyFamily()

    class Nephew:MyFamily()
}

fun elvis(family:MyFamily)  = when (family){
    is MyFamily.Mom -> println("是妈妈")
    is MyFamily.Dad -> println("是爸爸")
    is MyFamily.Nephew -> println("是侄子")
}

fun main() {
    elvis(MyFamily.Mom())
}