
/*
 * Функция, выводящая содержание базы данных
 */
fun Out(arr : List<String>){
    if(arr.size != 2){
        Error(Errors.Size)
        return
    }
    read(arr[1])
    if(map.isEmpty()){
        println("Data Base is empty")
    }
    map.forEach{ node ->
        println("${node.Key} ${node.value}")
    }
}

/*
 * Функция, выводящая пары ключ-значения в зависимости
 * от соостветствия ключа условию на его длину
 */

fun IfLength(func : (a : Int, b : Int) -> Boolean, x : Int){
    map.forEach{ node ->
        if(func(node.Key.length, x)){
            println("${node.Key} ${node.value}")
        }
    }
}
/*
 * Функиця, выводящая пары ключ-значения в зависимости
 * от соответствия ключа заданному условию
 */
var mapActionsFilter = mapOf(">" to {a : Int, b : Int -> a > b}, "<" to {a : Int, b : Int -> a < b},
"<=" to {a : Int, b : Int -> a <= b}, ">=" to {a : Int, b : Int -> a >= b}, "==" to {a : Int, b : Int -> a == b})
fun FilterOut(arr : List<String>){
    if(arr.size < 2){
        Error(Errors.Size)
        return
    }
    read(arr[1])
    if(arr[2] == "length"){
        if(arr.size != 5){
            Error(Errors.Size)
            return
        }
        var v : Int? = arr[4].toIntOrNull()
        if(!mapActionsFilter.containsKey(arr[3]) || v == null){
            Error(Errors.Other)
            return
        }
        mapActionsFilter[arr[3]]?.let { IfLength(it, v) }
        write(arr[1])
    }
    else if(arr[2] == "regex"){
        var pattern = arr[3].toRegex()
        map.forEach{
            node ->
            if(pattern.matches(node.Key)){
                println("${node.Key} ${node.value}")
            }
        }
        write(arr[1])
    }
    else{
        Error(Errors.Other)
    }
}