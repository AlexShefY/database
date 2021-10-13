import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.system.measureNanoTime
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
        RemoveAll(listOf("remove", "file_data.txt"))
        Add(listOf("add", "file_data.txt", "one", "1"))
        Add(listOf("add", "file_data.txt", "two", "2"))
        Extract(listOf("extract", "file_data.txt", "two"))
        In(listOf("in", "file_data.txt", "one"))
        Extract(listOf("extract", "file_data.txt", "three"))
        Remove(listOf("remove", "file_data.txt", "three"))
        Remove(listOf("remove", "file_data.txt", "two"))
        Extract(listOf("extract", "file_data.txt", "two"))
        Add(listOf("add", "file_data.txt", "one hundred", "100"))
        RemoveIf(listOf("RemoveIf", "file_data.txt", "regex", "one \\w*"))
        In(listOf("in", "file_data.txt", "one hundred"))
        Extract(listOf("extract", "file_data.txt", "one"))
        assertEquals(getOutput(), listOf("2", "This key exists", "This item doesn`t exist", "This item doesn`t exist", "This item doesn`t exist","This key doesn`t exist", "1"))
    }
    @Test
    fun test2(){
        RemoveAll(listOf("remove", "file_data.txt"))
        In(listOf("in", "file_data.txt", "word1"))
        Remove(listOf("remove", "file_data.txt", "word2"))
        Add(listOf("add", "file_data.txt", "key1", "value1"))
        Add(listOf("add", "file_data.txt", "key11", "value2"))
        Add(listOf("add", "file_data.txt", "key1", "value3"))
        Extract(listOf("extract", "file_data.txt", "key1"))
        Extract(listOf("extract", "file_data.txt", "key2"))
        In(listOf("in", "file_data.txt"))
        Add(listOf("add", "file_data.txt", "key3"))
        RemoveIf(listOf("RemoveIf", "file_data.txt", "regex", "key1\\w*"))
        Extract(listOf("extract", "file_data.txt", "key1"))
        assertEquals(getOutput(), listOf("This key doesn`t exist", "This item doesn`t exist", "This item already exists", "value1", "This item doesn`t exist", "Invalid amount of input",
            "Invalid amount of input", "This item doesn`t exist"))
    }
    @Test
    fun testHashes(){
        assertEquals(hashString("abslsdjkfglhekdjbgfhtjrsk;eglrsEA29!", "SHA-256"), hashString("abslsdjkfglhekdjbgfhtjrsk;eglrsEA29!", "SHA-256"))
        assertEquals(hashString("mdckns0f421-3irlknsldjh2pwjnlnjj\\/.'::", "SHA-256"), hashString("mdckns0f421-3irlknsldjh2pwjnlnjj\\/.'::", "SHA-256"))
        assertNotEquals(hashString("abc", "SHA-256"), hashString("abcd", "SHA-256"))
        assertNotEquals(hashString("njuhioomnjhiupwkjjhywgefpw", "SHA-256"), hashString("njuhioomnjhiupwkjjhywgef.pw", "SHA-256"))
    }
    /*
     * Проверяю время и корректность работы
     */
    @Test
    fun testTimer(){
        RemoveAll(listOf("remove", "file_data.txt"))
        val Time = measureNanoTime {
        var test = 1000.toInt()
        while(test > 0){
            cnt = 0
            Add(listOf("in", "file_data.txt", "$test", "${test * test}"))
            var q = (test..1000.toInt()).random()
            var new = node(1, "0".repeat(64), (0..(1e15 - 1).toInt()).random(), "$q", "", 0, 0, getLength("file_data.txt"))
            new.countHash()
            startnodeindex = readFirst("file_data.txt")
            assert(find("file_data.txt", startnodeindex, new))
            assert(cnt <= 100)
            test--
        }
        }
        assert(Time / 1e9 <= 400) // проработали меньше 400 секунд
    }
}
