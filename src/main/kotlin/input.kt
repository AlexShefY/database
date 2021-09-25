import java.io.File

/*
 * Чтение из файла данных
 */
fun dataIn(){
    File("file_data.txt").useLines{
        lines -> lines.forEach{
            var pair = it.split("~")
            map[pair[0]] = pair[1]
        }
    }
}