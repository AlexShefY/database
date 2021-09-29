import java.security.MessageDigest

fun digits(a : Int) : String{
    var res : String = ""
    var b = a
    for(i in 0..5){
        res += (b % 10).toString()
        b /= 10
    }
    return res.reversed()
}
class node(var used : Boolean, var Hash : String, var Key : String,var value : String,var left : Int, var right : Int, var selfit : Int){
    fun countHash()
    {
        val md = MessageDigest.getInstance(Key)
        Hash = md.digest(Key.toByteArray()).toString()
    }
    override fun toString() : String{
        var res : String = ""
        res += used.toString()
        res += "*"
        res += digits(left)
        res +="*"
        res += digits(right)
        res += "*"
        res += Hash
        res += "*"
        res += Key
        res += "*"
        res += value
        res += "*"
        res += digits(selfit)
        res += "|"
        return res
    }
}

