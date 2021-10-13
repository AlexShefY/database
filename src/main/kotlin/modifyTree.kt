import kotlin.math.*
var cnt = 0
/*
 * Эта функция добавляет новую вершину декартова дерева в файл и вызывает goAdd,
 * которая добавит вершину в само дерево
 */
fun addNode(new : node){
    startnodeindex = WorkWithFile("file_data.txt").readFirst()
    WorkWithFile("file_data.txt").addWrite(new.toString())
    val (first, second) = split(startnodeindex, new.Hash, false)
    val first2 = merge(first, new.selfit)
    startnodeindex = merge(first2, second)
    WorkWithFile("file_data.txt").writeFrom(0, digits(startnodeindex, 6))
}
/*
 * Функция удаления вершины из декартова дерева, которая делит дерево
 * на поддеревья вершин с меньшим хэшом, с равным(в этом поддереве будет только
 *  вершина, которую мы удаляем, и с большим хэшом. А потом сливает
 * поддеревья с меньшим и большим хэшом в одно декртово дерево
 */
fun removeNode(cur : node){
    startnodeindex = WorkWithFile("file_data.txt").readFirst()
    val (first, second) = split(startnodeindex, cur.Hash, false)
    val (first1, second1) = split(first, cur.Hash, true)
    startnodeindex = merge(first1, second)
    WorkWithFile("file_data.txt").writeFrom(0, digits(startnodeindex, 6))
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
fun compareHash(itfirst : Int, HashSecond : String) : Int{
    var parts = 0
    while(parts < 16){
        var str1 = WorkWithFile("file_data.txt").readCnt(itfirst + parts * 4 + 16, 4)
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
fun find(nodeindex : Int, toFind : node) : Boolean{
    cnt++
    if(nodeindex == 0){
        return false
    }
    var curNode = WorkWithFile("file_data.txt").readFromPart(nodeindex)
    var rescompare = compareHash(curNode.selfit, toFind.Hash)
    if(rescompare == 0){
        return true
    }
    if(rescompare > 0){
        return find(curNode.left, toFind)
    }
    else{
        return find(curNode.right, toFind)
    }
}
/*
 * Функция, строящая декартово дерево по имеющимся
 * у нас парам ключ-значение
 */
fun build(){
    WorkWithFile("file_data.txt").addWrite(digits(startnodeindex, 6) + "|")
    for(p in map){
        Add(listOf("add", p.Key, p.value))
    }
}
/*
 * Функция, которая находит вершину в декартовом дереве с заданным ключом
 * и возвращает значение для этого ключа
 */
fun getValue(nodeindex : Int, toFind : node) : String{
    var curNode = WorkWithFile("file_data.txt").readFromPart(nodeindex)
    var res = compareHash(curNode.selfit, toFind.Hash)
    if(res == 0){
        curNode = WorkWithFile("file_data.txt").readFromFull(nodeindex)
        return curNode.value
    }
    if(res > 0){
        return getValue(curNode.left, toFind)
    }
    else{
        return getValue(curNode.right, toFind)
    }
}
/*
 * Функция, которая сливает два поддерева
 */
fun merge(nodeindex1 : Int, nodeindex2 : Int) : Int{
    cnt++
    if(nodeindex1 == 0){
        return nodeindex2
    }
    if(nodeindex2 == 0){
        return nodeindex1
    }
    var curNode1 = WorkWithFile("file_data.txt").readFromPart(nodeindex1)
    var curNode2 = WorkWithFile("file_data.txt").readFromPart(nodeindex2)
    if(curNode1.priority > curNode2.priority){
        curNode1.right = merge(curNode1.right, nodeindex2)
        WorkWithFile("file_data.txt").writeFrom(curNode1.selfit + 2 + 7, digits(curNode1.right, 6))
        return curNode1.selfit
    }
    else{
        curNode2.left = merge(nodeindex1, curNode2.left)
        WorkWithFile("file_data.txt").writeFrom(curNode2.selfit + 2, digits(curNode2.left, 6))
        return curNode2.selfit
    }
}
/*
 * Функция, разделяющая декартово дерево на два поддерева:
 * с большим хэшом и меньшим хэшом( при этом в одном из поддеревьев может
 * оказаться вершина с заданным хэшом, это зависит от флага)
 */
fun split(nodeindex : Int, forSplitHash :String, Flag : Boolean) : Pair<Int, Int>{
    cnt++
    if(nodeindex == 0){
        return Pair(0, 0)
    }
    var curNode = WorkWithFile("file_data.txt").readFromPart(nodeindex)
    var res = compareHash(curNode.selfit, forSplitHash)
    if(res == 0){
        WorkWithFile("file_data.txt").writeFrom(curNode.selfit, "0")
    }
    if(res > 0|| res == 0 && Flag){
        val (a, b) = split(curNode.left, forSplitHash, Flag)
        curNode.left = b
        WorkWithFile("file_data.txt").writeFrom(curNode.selfit + 2, digits(curNode.left, 6))
        return Pair(a, curNode.selfit)
    }
    else{
        val (a, b) = split(curNode.right, forSplitHash, Flag)
        curNode.right = a
        WorkWithFile("file_data.txt").writeFrom(curNode.selfit + 2 + 7, digits(curNode.right, 6))
        return Pair(curNode.selfit, b)
    }
}