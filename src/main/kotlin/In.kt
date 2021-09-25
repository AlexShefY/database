
/*
 * Фукнция, проверяющая существование ключа в словаре
 */
fun In(args : List<String>){
    if(args.size != 2){
        Error(Errors.Size)
    }
    else if(map.containsKey(args[1])){
        println("This key exists")
    }
    else{
        println("This key doesn`t exist")
    }
}