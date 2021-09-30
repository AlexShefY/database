import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.RandomAccessFile

/*
 * Класс, предназначенный для работы с файлом
 */
class WorkWithFile(var path : String) {
    lateinit var file : RandomAccessFile
    /*
     * Чтение всего файла
     */
    fun read() {
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
    /*
     * Запись всех данных в файл
     */
    fun write(){
        file = RandomAccessFile(path, "rw")
        file.setLength(0)
        startnodeindex = 0
        build()
        file.close()
    }
    /*
     * Узнаем размер файла
     */
    fun getLength() : Int {
        file = RandomAccessFile(path, "rw")
        var len = file.length()
        file.close()
        return len.toInt()
    }
    /*
     * Добавляем данные в конец файла
     */
    fun addWrite(toWrite : String){
        file = RandomAccessFile(path, "rw")
        file.seek(file.length())
        file.writeBytes(toWrite)
        file.close()
    }
    /*
     * Считываем номер вершины, которая является корнем дерева.
     * Этот номер мы записываем в начало файла
     */
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
    /*
     * Читаем символы до встречающегося разделительного символа
     */
    fun readUntilChar(start : Int, char : Char) : Pair <String, Int> {
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
     * Читаем данные вершины декартова дерева, за исключением ее ключа и значения(чтобы
     * меньше тратилось времени). Здесь мы считываем всегда фиксированное количество символов,
     * не обходимых для восстановаления декартова дерева и сравнения двух ключей.
     */
    fun readFromPart(start : Int) : node{
        val (res1, start1) = readUntilChar(start, '*')
        var newNode = node(res1.toInt())
        val (res2, start2) = readUntilChar(start1, '*')
        newNode.left = res2.toInt()
        val (res3, start3) = readUntilChar(start2, '*')
        newNode.right = res3.toInt()
        val (res4, start4) = readUntilChar(start3, '*')
        newNode.Hash = res4
        val (res5, start5) = readUntilChar(start4, '*')
        newNode.priority = res5.toInt()
        val (res6, start6) = readUntilChar(start5, '*')
        newNode.selfit = res6.toInt()
        return newNode
    }
    /*
     * Здесь мы считываем все данне для вершины
     */
    fun readFromFull(start : Int) : node{
        val (res1, start1) = readUntilChar(start, '*')
        var newNode = node(res1.toInt())
        val (res2, start2) = readUntilChar(start1, '*')
        newNode.left = res2.toInt()
        val (res3, start3) = readUntilChar(start2, '*')
        newNode.right = res3.toInt()
        val (res4, start4) = readUntilChar(start3, '*')
        newNode.Hash = res4
        val (res5, start5) = readUntilChar(start4, '*')
        newNode.priority = res5.toInt()
        val (res6, start6) = readUntilChar(start5, '*')
        newNode.selfit = res6.toInt()
        val (res7, start7) = readUntilChar(start6, '*')
        newNode.Key = res7
        val (res8, start8) = readUntilChar(start7, '|')
        newNode.value = res8
        return newNode
    }
    /*
     * Записываем определенные данные для вершин или номер корня в файл
     */
    fun writeFrom(from : Int, toWrite : String){
        file = RandomAccessFile(path, "rw")
        file.seek(from.toLong())
        file.write(toWrite.toByteArray())
        file.close()
    }
}