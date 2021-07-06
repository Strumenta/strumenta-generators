package <%= packageName %>

import <%= packageName %>.ast.CompilationUnit
import <%= packageName %>.ast.HelloStmt
import org.junit.Test
import kotlin.test.assertEquals

class <%= languageName %>ParserTest {

    @Test
    fun parseSimpleFile() {
        val code = "hello John"
        val parserFacade = <%= languageName %>KolasuParserFacade()
        val result = parserFacade.parse(code, considerPosition = false)
        assertEquals(true, result.correct)
        assertEquals(true, result.issues.isEmpty())
        assertEquals(CompilationUnit(listOf(HelloStmt("John"))), result.root)
    }
}