package <%= packageName %>

import com.strumenta.kolasu.testing.assertParseTreeStr
import org.junit.Test
import kotlin.test.assertEquals

class <%= languageName %>FirstStageParserTest {

    @Test
    fun parseExample1FromString() {
        val code = "hello John"
        val kolasuParser = <%= languageName %>KolasuParser()
        val result = kolasuParser.parseFirstStage(code)
        assertEquals(true, result.correct)
        assertEquals(true, result.issues.isEmpty())

        assertParseTreeStr(
                """CompilationUnit
                  |  HelloStmt
                  |    T:HELLO[hello]
                  |    T:ID[John]
                  |  T:EOF[<EOF>]""".trimMargin("|"), result.root!!, <%= languageName %>Parser.VOCABULARY)
    }

    @Test
    fun parseExample1FromFile() {
        val kolasuParser = <%= languageName %>KolasuParser()
        val result = kolasuParser.parseFirstStage(this.javaClass.getResourceAsStream("/example1.hello"))
        assertEquals(true, result.correct)
        assertEquals(true, result.issues.isEmpty())

        assertParseTreeStr(
                """CompilationUnit
                  |  HelloStmt
                  |    T:HELLO[hello]
                  |    T:ID[John]
                  |  T:EOF[<EOF>]""".trimMargin("|"), result.root!!, <%= languageName %>Parser.VOCABULARY)
    }
}
