
/*
 * Удаляем конкретный ключ
 */
fun Remove(arr : List<String>){
    if(arr.size != 2){
        Error(Errors.Size)
    }
    else if(!map.containsKey(arr[1])){
        Error(Errors.NotExists)
    }
    else{
        map.remove(arr[1])
    }
}

/*
 * Очищаем словарь
 */
fun RemoveAll(){
    map.clear()
}

/*
 * Функция, реализующая кдаления в зависимости от того,
 * соотвествует ли длина заданному условию
 */
fun removeIfLength(func : (a : Int, b : Int) -> Boolean, x : Int){
    var toDelete : MutableList<String> = mutableListOf()
    map.forEach{ (key, value) ->
        if(func(key.length, x)){
            toDelete.add(key)
        }
    }
    for(key in toDelete){
        map.remove(key)
    }
}
/*
 * Функция, реализующая удаление в зависимости
 * от соответствия условию
 */
fun RemoveIf(arr : List<String>){
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
                removeIfLength({a, b -> a < b}, v)
            }
            "<=" -> {
                removeIfLength({a, b -> a <= b}, v)
            }
            ">=" -> {
                removeIfLength({a, b -> a >= b}, v)
            }
            ">" -> {
                removeIfLength({a, b -> a < v}, v)
            }
            "==" -> {
                removeIfLength({a, b -> a == b}, v)
            }
            else -> Error(Errors.Other)
        }
    }
    else if(arr[1] == "regex"){
        var pattern = arr[2].toRegex()
        var toDelete = mutableListOf<String>()
        map.forEach{
                (key, value) ->
            if(pattern.matches(key)){
                toDelete.add(key)
            }
        }
        for(key in toDelete){
            map.remove(key)
        }
    }
    else{
        Error(Errors.Other)
    }
}