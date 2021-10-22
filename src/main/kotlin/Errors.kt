
/*
 * Class for handling errors
 */
enum class Errors{
    Size, Exists, Other, NotExists, FileNotExists
}

/*
 * Depending on the error, we display the message
 */
fun Error(err : Errors){
    when(err.name){
        "Size" -> println("Invalid amount of input")
        "Exists" -> println("This item already exists")
        "NotExists" -> println("This item doesn`t exist")
        "FileNotExists" -> println("This file doesn`t exist")
        else -> println("Invalid input")
    }
}