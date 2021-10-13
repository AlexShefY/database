/*
 * Функция ивлечения значения для ключа
 */
fun Extract(arr : List<String>){
    if(arr.size != 3){
        Error(Errors.Size)
        return
    }
    var new = createNode(arr[2], "", arr[1])
    startnodeindex = readFirst(arr[1])
    if(find(arr[1], startnodeindex, new)){
        println(getValue(arr[1], startnodeindex, new))
    }
    else {
        Error(Errors.NotExists)
    }
}