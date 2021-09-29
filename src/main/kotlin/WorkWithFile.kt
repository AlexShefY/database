import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.RandomAccessFile

class WorkWithFile(var path : String) {
    lateinit var file : RandomAccessFile
    public fun read() {
        //  throw IOException()
        file = RandomAccessFile(path, "rw")
        var str = ""
        var b: Int = file.read()
        while (b != -1) {
            str += b.toChar()
            b = file.read()
        }
        str.split('|').forEach { line ->
            var pair = line.split('~')
            if (pair.size == 2) {
                map[pair[0]] = pair[1]
            }
        }
        file.close()
    }
    public fun write(){
        file = RandomAccessFile(path, "rw")
        map.forEach { key, value ->
            file.writeBytes("$key~$value|")
        }
        file.close()
    }
}