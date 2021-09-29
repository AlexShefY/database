/*
 * Функция ивлечения значения для ключа
 */
fun Extract(arr : List<String>){
    if(arr.size != 2){
        Error(Errors.Size)
    }
    WorkWithFile("file_data.txt").read()
    for(p in map){
        if(p.Key == arr[1]){
            println(p.value)
            return
        }
    }
    Error(Errors.NotExists)
}