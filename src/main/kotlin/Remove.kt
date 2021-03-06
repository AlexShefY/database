
/*
 * Удаляем конкретный ключ
 */
fun Remove(arr : List<String>){
    if(arr.size != 3){
        Error(Errors.Size)
        return
    }
    var new = createNode(arr[2], "", arr[1])
    startnodeindex = readFirst(arr[1])
    if(find(arr[1], startnodeindex, new)){
        removeNode(arr[1], new)
    }
    else {
        Error(Errors.NotExists)
    }
}

/*
 * Clearing the map
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
 * A function that implements deletions depending on whether
 * whether the length meets the specified condition
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
 * Function that implements deletion depending on
 * from compliance with the condition
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
        mapActionsFilter[arr[3]]?.let { removeIfLength(it, v) }
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