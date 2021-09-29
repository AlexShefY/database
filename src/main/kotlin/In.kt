
/*
 * Фукнция, проверяющая существование ключа в словаре
 */
fun In(args : List<String>){
    if(args.size != 2){
        Error(Errors.Size)
        return
    }
    WorkWithFile("file_data.txt").read()
    for(p in map){
        if(p.Key == args[1]){
            println("This key exists")
            return
        }
    }
    println("This key doesn`t exist")
}