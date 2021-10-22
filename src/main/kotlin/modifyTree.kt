import kotlin.math.*
var cnt = 0
/*
 * This function adds a new top of the Cartesian tree to the file and calls goAdd.
 * which will add the vertex to the tree itself
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
 * The function of removing a vertex from a Cartesian tree that divides the tree
 * to the subtrees of vertices with a smaller hash, with equal (in this subtree there will be only
 * the vertex we are removing) and vertices with a large hash. And then it drains
 * subtrees with smaller and larger hash into one dekart tree
 */
fun removeNode(path : String, cur : node){
    startnodeindex = readFirst(path)
    val (first, second) = split(path, startnodeindex, cur.Hash, false)
    val (first1, second1) = split(path, first, cur.Hash, true)
    startnodeindex = merge(path, first1, second)
    writeFrom(path, 0, digits(startnodeindex, 6))
}
/*
 * A function that swaps the values of two class elements in places
 */
fun swap(a : node, b : node){
    var c = node()
    c.set(a)
    a.set(b)
    b.set(c)
}
/*
 * Compare string hashes
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
 * The function of finding a vertex in a tree with a given key
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
 * Function that builds a Cartesian tree from the available ones
 * we have key-value pairs
 */
fun build(path : String){
    addWrite(path, digits(startnodeindex, 6) + "|")
    for(p in map){
        Add(listOf("add", path, p.Key, p.value))
    }
}
/*
 * A function that finds a node in a Cartesian tree with a given key
 * and returns the value for this key
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
 * A function that merges two subtrees
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
 * A function that splits a Cartesian tree into two subtrees:
 * with a larger hash and a smaller hash (while in one of the subtrees can
 * turn out to be a vertex with a given hash, it depends on the Flag)
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