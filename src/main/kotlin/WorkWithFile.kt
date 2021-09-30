import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.RandomAccessFile

var globalIt = 7

class WorkWithFile(var path : String) {
    lateinit var file : RandomAccessFile
    public fun read() {
        file = RandomAccessFile(path, "rw")
        file.seek(0)
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
            if (node.size == 8 && node[0].toInt() == 1) {
                map.add(node(node[0].toInt(), node[3], node[4].toInt(), node[6], node[7], node[1].toInt(), node[2].toInt(), node[5].toInt()))
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
        startnodeindex = 0
        build()
        file.close()
    }
    public fun getLength() : Int {
        file = RandomAccessFile(path, "rw")
        var len = file.length()
        file.close()
        return len.toInt()
    }
    public fun addWrite(toWrite : String){
        file = RandomAccessFile(path, "rw")
        file.seek(file.length())
        file.writeBytes(toWrite)
        file.close()
    }
    fun readFirst() : Int{
        file = RandomAccessFile(path, "rw")
        file.seek(0)
        var res = ""
        var b = file.read()
        while(b.toChar() != '|'){
            res += b.toChar()
            b = file.read()
        }
        file.close()
        return res.toInt()
    }
    fun readFromPart(start : Int) : node{
        file = RandomAccessFile(path, "rw")
        file.seek(start.toLong())
        var res = ""
        var b = file.read()
        while(b.toChar() != '*'){
            res += b.toChar()
            b = file.read()
        }
        var newNode = node(res.toInt())
        res = ""
        b = file.read()
        while(b.toChar() != '*'){
            res += b.toChar()
            b = file.read()
        }
        newNode.left= res.toInt()
        res = ""
        b = file.read()
        while(b.toChar() != '*'){
            res += b.toChar()
            b = file.read()
        }
        newNode.right = res.toInt()
        res = ""
        b = file.read()
        while(b.toChar() != '*'){
            res += b.toChar()
            b = file.read()
        }
        newNode.Hash = res
        res = ""
        b = file.read()
        while(b.toChar() != '*'){
            res += b.toChar()
            b = file.read()
        }
        newNode.priority = res.toInt()
        res = ""
        b = file.read()
        while(b.toChar() != '*'){
            res += b.toChar()
            b = file.read()
        }
        newNode.selfit = res.toInt()
        file.close()
        return newNode
    }
    fun readFromFull(start : Int) : node{
        file = RandomAccessFile(path, "rw")
        file.seek(start.toLong())
        var res = ""
        var b = file.read()
        while(b.toChar() != '*'){
            res += b.toChar()
            b = file.read()
        }
        var newNode = node(res.toInt())
        res = ""
        b = file.read()
        while(b.toChar() != '*'){
            res += b.toChar()
            b = file.read()
        }
        newNode.left= res.toInt()
        res = ""
        b = file.read()
        while(b.toChar() != '*'){
            res += b.toChar()
            b = file.read()
        }
        newNode.right = res.toInt()
        res = ""
        b = file.read()
        while(b.toChar() != '*'){
            res += b.toChar()
            b = file.read()
        }
        newNode.Hash = res
        res = ""
        b = file.read()
        while(b.toChar() != '*'){
            res += b.toChar()
            b = file.read()
        }
        newNode.priority = res.toInt()
        res = ""
        b = file.read()
        while(b.toChar() != '*'){
            res += b.toChar()
            b = file.read()
        }
        newNode.selfit = res.toInt()
        res = ""
        b = file.read()
        while(b.toChar() != '*'){
            res += b.toChar()
            b = file.read()
        }
        newNode.Key = res
        res = ""
        b = file.read()
        while(b.toChar() != '|'){
            res += b.toChar()
            b = file.read()
        }
        newNode.value = res
        file.close()
        return newNode
    }
    fun writeFrom(from : Int, toWrite : String){
        file = RandomAccessFile(path, "rw")
        file.seek(from.toLong())
        file.write(toWrite.toByteArray())
        file.close()
    }
}