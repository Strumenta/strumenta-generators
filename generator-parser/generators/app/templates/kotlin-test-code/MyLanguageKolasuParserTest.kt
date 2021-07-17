package <%= packageName %>

import <%= packageName %>.ast.CompilationUnit
import <%= packageName %>.ast.HelloStmt
import org.junit.Test
import kotlin.test.assertEquals

class <%= languageName %>KolasuParserTest {

    @Test
    fun parseExample1FromString() {
        val code = "hello John"
        val parserFacade = <%= languageName %>KolasuParserFacade()
        val result = parserFacade.parse(code, considerPosition = false)
        assertEquals(true, result.correct)
        assertEquals(true, result.issues.isEmpty())
        assertEquals(CompilationUnit(listOf(HelloStmt("John"))), result.root)
    }

    @Test
    fun parseExample1FromFile() {
        val parserFacade = <%= languageName %>KolasuParserFacade()
        val result = parserFacade.parse(this.javaClass.getResourceAsStream("/example1.hello"),
                considerPosition = false)
        assertEquals(true, result.correct)
        assertEquals(true, result.issues.isEmpty())
        assertEquals(CompilationUnit(listOf(HelloStmt("John"))), result.root)
    }
}