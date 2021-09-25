/*
 * Функция именения значения для ключа
 */
fun Extract(arr : List<String>){
    if(arr.size != 2){
        Error(Errors.Size)
    }
    else if(!map.contains(arr[1])){
        Error(Errors.NotExists)
    }
    else{
        println(map[arr[1]])
    }
}