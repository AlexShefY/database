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
        RemoveAll()
        In(listOf("in", "word1"))
        Remove(listOf("remove", "word2"))
        Add(listOf("add", "key1", "value1"))
        Add(listOf("add", "key11", "value2"))
        Add(listOf("add", "key1", "value3"))
        Extract(listOf("extract", "key1"))
        Extract(listOf("extract", "key2"))
        In(listOf("in"))
        Add(listOf("add", "key3"))
        RemoveIf(listOf("RemoveIf", "regex", "key1\\w*"))
        Extract(listOf("extract", "key1"))
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
        RemoveAll()
        val Time = measureNanoTime {
        var test = 1e3.toInt()
        while(test > 0){
            cnt = 0
            Add(listOf("in", "$test", "${test * test}"))
            var q = (test..1e3.toInt()).random()
            var new = node(1, "0".repeat(64), (0..(1e15 - 1).toInt()).random(), "$q", "", 0, 0, WorkWithFile("file_data.txt").getLength())
            new.countHash()
            startnodeindex = WorkWithFile("file_data.txt").readFirst()
            assert(find(startnodeindex, new))
            assert(cnt <= 100)
            test--
        }
        }
        assert(Time / 1e9 <= 200) // проработали меньше 200 секунд
    }
}
