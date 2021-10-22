import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.RandomAccessFile
import java.nio.charset.Charset

/*
 * A class for working with a file
 */
lateinit var file : RandomAccessFile
/*
 * Reading the entire file
*/

fun read(path : String) {
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
        }
        it += line.length + 1
    }
    file.close()
}
/*
 * Writing all data to a file
*/
fun write(path : String){
    file = RandomAccessFile(path, "rw")
    file.setLength(0)
    startnodeindex = 0
    build(path)
    file.close()
}
/*
 * Find out the file size
*/
fun getLength(path : String) : Int {
    file = RandomAccessFile(path, "rw")
    var len = file.length()
    file.close()
    return len.toInt()
}
/*
 * Adding data to the end of the file
*/
fun addWrite(path : String, toWrite : String){
    file = RandomAccessFile(path, "rw")
    file.seek(file.length())
    file.writeBytes(toWrite)
    file.close()
}
/*
 * We read the number of the vertex, which is the root of the tree.
 * We write this number at the beginning of the file
*/
fun readFirst(path : String) : Int{
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
/*
 * We read characters up to the encountered separator character
*/
fun readUntilChar(path : String, start : Int, char : Char) : Pair <String, Int> {
    file = RandomAccessFile(path, "rw")
    file.seek(start.toLong())
    var res = ""
    var b = file.read()
    var start1 = start
    while(b.toChar() != char){
        res += b.toChar()
        b = file.read()
        start1++
    }
    start1++
    file.close()
    return Pair(res, start1)
}
/*
 * We read the data of vertices of the Cartesian tree, with the exception of its key and value (so that
 * less time wasted). Here we always read a fixed number of characters,
 * required for restoring a Cartesian tree and comparing two keys.
*/
fun readFromPart(path : String, start : Int) : node{
    val (res1, start1) = readUntilChar(path, start, '*')
    var newNode = node(res1.toInt())
    val (res2, start2) = readUntilChar(path, start1, '*')
    newNode.left = res2.toInt()
    val (res3, start3) = readUntilChar(path,start2, '*')
    newNode.right = res3.toInt()
    val (res5, start5) = readUntilChar(path,start3 + 65, '*')
    newNode.priority = res5.toInt()
    val (res6, start6) = readUntilChar(path, start5, '*')
    newNode.selfit = res6.toInt()
    return newNode
}
/*
 * Here we read all the data for the top
*/
fun readFromFull(path : String, start : Int) : node{
    val (res1, start1) = readUntilChar(path, start, '*')
    var newNode = node(res1.toInt())
    val (res2, start2) = readUntilChar(path, start1, '*')
    newNode.left = res2.toInt()
    val (res3, start3) = readUntilChar(path, start2, '*')
    newNode.right = res3.toInt()
    val (res4, start4) = readUntilChar(path, start3, '*')
    newNode.Hash = res4
    val (res5, start5) = readUntilChar(path, start4, '*')
    newNode.priority = res5.toInt()
    val (res6, start6) = readUntilChar(path, start5, '*')
    newNode.selfit = res6.toInt()
    val (res7, start7) = readUntilChar(path, start6, '*')
    newNode.Key = res7
    val (res8, start8) = readUntilChar(path, start7, '|')
    newNode.value = res8
    return newNode
}
/*
 * Read a specific number of characters from a file, starting at "from"
*/
fun readCnt(path : String, from : Int, cnt : Int) : String{
    file = RandomAccessFile(path, "rw")
    file.seek(from.toLong())
    var res = ""
    var i = 0
    while(i < cnt){
        var b = file.read()
        res += b.toChar()
        i++
    }
    file.close()
    return res
}
/*
 * We write certain data for the vertices and the number of the root of a Cartesian tree to a file
*/
fun writeFrom(path : String, from : Int, toWrite : String){
    file = RandomAccessFile(path, "rw")
    file.seek(from.toLong())
    file.write(toWrite.toByteArray())
    file.close()
}