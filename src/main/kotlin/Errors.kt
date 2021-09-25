
enum class Errors{
    Size, Exists, Other, NotExists
}

fun Error(err : Errors){
    when(err.name){
        "Size" -> println("Invalid amount of input")
        "Exists" -> println("This item already exists")
        "NotExists" -> println("This item doesn`t exists")
        else -> println("Invalid input")
    }
}