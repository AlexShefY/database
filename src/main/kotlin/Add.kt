
/*
 * Функция добавления пары ключ - значение
 */
fun Add(arr : List<String>){
    if(arr.size != 3){
        Error(Errors.Size)
        return
    }
    var new = node(1, "0".repeat(64), (0..(1e15 - 1).toInt()).random(), arr[1], arr[2], 0, 0, WorkWithFile("file_data.txt").getLength())
    new.countHash()
    startnodeindex = WorkWithFile("file_data.txt").readFirst()
    if(find(startnodeindex, new)){
        Error(Errors.Exists)
    }
    else {
        addNode(new)
    }
}