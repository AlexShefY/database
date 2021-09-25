var map : MutableMap<String, String> = mutableMapOf()
fun main(args: Array<String>) {
    if(args[0] == "start"){
        print(">")
        dataIn()
        var s = readLine()!!
        while(s != "finish"){
            var arr = s.split(" ")
            when(arr[0]){
                "add" -> {

                }
                "extract" -> {

                }
                "in" -> {

                }
                "first" -> {

                }
                "next" -> {

                }
                "last" -> {

                }
                "previous" -> {

                }
                "out" -> {

                }
                "remove" -> {

                }
                "removeAll" -> {

                }
                "removeIf" -> {

                }
                "change" -> {

                }
                "filterOut" -> {

                }
            }
            print(">")
            s = readLine()!!
        }
        dataOut()
    }
    else{
        println("Error")
        println("You should look at the terms of use")
    }
}
