
fun addNode(new : node){
    startnodeindex = WorkWithFile("file_data.txt").readFirst()
    WorkWithFile("file_data.txt").addWrite(new.toString())
    if(startnodeindex == 0){
        startnodeindex = new.selfit
    }
    else {
        startnodeindex = goAdd(startnodeindex, new)
    }
    WorkWithFile("file_data.txt").writeFrom(0, digits(startnodeindex, 6))
}

fun removeNode(cur : node){
    startnodeindex = WorkWithFile("file_data.txt").readFirst()
    val (first, second) = split(startnodeindex, cur.Hash, false)
    val (first1, second1) = split(first, cur.Hash, true)
    startnodeindex = merge(first1, second)
    WorkWithFile("file_data.txt").writeFrom(0, digits(startnodeindex, 6))
}
fun swap(a : node, b : node){
    var c = node()
    c.set(a)
    a.set(b)
    b.set(c)
}
fun goAdd(nodeindex : Int, new : node) : Int{
    if(nodeindex == 0){
        return new.selfit
    }
    var curNode = WorkWithFile("file_data.txt").readFromPart(nodeindex)
    if(new.priority > curNode.priority){
        swap(new, curNode)
    }
    if(new.Hash < curNode.Hash){
        curNode.left = goAdd(curNode.left, new)
        WorkWithFile("file_data.txt").writeFrom(curNode.selfit + 2, digits(curNode.left, 6))
    }
    else{
        curNode.right = goAdd(curNode.right, new)
        WorkWithFile("file_data.txt").writeFrom(curNode.selfit + 2 + 7, digits(curNode.right, 6))
    }
    return curNode.selfit
}


fun find(nodeindex : Int, toFind : node) : Boolean{
    if(nodeindex == 0){
        return false
    }
    var curNode = WorkWithFile("file_data.txt").readFromPart(nodeindex)
    if(toFind.Hash == curNode.Hash){
        return true
    }
    if(toFind.Hash < curNode.Hash){
        return find(curNode.left, toFind)
    }
    else{
        return find(curNode.right, toFind)
    }
}

fun build(){
    WorkWithFile("file_data.txt").addWrite(digits(startnodeindex, 6) + "|")
    for(p in map){
        Add(listOf("add", p.Key, p.value))
    }
}

fun getValue(nodeindex : Int, toFind : node) : String{
    var curNode = WorkWithFile("file_data.txt").readFromPart(nodeindex)
    if(toFind.Hash == curNode.Hash){
        curNode = WorkWithFile("file_data.txt").readFromFull(nodeindex)
        return curNode.value
    }
    if(toFind.Hash < curNode.Hash){
        return getValue(curNode.left, toFind)
    }
    else{
        return getValue(curNode.right, toFind)
    }
}

fun merge(nodeindex1 : Int, nodeindex2 : Int) : Int{
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
        return curNode1.selfit
    }
    else{
        curNode2.left = merge(nodeindex1, curNode2.left)
        return curNode2.selfit
    }
}

fun split(nodeindex : Int, forSplitHash : String, Flag : Boolean) : Pair<Int, Int>{
    if(nodeindex == 0){
        return Pair(0, 0)
    }
    var curNode = WorkWithFile("file_data.txt").readFromPart(nodeindex)
    if(curNode.Hash == forSplitHash){
        WorkWithFile("file_data.txt").writeFrom(curNode.selfit, "0")
    }
    if(curNode.Hash > forSplitHash || curNode.Hash == forSplitHash && Flag){
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