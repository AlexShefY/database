
/*
 * Функция добавления пары ключ - значение
 */
fun Add(arr : List<String>){
    if(arr.size != 3){
        Error(Errors.Size)
        return
    }
    WorkWithFile("file_data.txt").read()
    for(p in map){
        if(p.Key == arr[1]){
            Error(Errors.Exists)
            return
        }
    }
    var new = node(true, "0".repeat(64), arr[1], arr[2], 0, 0, startnodeindex)
    new.countHash()
    map.add(new)
    WorkWithFile("file_data.txt").write()
}