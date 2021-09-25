
/*
 * Функция, изменяющая значение для существующего ключа
 */
fun Change(args : List<String>){
    if(args.size != 3){
        Error(Errors.Size)
    }
    else if(!map.containsKey(args[1])){
        Error(Errors.NotExists)
    }
    else{
        map[args[1]] = args[2]
    }
}