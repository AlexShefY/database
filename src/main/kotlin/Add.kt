
/*
 * Function for adding a key-value pair
 */

fun Add(arr : List<String>){
    if(arr.size != 4){
        Error(Errors.Size)
        return
    }
    var new = createNode(arr[2], arr[3], arr[1])
    startnodeindex = readFirst(arr[1])
    if(find(arr[1], startnodeindex, new)){
        Error(Errors.Exists)
    }
    else {
        addNode(arr[1], new)
    }
}