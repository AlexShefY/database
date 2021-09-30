import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.*

internal class Test1 {

    private val standardOut = System.out
    private val standardIn = System.`in`
    private val stream = ByteArrayOutputStream()
    @BeforeTest
    fun setUp() {
        System.setOut(PrintStream(stream))
    }
    @AfterTest
    fun tearDown() {
        System.setOut(standardOut)
        System.setIn(standardIn)
    }
    fun getOutput() : List<String>{
        return stream.toString().trim().lines()
    }
    @Test
    fun test1(){
        RemoveAll()
        Add(listOf("add", "one", "1"))
        Add(listOf("add", "two", "2"))
        Extract(listOf("extract", "two"))
        In(listOf("in", "one"))
        Extract(listOf("extract", "three"))
        Remove(listOf("remove", "three"))
        Remove(listOf("remove", "two"))
        Extract(listOf("extract", "two"))
        Add(listOf("add", "one hundred", "100"))
        RemoveIf(listOf("RemoveIf", "regex", "one \\w*"))
        In(listOf("in", "one hundred"))
        Extract(listOf("extract", "one"))
        assertEquals(getOutput(), listOf("2", "This key exists", "This item doesn`t exist", "This item doesn`t exist", "This item doesn`t exist","This key doesn`t exist", "1"))
    }
    @Test
    fun test2(){
        assertEquals(hashString("abslsdjkfglhekdjbgfhtjrsk;eglrsEA29!", "SHA-256"), hashString("abslsdjkfglhekdjbgfhtjrsk;eglrsEA29!", "SHA-256"))
        assertEquals(hashString("mdckns0f421-3irlknsldjh2pwjnlnjj\\/.'::", "SHA-256"), hashString("mdckns0f421-3irlknsldjh2pwjnlnjj\\/.'::", "SHA-256"))
        assertNotEquals(hashString("abc", "SHA-256"), hashString("abcd", "SHA-256"))
        assertNotEquals(hashString("njuhioomnjhiupwkjjhywgefpw", "SHA-256"), hashString("njuhioomnjhiupwkjjhywgef.pw", "SHA-256"))
    }
}
