
/*
 * Функция, выводящая содержание базы данных
 */
fun Out(){
    if(map.isEmpty()){
        print("Data Base is empty")
    }
    map.forEach{ (key, value) ->
        println("$key $value")
    }
}

/*
 *
 */

fun IfLength(func : (a : Int, b : Int) -> Boolean, x : Int){
    map.forEach{ (key, value) ->
        if(func(key.length, x)){
            println("$key $value")
        }
    }
}
fun FilterOut(arr : List<String>){
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
                IfLength({a, b -> a < v}, v)
            }
            "==" -> {
                IfLength({a, b -> a == b}, v)
            }
            else -> Error(Errors.Other)
        }
    }
    else if(arr[1] == "regex"){
        var pattern = arr[2].toRegex()
        map.forEach{
            (key, value) ->
            if(pattern.matches(key)){
                println("$key $value")
            }
        }
    }
    else{
        Error(Errors.Other)
    }
}