/*
 * Функция ивлечения значения для ключа
 */
fun Extract(arr : List<String>){
    if(arr.size != 2){
        Error(Errors.Size)
    }
    var new = node(1, "0".repeat(64), (0..(1e15 - 1).toInt()).random(), arr[1], "", 0, 0, WorkWithFile("file_data.txt").getLength())
    new.countHash()
    startnodeindex = WorkWithFile("file_data.txt").readFirst()
    if(find(startnodeindex, new)){
        println(getValue(startnodeindex, new))
    }
    else {
        Error(Errors.NotExists)
    }
}