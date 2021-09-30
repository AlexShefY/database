
/*
 * Функция, изменяющая значение для существующего ключа
 */
fun Change(args : List<String>){
    if(args.size != 3){
        Error(Errors.Size)
        return
    }
    var new = node(1, "0".repeat(64), (0..(1e15 - 1).toInt()).random(), args[1], "", 0, 0, WorkWithFile("file_data.txt").getLength())
    new.countHash()
    startnodeindex = WorkWithFile("file_data.txt").readFirst()
    if(find(startnodeindex, new)){
        Remove(listOf("remove", args[1]))
        Add(listOf("add", args[1], args[2]))
    }
    else {
        Error(Errors.NotExists)
    }
}