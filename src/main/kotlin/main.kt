import java.io.File
import java.nio.charset.Charset

/*
 * В main мы считываем ввод и обрабатываем его
 */
var startnodeindex = 0
var map : MutableList<node> = mutableListOf()
var mapActions = mapOf("add" to :: Add, "extract" to :: Extract, "in" to :: In, "out" to :: Out, "remove" to :: Remove,
"removeAll" to  :: RemoveAll, "removeIf" to :: RemoveIf, "filterOut" to :: FilterOut)
fun main(args: Array<String>) {
    print(">")
    var s = String(readLine()!!.toByteArray(), Charset.forName("Windows-1251"))
    while(s != "finish"){
        var arr = s.split(" ")
        if(!mapActions.containsKey(arr[0])){
            Error(Errors.Other)
            return
        }
        else if(arr.size < 2){
            Error(Errors.Size)
            return
        }
        else if(!File(arr[1]).exists()){
            Error(Errors.FileNotExists)
            return
        }
        mapActions[arr[0]]?.let { it(arr) }
        print(">")
        s = readLine()!!
    }
}
