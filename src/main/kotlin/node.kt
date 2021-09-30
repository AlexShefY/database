import java.security.MessageDigest

/*
 * Преобразуем число в строку длины 6
 * (возмножно с лидирующими нулями)
 */
fun digits(a : Int) : String{
    return "%06d".format(a)
}
/*
 * Хэшируем строку
 */
private fun hashString(input: String, algorithm: String): String {
    return MessageDigest
        .getInstance(algorithm)
        .digest(input.toByteArray())
        .fold("", { str, it -> str + "%02x".format(it) })
}
class node(var used : Boolean, var Hash : String, var Key : String,var value : String,var left : Int, var right : Int, var selfit : Int){
    fun countHash()
    {
        Hash = hashString(Key, "SHA-256")
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

