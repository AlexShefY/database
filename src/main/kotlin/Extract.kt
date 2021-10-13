/*
 * Функция ивлечения значения для ключа
 */
fun Extract(arr : List<String>){
    if(arr.size != 3){
        Error(Errors.Size)
        return
    }
    var new = node(1, "0".repeat(64), (0..(1e15 - 1).toInt()).random(), arr[2], "", 0, 0, getLength(arr[1]))
    new.countHash()
    startnodeindex = readFirst(arr[1])
    if(find(arr[1], startnodeindex, new)){
        println(getValue(arr[1], startnodeindex, new))
    }
    else {
        Error(Errors.NotExists)
    }
}