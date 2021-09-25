
/*
 * Функция добавления пары ключ - значение
 */
fun Add(arr : List<String>){
    if(arr.size != 3){
        Error(Errors.Size)
    }
    else if(map.contains(arr[1])){
        Error(Errors.Exists)
    }
    else{
        map[arr[1]] = arr[2]
    }
}