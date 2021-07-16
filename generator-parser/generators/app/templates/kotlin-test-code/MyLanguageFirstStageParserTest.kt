package <%= packageName %>

import com.strumenta.kolasu.parsing.toParseTree
import com.strumenta.kolasu.testing.assertParseTreeStr
import <%= packageName %>.ast.CompilationUnit
import <%= packageName %>.ast.HelloStmt
import org.junit.Test
import kotlin.test.assertEquals

class <%= languageName %>FirstStageParserTest {

    @Test
    fun parseExample1FromString() {
        val code = "hello John"
        val parserFacade = <%= languageName %>KolasuParserFacade()
        val result = parserFacade.parseFirstStage(code)
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
        val parserFacade = <%= languageName %>KolasuParserFacade()
        val result = parserFacade.parseFirstStage(this.javaClass.getResourceAsStream("/example1.hello"))
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