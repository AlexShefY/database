import java.security.MessageDigest

/*
 * Преобразуем число в строку длины 6
 * (возмножно с лидирующими нулями)
 */
fun digits(a : Int, t : Int) : String{
    return "%0${t}d".format(a)
}
/*
 * Хэшируем строку
 */
fun hashString(input: String, algorithm: String): String {
    return MessageDigest
        .getInstance(algorithm)
        .digest(input.toByteArray())
        .fold("", { str, it -> str + "%02x".format(it) })
}
class node(var used : Int = 0, var Hash : String = "", var priority : Int = 0, var Key : String = "",var value : String = "",var left : Int = 0, var right : Int = 0, var selfit : Int = 0){
    fun countHash()
    {
        Hash = hashString(Key, "SHA-256")
    }
    fun set(other : node){
        used = other.used
        Hash = other.Hash
        priority = other.priority
        left = other.left
        right = other.right
        selfit = other.selfit
    }
    override fun toString() : String{
        var res : String = ""
        res += used.toString()
        res += "*"
        res += digits(left, 6)
        res +="*"
        res += digits(right, 6)
        res += "*"
        res += Hash
        res += "*"
        res += digits(priority, 15)
        res += "*"
        res += digits(selfit, 6)
        res += "*"
        res += Key
        res += "*"
        res += value
        res += "|"
        return res
    }
}

