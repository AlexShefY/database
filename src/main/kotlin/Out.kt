
/*
 * Функция, выводящая содержание базы данных
 */
fun Out(){
    if(map.size == 0){
        print("Data Base is empty")
    }
    map.forEach() {
        key, value ->
        println("$key $value")
    }
}