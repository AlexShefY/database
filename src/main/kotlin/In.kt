
/*
 * Фукнция, проверяющая существование ключа в словаре
 */
fun In(args : List<String>){
    if(args.size != 3){
        Error(Errors.Size)
        return
    }
    var new = createNode(args[2], "", args[1])
    startnodeindex = readFirst(args[1])
    if(find(args[1], startnodeindex, new)){
        println("This key exists")
    }
    else {
        println("This key doesn`t exist")
    }
}