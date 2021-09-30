
/*
 * Фукнция, проверяющая существование ключа в словаре
 */
fun In(args : List<String>){
    if(args.size != 2){
        Error(Errors.Size)
        return
    }
    var new = node(1, "0".repeat(64), (0..(1e15 - 1).toInt()).random(), args[1], "", 0, 0, WorkWithFile("file_data.txt").getLength())
    new.countHash()
    startnodeindex = WorkWithFile("file_data.txt").readFirst()
    if(find(startnodeindex, new)){
        println("This key exists")
    }
    else {
        println("This key doesn`t exist")
    }
}