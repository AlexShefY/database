
/*
 * Удаляем конкретный ключ
 */
fun Remove(arr : List<String>){
    if(arr.size != 3){
        Error(Errors.Size)
        return
    }
    var new = node(1, "0".repeat(64), (0..(1e15 - 1).toInt()).random(), arr[2], "", 0, 0, getLength(arr[1]))
    new.countHash()
    startnodeindex = readFirst(arr[1])
    if(find(arr[1], startnodeindex, new)){
        removeNode(arr[1], new)
    }
    else {
        Error(Errors.NotExists)
    }
}

/*
 * Очищаем словарь
 */
fun RemoveAll(arr : List<String>){
    if(arr.size != 2){
        Error(Errors.Size)
        return
    }
    read(arr[1])
    map.clear()
    write(arr[1])
}

/*
 * Функция, реализующая кдаления в зависимости от того,
 * соотвествует ли длина заданному условию
 */
fun removeIfLength(func : (a : Int, b : Int) -> Boolean, x : Int){
    var toDelete : MutableList<node> = mutableListOf()
    map.forEach{ node ->
        if(func(node.Key.length, x)){
            toDelete.add(node)
        }
    }
    for(node in toDelete){
        map.remove(node)
    }
}
/*
 * Функция, реализующая удаление в зависимости
 * от соответствия условию
 */
fun RemoveIf(arr : List<String>){
    if(arr.size < 3){
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
        if(v == null){
            Error(Errors.Other)
            return
        }
        when(arr[3]){
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
        write(arr[1])
    }
    else if(arr[2] == "regex"){
        var pattern = arr[3].toRegex()
        var toDelete = mutableListOf<node>()
        map.forEach{
                node ->
            if(pattern.matches(node.Key)){
                toDelete.add(node)
            }
        }
        for(key in toDelete){
            map.remove(key)
        }
        write(arr[1])
    }
    else{
        Error(Errors.Other)
    }
}