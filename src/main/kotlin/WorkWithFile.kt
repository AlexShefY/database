import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.RandomAccessFile

var globalIt = 0

class WorkWithFile(var path : String) {
    lateinit var file : RandomAccessFile
    public fun read() {
        file = RandomAccessFile(path, "rw")
        var str = ""
        var b: Int = file.read()
        while (b != -1) {
            str += b.toChar()
            b = file.read()
        }
        map.clear()
        var it = 0
        str.split('|').forEach { line ->
            var node = line.split('*')
            if (node.size == 7 && node[0].toBoolean()) {
                map.add(node(node[0].toBoolean(), node[3], node[4], node[5], node[1].toInt(), node[2].toInt(), node[6].toInt()))
            }
            else if(node.size == 1 && node[0].length==6){
                startnodeindex = node[0].substring(0, 5).toInt()
              //  startnodeindex = 0
            }
            it += line.length + 1
        }
        file.close()
    }
    public fun write(){
        file = RandomAccessFile(path, "rw")
        file.setLength(0)
        file.writeBytes(digits(startnodeindex))
        file.writeBytes("|")
        map.forEach {
            file.writeBytes(it.toString())
        }
        file.close()
    }
}