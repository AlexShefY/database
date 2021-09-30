
fun addNode(new : node){
    startnodeindex = WorkWithFile("file_data.txt").readFirst()
    WorkWithFile("file_data.txt").addWrite(new.toString())
    globalIt += new.toString().length
    if(startnodeindex == 0){
        startnodeindex = new.selfit
    }
    else {
        startnodeindex = goAdd(startnodeindex, new)
    }
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
    var curNode = WorkWithFile("file_data.txt").readFrom(nodeindex)
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
    var curNode = WorkWithFile("file_data.txt").readFrom(nodeindex)
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