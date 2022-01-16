val pizzaSizes = listOf("Small","Medium","Large")
val pizzaToppings = listOf("Olives","Bacon","Pineapple","Mushrooms","Green pepper")

fun main() {
    launchApp()
}

fun launchApp() {
    println("Hello, welcome to OrderPizza")
    println("Choose an option(#) to continue")
    startOptions()
}

fun startOptions(initText : String = "") {
    if(initText.isNotEmpty()) println("Invalid choice, select again")
    println("#1. Order Pizza")
    println("#99. Exit App")
    try {
        when(readLine()?.toInt() ?: 99){
            1 -> orderSize()
            99 -> exitApp()
            else -> startOptions(initText = "Invalid choice, select again")
        }
    } catch (e : Exception){
        startOptions(initText = "Invalid choice, select again")
    }
}

fun orderSize(initText : String = "") {
    if(initText.isNotEmpty()) println("Invalid choice, select again")
    for ((index,size) in pizzaSizes.withIndex()){
        println("#${index + 1}. $size")
    }
    println("#99. Exit App")
    try {
        when(readLine()?.toInt() ?: 99){
            1 -> addToppings(0)
            2 -> addToppings(1)
            3 -> addToppings(2)
            99 -> exitApp()
            else -> orderSize(initText = "Invalid choice, select again")
        }
    } catch (e : Exception){
        orderSize(initText = "Invalid choice, select again")
    }
}

fun addToppings(size : Int, initText: String = ""){
    if(initText.isNotEmpty()) println("Invalid choice, select again")
    println("Select toppings by number separating each with a comma(,)")
    for ((index,topping) in pizzaToppings.withIndex()){
        println("#${index + 1}. $topping")
    }
    println("Press enter to have a our default")
    println("#99. Exit App")
    try {
        val toppings = readLine() ?: "";
        if(toppings.isEmpty()) return processOrder(default = true,size = size)
        val toppingsList = toppings.split(",").map { topping -> topping.toInt() - 1 }
        processOrder(size = size,toppings = toppingsList)
    }
    catch (e : Exception){
        println(e.message)
        addToppings(size = size,initText = "Invalid choice, select again")
    }
}

fun processOrder(default : Boolean = false, size: Int,toppings : List<Int> = listOf() ) {
    var sum = 0.0
    sum += getSizePrice(size = size)
    if(default) return printOrder(size = size,sum = sum + 3,toppings = toppings)
    for (topping in toppings){
        sum += getToppingPrice(option = topping)
    }
    return printOrder(size = size,sum = sum,toppings = toppings)
}

fun getSizePrice(size : Int) : Double {
    return when (size) {
        0 -> 5.00
        1 -> 10.00
        2 -> 15.00
        else -> 0.00
    }
}

fun getToppingPrice(option : Int) : Double {
    return when (option) {
        0 -> 1.50
        1 -> 2.00
        2 -> 0.50
        3 -> 1.00
        4 -> 1.00
        else -> 0.00
    }
}

fun printOrder(size : Int,sum : Double,toppings: List<Int>) {
    println("Order Complete")
    println("You ordered a ${pizzaSizes[size].lowercase()} sized pizza")
    println("These are your toppings...")
    if(toppings.isEmpty()){
        for (toppingIndex in listOf(0,1)){
            println(pizzaToppings[toppingIndex])
        }
    } else {
        for (toppingIndex in toppings){
            println(pizzaToppings[toppingIndex])
        }
    }

    println("Your total is GHS ${String.format("%.2f", sum)}")
    launchApp()
}

fun exitApp () {
    println("Good Bye!!!")
}