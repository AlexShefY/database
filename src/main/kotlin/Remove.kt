
/*
 * Удаляем конкретный ключ
 */
fun Remove(arr : List<String>){
    if(arr.size != 2){
        Error(Errors.Size)
        return
    }
    WorkWithFile("file_data.txt").read()
    for(p in map.indices){
        if(map[p].Key == arr[1]){
            map.removeAt(p)
            WorkWithFile("file_data.txt").write()
            return
        }
    }
    Error(Errors.NotExists)
}

/*
 * Очищаем словарь
 */
fun RemoveAll(){
    WorkWithFile("file_data.txt").read()
    map.clear()
    WorkWithFile("file_data.txt").write()
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
        WorkWithFile("file_data.txt").write()
    }
    else if(arr[1] == "regex"){
        var pattern = arr[2].toRegex()
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
        WorkWithFile("file_data.txt").write()
    }
    else{
        Error(Errors.Other)
    }
}