
/*
 * Функция добавления пары ключ - значение
 */
fun Add(arr : List<String>){
    if(arr.size != 4){
        Error(Errors.Size)
        return
    }
    var new = node(1, "0".repeat(64), (0..(1e15 - 1).toInt()).random(), arr[2], arr[3], 0, 0, getLength(arr[1]))
    new.countHash()
    startnodeindex = readFirst(arr[1])
    if(find(arr[1], startnodeindex, new)){
        Error(Errors.Exists)
    }
    else {
        addNode(arr[1], new)
    }
}