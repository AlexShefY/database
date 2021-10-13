
/*
 * Функция, изменяющая значение для существующего ключа
 */
fun Change(args : List<String>){
    if(args.size != 4){
        Error(Errors.Size)
        return
    }
    var new = createNode(args[2], "", args[1])
    startnodeindex = readFirst(args[1])
    if(find(args[1], startnodeindex, new)){
        Remove(listOf("remove", args[1], args[2]))
        Add(listOf("add", args[1], args[2], args[3]))
    }
    else {
        Error(Errors.NotExists)
    }
}