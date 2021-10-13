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
        }
        else{
            mapActions[arr[0]]?.let { it(arr) }
        }
        print(">")
        s = readLine()!!
    }
}
