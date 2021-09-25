import java.io.File

fun dataOut(){
    File("file_data.txt").printWriter().use { out ->
        map.forEach { key, value ->
            out.println("$key~$value")
        }
    }
}