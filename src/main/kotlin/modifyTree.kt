import kotlin.math.*
var cnt = 0
/*
 * Эта функция добавляет новую вершину декартова дерева в файл и вызывает goAdd,
 * которая добавит вершину в само дерево
 */
fun addNode(path : String, new : node){
    startnodeindex = readFirst(path)
    addWrite(path, new.toString())
    val (first, second) = split(path, startnodeindex, new.Hash, false)
    val first2 = merge(path, first, new.selfit)
    startnodeindex = merge(path, first2, second)
    writeFrom(path, 0, digits(startnodeindex, 6))
}
/*
 * Функция удаления вершины из декартова дерева, которая делит дерево
 * на поддеревья вершин с меньшим хэшом, с равным(в этом поддереве будет только
 *  вершина, которую мы удаляем, и с большим хэшом. А потом сливает
 * поддеревья с меньшим и большим хэшом в одно декртово дерево
 */
fun removeNode(path : String, cur : node){
    startnodeindex = readFirst(path)
    val (first, second) = split(path, startnodeindex, cur.Hash, false)
    val (first1, second1) = split(path, first, cur.Hash, true)
    startnodeindex = merge(path, first1, second)
    writeFrom(path, 0, digits(startnodeindex, 6))
}
/*
 * Функция, меняющая значения двух элементов класса местами
 */
fun swap(a : node, b : node){
    var c = node()
    c.set(a)
    a.set(b)
    b.set(c)
}
/*
 * Сравниваем хэши строк
 */
fun compareHash(path : String, itfirst : Int, HashSecond : String) : Int{
    var parts = 0
    while(parts < 16){
        var str1 = readCnt(path, itfirst + parts * 4 + 16, 4)
        for(i in 0..3){
            if(str1[i] < HashSecond[parts * 4 + i]){
                return -1
            }
            if(str1[i] > HashSecond[parts * 4 + i]){
                return 1
            }
        }
        parts++
    }
    return 0
}
/*
 * Функция поиска вершины в дереве с заданным ключом
 */
fun find(path : String, nodeindex : Int, toFind : node) : Boolean{
    cnt++
    if(nodeindex == 0){
        return false
    }
    var curNode = readFromPart(path, nodeindex)
    var rescompare = compareHash(path, curNode.selfit, toFind.Hash)
    if(rescompare == 0){
        return true
    }
    if(rescompare > 0){
        return find(path, curNode.left, toFind)
    }
    else{
        return find(path, curNode.right, toFind)
    }
}
/*
 * Функция, строящая декартово дерево по имеющимся
 * у нас парам ключ-значение
 */
fun build(path : String){
    addWrite(path, digits(startnodeindex, 6) + "|")
    for(p in map){
        Add(listOf("add", path, p.Key, p.value))
    }
}
/*
 * Функция, которая находит вершину в декартовом дереве с заданным ключом
 * и возвращает значение для этого ключа
 */
fun getValue(path : String, nodeindex : Int, toFind : node) : String{
    var curNode = readFromPart(path, nodeindex)
    var res = compareHash(path, curNode.selfit, toFind.Hash)
    if(res == 0){
        curNode = readFromFull(path, nodeindex)
        return curNode.value
    }
    if(res > 0){
        return getValue(path, curNode.left, toFind)
    }
    else{
        return getValue(path, curNode.right, toFind)
    }
}
/*
 * Функция, которая сливает два поддерева
 */
fun merge(path : String, nodeindex1 : Int, nodeindex2 : Int) : Int{
    cnt++
    if(nodeindex1 == 0){
        return nodeindex2
    }
    if(nodeindex2 == 0){
        return nodeindex1
    }
    var curNode1 = readFromPart(path, nodeindex1)
    var curNode2 = readFromPart(path, nodeindex2)
    if(curNode1.priority > curNode2.priority){
        curNode1.right = merge(path, curNode1.right, nodeindex2)
        writeFrom(path, curNode1.selfit + 2 + 7, digits(curNode1.right, 6))
        return curNode1.selfit
    }
    else{
        curNode2.left = merge(path, nodeindex1, curNode2.left)
        writeFrom(path, curNode2.selfit + 2, digits(curNode2.left, 6))
        return curNode2.selfit
    }
}
/*
 * Функция, разделяющая декартово дерево на два поддерева:
 * с большим хэшом и меньшим хэшом( при этом в одном из поддеревьев может
 * оказаться вершина с заданным хэшом, это зависит от флага)
 */
fun split(path : String, nodeindex : Int, forSplitHash :String, Flag : Boolean) : Pair<Int, Int>{
    cnt++
    if(nodeindex == 0){
        return Pair(0, 0)
    }
    var curNode = readFromPart(path, nodeindex)
    var res = compareHash(path, curNode.selfit, forSplitHash)
    if(res == 0){
        writeFrom(path, curNode.selfit, "0")
    }
    if(res > 0|| res == 0 && Flag){
        val (a, b) = split(path, curNode.left, forSplitHash, Flag)
        curNode.left = b
        writeFrom(path, curNode.selfit + 2, digits(curNode.left, 6))
        return Pair(a, curNode.selfit)
    }
    else{
        val (a, b) = split(path, curNode.right, forSplitHash, Flag)
        curNode.right = a
        writeFrom(path, curNode.selfit + 2 + 7, digits(curNode.right, 6))
        return Pair(curNode.selfit, b)
    }
}