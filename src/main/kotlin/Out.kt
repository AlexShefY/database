
/*
 * Функция, выводящая содержание базы данных
 */
fun Out(arr : List<String>){
    WorkWithFile("file_data.txt").read()
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
fun FilterOut(arr : List<String>){
    WorkWithFile("file_data.txt").read()
    if(arr[1] == "length"){
        if(arr.size != 4){
            Error(Errors.Size)
            return
        }
        var v : Int? = arr[3].toIntOrNull()
        if(v == null){
            Error(Errors.Other)
            return
        }
        when(arr[2]){
            "<" -> {
                IfLength({a, b -> a < b}, v)
            }
            "<=" -> {
                IfLength({a, b -> a <= b}, v)
            }
            ">=" -> {
                IfLength({a, b -> a >= b}, v)
            }
            ">" -> {
                IfLength({a, b -> a > b}, v)
            }
            "==" -> {
                IfLength({a, b -> a == b}, v)
            }
            else -> Error(Errors.Other)
        }
        WorkWithFile("file_data.txt").write()
    }
    else if(arr[1] == "regex"){
        var pattern = arr[2].toRegex()
        map.forEach{
            node ->
            if(pattern.matches(node.Key)){
                println("${node.Key} ${node.value}")
            }
        }
        WorkWithFile("file_data.txt").write()
    }
    else{
        Error(Errors.Other)
    }
}