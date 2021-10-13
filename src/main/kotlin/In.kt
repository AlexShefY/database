
/*
 * Фукнция, проверяющая существование ключа в словаре
 */
fun In(args : List<String>){
    if(args.size != 3){
        Error(Errors.Size)
        return
    }
    var new = node(1, "0".repeat(64), (0..(1e15 - 1).toInt()).random(), args[2], "", 0, 0, getLength(args[1]))
    new.countHash()
    startnodeindex = readFirst(args[1])
    if(find(args[1], startnodeindex, new)){
        println("This key exists")
    }
    else {
        println("This key doesn`t exist")
    }
}