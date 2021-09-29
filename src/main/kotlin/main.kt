
var startnodeindex = 0
var map : MutableList<node> = mutableListOf()
fun main(args: Array<String>) {
    if(args[0] == "start"){
        print(">")
        var s = readLine()!!
        while(s != "finish"){
            var arr = s.split(" ")
            when(arr[0]){
                "add" -> {
                    Add(arr)
                }
                "extract" -> {
                    Extract(arr)
                }
                "in" -> {
                    In(arr)
                }
                "out" -> {
                    Out()
                }
                "remove" -> {
                    Remove(arr)
                }
                "removeAll" -> {
                    RemoveAll()
                }
                "removeIf" -> {
                    RemoveIf(arr)
                }
                "change" -> {
                    Change(arr)
                }
                "filterOut" -> {
                    FilterOut(arr)
                }
                else -> {
                    Error(Errors.Other)
                }
            }
            print(">")
            s = readLine()!!
        }
    }
    else{
        println("Error")
        println("You should look at the terms of use")
    }
}
