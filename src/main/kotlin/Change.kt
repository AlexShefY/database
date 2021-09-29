
/*
 * Функция, изменяющая значение для существующего ключа
 */
fun Change(args : List<String>){
    if(args.size != 3){
        Error(Errors.Size)
        return
    }
    WorkWithFile("file_data.txt").read()
    for(p in map){
        if(p.Key == args[1]){
            p.value = args[2]
            WorkWithFile("file_data.txt").write()
            return
        }
    }
    Error(Errors.NotExists)
}